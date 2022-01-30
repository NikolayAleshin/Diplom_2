import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rest.RestUrl;

public class AuthorizationTests extends InitializationTests {

    public Response responseAuthorization;

    @Before
    public void init() {
        super.init();
        responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
    }

    @Test
    @DisplayName("Check authorization new user of /api/auth/login")
    @Description("Basic success test for /api/auth/login endpoint")
    public void authorizationUserCorrectTest() {

        UserResponse userResponse = responseAuthorization.getBody().as(UserResponse.class);

        Assert.assertEquals(200, responseAuthorization.getStatusCode());
        Assert.assertTrue(userResponse.isSuccess());
        Assert.assertEquals(user.getEmail(), userResponse.getUser().getEmail());
        Assert.assertEquals(user.getName(), userResponse.getUser().getName());
        Assert.assertNull(userResponse.getUser().getPassword());
        Assert.assertNotNull(userResponse.getAccessToken());
        Assert.assertNotNull(userResponse.getRefreshToken());
    }
}