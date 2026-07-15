# kotlin-api-automation

REST API test automation written in **Kotlin** — JUnit 5, REST Assured, Gradle (Kotlin DSL).
Tests run against the public [reqres.in](https://reqres.in) sandbox API.

## Stack

- Kotlin 1.9
- JUnit 5 (Jupiter)
- REST Assured 5 (+ Kotlin extensions)
- Gradle (Kotlin DSL)

## What's covered

- `GET /users` — paginated list of users
- `GET /users/{id}` — single user lookup with field assertions
- `GET /users/{id}` — 404 handling for an unknown user
- `POST /users` — resource creation

## Run locally

Open the project in IntelliJ IDEA and let it sync Gradle, or from the command line:

```bash
gradle test
```

## CI

GitHub Actions runs the full suite on every push and pull request (see `.github/workflows/ci.yml`).
