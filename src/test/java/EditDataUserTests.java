import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import model.UserResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import rest.RestUrl;

public class EditDataUserTests extends InitializationTests {

    private User userEdit;

    @Test
    @DisplayName("Check edit data authorization new user of /api/auth/user")
    @Description("Basic success test for /api/auth/user endpoint")
    public void editDataUserAuthorizationTest(){

        Response responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
        UserResponse userResponseAuthorization = responseAuthorization.getBody().as(UserResponse.class);

        userEdit = new User(RandomStringUtils.randomAlphabetic(10).toLowerCase(), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        Response responseEditUser = restSend.patchRequestAndReturnResponse(userEdit, RestUrl.EDIT_DATA_USER, userResponseAuthorization.getAccessToken());

        UserResponse userResponse = responseEditUser.getBody().as(UserResponse.class);

        Assert.assertEquals(200, responseEditUser.getStatusCode());
        Assert.assertTrue(userResponse.isSuccess());
        Assert.assertEquals(userEdit.getEmail(), userResponse.getUser().getEmail());
        Assert.assertEquals(userEdit.getName(), userResponse.getUser().getName());
        Assert.assertNull(userResponse.getUser().getPassword());

        Response responseDataUser = restSend.getRequestAndReturnResponse(RestUrl.EDIT_DATA_USER, userResponseAuthorization.getAccessToken());
        UserResponse userEditResponse = responseEditUser.getBody().as(UserResponse.class);

        Assert.assertEquals(200, responseDataUser.getStatusCode());
        Assert.assertTrue(userEditResponse.isSuccess());
        Assert.assertEquals(userEdit.getEmail(), userEditResponse.getUser().getEmail());
        Assert.assertEquals(userEdit.getName(), userEditResponse.getUser().getName());
        Assert.assertNull(userEditResponse.getUser().getPassword());
    }

    @Test
    @DisplayName("Check edit data no authorization new user of /api/auth/user")
    @Description("Edit data for /api/auth/user endpoint")
    public void editDataUserNoAuthorizationTest(){

        userEdit = new User(RandomStringUtils.randomAlphabetic(10).toLowerCase(), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        Response responseEditUser = restSend.patchRequestAndReturnResponse(userEdit, RestUrl.EDIT_DATA_USER, "");

        UserResponse userResponse = responseEditUser.getBody().as(UserResponse.class);

        Assert.assertEquals(401, responseEditUser.getStatusCode());
        Assert.assertFalse(userResponse.isSuccess());
        Assert.assertEquals("You should be authorised", userResponse.getMessage());
    }
}