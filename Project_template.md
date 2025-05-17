## Изучите [README.md](./README.md) файл и структуру проекта.

# Задание 1

[Диаграмма контейнеров "КиноБездны"](c4/cinemaabyss-containers.puml)

# Задание 2

### 1. Proxy
Сервис реализован

### 2. Kafka
[Тесты](image/kafka-tests.png)

[Топики](image/kafka-topics.png)

[Топик movie-events](image/movie-events-topic.png)

[Топик payment-events](image/payment-events-topic.png)

[Топик user-events](image/payment-events-topic.png)

# Задание 3

### Proxy в Kubernetes
[Вывод при вызове https://cinemaabyss.example.com/api/movies](image/k8s-movies-service-request.png)

[Вывод event-service после вызова тестов](image/k8s-events-service-log.png)

[Тесты](image/k8s-postman-tests.png)

# Задание 4
[Вывод при вызове https://cinemaabyss.example.com/api/movies](image/helm-movies-service-request.png)

[Тесты](image/helm-movies-service-request.png)
