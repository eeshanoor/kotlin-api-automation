package automation.api

import automation.api.config.ApiConfig
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Basic Kotlin + REST Assured smoke suite for the DummyJSON "users" resource.
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
    @DisplayName("GET /users returns a paginated list of users")
    fun getUsersListReturnsExpectedPage() {
        given()
            .queryParam("limit", 10)
            .queryParam("skip", 10)
            .`when`()
            .get("/users")
            .then()
            .statusCode(200)
            .body("users.size()", equalTo(10))
            .body("total", greaterThan(0))
            .body("limit", equalTo(10))
            .body("skip", equalTo(10))
    }

    @Test
    @DisplayName("GET /users/{id} returns a single user with expected fields")
    fun getSingleUserReturnsExpectedFields() {
        given()
            .`when`()
            .get("/users/2")
            .then()
            .statusCode(200)
            .body("id", equalTo(2))
            .body("firstName", notNullValue())
            .body("lastName", notNullValue())
            .body("email", notNullValue())
    }

    @Test
    @DisplayName("GET /users/{id} returns 404 for a non-existent user")
    fun getNonExistentUserReturns404() {
        given()
            .`when`()
            .get("/users/999999")
            .then()
            .statusCode(404)
    }

    @Test
    @DisplayName("POST /users/add creates a new user and returns id + name")
    fun createUserReturnsIdAndName() {
        val payload = mapOf(
            "firstName" to "Eesha",
            "lastName" to "Noor",
            "age" to 25
        )
        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .`when`()
            .post("/users/add")
            .then()
            .statusCode(201)
            .body("firstName", equalTo("Eesha"))
            .body("lastName", equalTo("Noor"))
            .body("id", notNullValue())
    }
}