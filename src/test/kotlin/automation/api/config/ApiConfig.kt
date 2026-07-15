package automation.api.config

import io.restassured.RestAssured

/**
 * Shared REST Assured configuration for all API tests.
 * Points at the public reqres.in sandbox API.
 */
object ApiConfig {
    const val BASE_URL = "https://reqres.in/api"

    fun configure() {
        RestAssured.baseURI = BASE_URL
    }
}
