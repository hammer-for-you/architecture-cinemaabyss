package ru.hammerforyou.proxy.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.hammerforyou.proxy.TargetUrlResolver;

import java.util.Random;

/**
 * @author Maxim Nikolsky
 */
@Component
public class TargetUrlResolverImpl implements TargetUrlResolver {
    private final boolean gradualMigrationEnabled;
    private final int gradualMigrationPercent;
    private final String monolithUrl;
    private final String movieServiceUrl;
    private final Random random = new Random();

    public TargetUrlResolverImpl(
            @Value("${app.gradual-migration.enabled}") boolean gradualMigrationEnabled,
            @Value("${app.gradual-migration.migration-percent}") int gradualMigrationPercent,
            @Value("${app.api.monolith-url}") String monolithUrl,
            @Value("${app.api.movies-service-url}") String movieServiceUrl
    ) {
        this.gradualMigrationEnabled = gradualMigrationEnabled;
        this.gradualMigrationPercent = gradualMigrationPercent;
        this.monolithUrl = monolithUrl;
        this.movieServiceUrl = movieServiceUrl;
    }

    @Override
    public String resolve(String path) {
        if (!gradualMigrationEnabled || !path.startsWith("/api/movies")) {
            return monolithUrl;
        }
        if (random.nextInt(100) < gradualMigrationPercent) {
            return movieServiceUrl;
        }
        return monolithUrl;
    }
}
