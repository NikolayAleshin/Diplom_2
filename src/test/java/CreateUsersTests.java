import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import rest.RestUrl;

public class CreateUsersTests extends InitializationTests {

    @Test
    @DisplayName("Check created new user of /api/auth/register")
    @Description("Basic success test for /api/auth/register endpoint")
    public void createNewUsersCorrectTest(){

        UserResponse userResponse = responseRegistration.getBody().as(UserResponse.class);

        Assert.assertEquals(200, responseRegistration.getStatusCode());
        Assert.assertTrue(userResponse.isSuccess());
        Assert.assertEquals(user.getEmail(), userResponse.getUser().getEmail());
        Assert.assertEquals(user.getName(), userResponse.getUser().getName());
        Assert.assertNull(userResponse.getUser().getPassword());
        Assert.assertNotNull(userResponse.getAccessToken());
        Assert.assertNotNull(userResponse.getRefreshToken());
    }

    @Test
    @DisplayName("Check created already exists user of /api/auth/register")
    @Description("Basic success test for /api/auth/register endpoint")
    public void createAlreadyExistsUsersCorrectTest(){

        Response responseRegistrationUser = restSend.postRequestAndReturnResponse(user, RestUrl.REGISTER_USER);

        UserResponse userResponse = responseRegistrationUser.getBody().as(UserResponse.class);

        Assert.assertEquals(403, responseRegistrationUser.getStatusCode());
        Assert.assertFalse(userResponse.isSuccess());
        Assert.assertEquals("User already exists", userResponse.getMessage());
    }
}
