package automation.api.config

import io.restassured.RestAssured

/**
 * Shared REST Assured configuration for all API tests.
 * Points at the public DummyJSON sandbox API (dummyjson.com) — a free,
 * key-less REST API with persistent-looking CRUD, pagination and proper
 * HTTP status codes (used after reqres.in moved to a paid API-key model).
 */
object ApiConfig {
    const val BASE_URL = "https://dummyjson.com"

    fun configure() {
        RestAssured.baseURI = BASE_URL
    }
}