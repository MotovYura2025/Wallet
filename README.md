text
# Wallet API

Проект на Java 17, Spring Boot 3 и PostgreSQL. REST API для управления кошельками: пополнение, снятие средств и получение баланса.

---

## Сборка и запуск

### Сборка проекта

mvn clean package

text

### Запуск в Docker

docker-compose up --build -d

text

---

## Использование API

### POST /api/v1/wallet

Пример запроса:

{
"walletId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"operationType": "DEPOSIT",
"amount": 1000
}

text

### GET /api/v1/wallets/{walletId}

Пример успешного ответа:

{
"walletId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"balance": 5000
}

text

---

## Запуск тестов

mvn test

text

---

## Структура проекта

- `src/main/java/com/example/walletapi/controller` — REST контроллеры
- `src/main/java/com/example/walletapi/dto` — DTO объекты
- `src/main/java/com/example/walletapi/entity` — JPA сущности
- `src/main/java/com/example/walletapi/repository` — Репозитории Spring Data JPA
- `src/main/java/com/example/walletapi/service` — Сервисы бизнес-логики
- `src/main/resources` — Конфигурационные файлы
- Корень проекта содержит: `Dockerfile`, `docker-compose.yml`, `pom.xml`, `README.md`

---

## Особенности

- Используется Jakarta Bean Validation для проверки данных
- Обработка ошибок с корректными HTTP-кодами (400, 404, 409)
- Документирование API с помощью OpenAPI (Swagger UI)
- Запуск и настройка базы данных PostgreSQL через Docker Compose

---

## Контакты +79084567750

Email: yuraggmotoff@gmail.com