package example2.tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLoginAPITest {

    @Test
    public void testVerifyLogin() {
        // Set the base URL
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Send the POST request
        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", "hagai.tregerman@gmail.com")
                .formParam("password", "KMsuTYNyY@Q5y")
                .post("/verifyLogin");

        // Validate the response code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");

        // Validate the response message
        String responseMessage = response.jsonPath().getString("message");
        Assert.assertEquals(responseMessage, "User exists!", "Expected response message is 'User exists!'");

    }
}