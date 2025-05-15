package ru.hammerforyou.proxy;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

/**
 * @author Maxim Nikolsky
 */
@RestController
public class ProxyController {
    private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final TargetUrlResolver resolver;

    public ProxyController(TargetUrlResolver resolver) {
        this.resolver = resolver;
    }

    @GetMapping("/health")
    public ResponseEntity<ProxyHealth> health() {
        return ResponseEntity.ok(new ProxyHealth(true));
    }

    @RequestMapping("/**")
    public ResponseEntity<byte[]> proxyRequest(
            @RequestBody(required = false) byte[] body,
            HttpServletRequest request
    ) {
        var url = createUrl(request);
        var method = HttpMethod.valueOf(request.getMethod());
        var headers = createHeaders(request);
        var entity = new HttpEntity<>(body, headers);

        logger.info("Proxying request to {}", url);
        var response = restTemplate.exchange(
                url,
                method,
                entity,
                byte[].class
        );
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    private String createUrl(HttpServletRequest request) {
        var path = request.getRequestURI();
        var query = request.getQueryString();

        return UriComponentsBuilder.fromUriString(resolver.resolve(path))
                .path(path)
                .query(query)
                .build()
                .toUriString();
    }

    private HttpHeaders createHeaders(HttpServletRequest request) {
        var headers = new HttpHeaders();
        var headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            var name = headerNames.nextElement();
            headers.addAll(name, Collections.list(request.getHeaders(name)));
        }
        headers.remove(HttpHeaders.HOST);
        return headers;
    }

    public record ProxyHealth(
            boolean status
    ) {}
}
