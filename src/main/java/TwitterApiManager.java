import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TwitterApiManager {

    private final TwitterConfig config;
    private final String oauthBaseUrl = "/oauth2/token";
    private final String userTimelineUrl = "/1.1/statuses/user_timeline.json";

    public TwitterApiManager(TwitterConfig config) {
        this.config = config;
    }

    public Response getUserTimeline(String userAccount, String count) {
        Response response = given()
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .param("screen_name", userAccount)
                .param("count", count)
                .get(config.baseUrl() + userTimelineUrl);

        response.then().log().all().statusCode(200);
        return response;
    }

    private String getAccessToken() {
        Response response = given()
                .auth().preemptive().basic(config.clientId(), config.clientSecret())
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .when()
                .post(config.baseUrl() + oauthBaseUrl);

        response.then().log().all().statusCode(200);

        return response.then().extract().path("access_token");
    }
}
