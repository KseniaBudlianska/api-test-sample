import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserTimelineTwitterTest {

    @DataProvider
    public Object[][] userData() {
        return new Object[][] {
            {"Hello", "Hello"},
            {"n26", "thank you for your feedback"}
        };
    }

    @Test(dataProvider = "userData")
    public void userTimelineTwitterTest(String userAccount, String expectedPartOfText) {
        TwitterConfig twitterConfig = new TwitterConfig();
        TwitterApiManager twitterApiManager = new TwitterApiManager(twitterConfig);
        Response response = twitterApiManager.getUserTimeline(userAccount, "1");

        String userTwit = response.then().extract().path("text").toString();
        Assert.assertTrue(userTwit.contains(expectedPartOfText));
    }

}
