package automation.api

import automation.api.config.ApiConfig
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Basic Kotlin + REST Assured smoke suite for the reqres.in "users" resource.
 * Demonstrates GET (list + single), 404 handling, and POST.
 */
class UsersApiTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun setup() {
            ApiConfig.configure()
        }
    }

    @Test
    @DisplayName("GET /users?page=2 returns a paginated list of users")
    fun getUsersListReturnsExpectedPage() {
        given()
            .queryParam("page", 2)
            .`when`()
            .get("/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("page", equalTo(2))
            .body("data.size()", greaterThan(0))
            .body("data[0].id", notNullValue())
    }

    @Test
    @DisplayName("GET /users/{id} returns a single user with expected fields")
    fun getSingleUserReturnsExpectedFields() {
        given()
            .pathParam("id", 2)
            .`when`()
            .get("/users/{id}")
            .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@"))
            .body("data.first_name", not(nullValue()))
    }

    @Test
    @DisplayName("GET /users/{id} returns 404 for a non-existent user")
    fun getUnknownUserReturns404() {
        given()
            .pathParam("id", 23)
            .`when`()
            .get("/users/{id}")
            .then()
            .statusCode(404)
    }

    @Test
    @DisplayName("POST /users creates a new user and returns id + createdAt")
    fun createUserReturnsIdAndTimestamp() {
        val payload = mapOf("name" to "Eesha Noor", "job" to "Senior QA Engineer")

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .`when`()
            .post("/users")
            .then()
            .statusCode(201)
            .body("name", equalTo("Eesha Noor"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue())
    }
}
