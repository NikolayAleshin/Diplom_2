import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import model.UserResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import rest.RestUrl;

@RunWith(Parameterized.class)
public class AuthorizationWithoutRequiredFieldsParameterizedTests extends InitializationTests {

    private User user;

    public AuthorizationWithoutRequiredFieldsParameterizedTests(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierData() {
        return new Object[][]{
                {new User(null, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10))},
                {new User(RandomStringUtils.randomAlphabetic(10),null, RandomStringUtils.randomAlphabetic(10))},
                {new User(null,null, null)},
        };
    }

    @Test
    @DisplayName("Check authorization without required field user of /api/auth/login")
    @Description("authorization test for /api/auth/login endpoint")
    public void authorizationWithoutRequiredNameFieldTest() {

        Response responseAuthorizationUser = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);

        UserResponse userResponse = responseAuthorizationUser.getBody().as(UserResponse.class);

        Assert.assertEquals(401, responseAuthorizationUser.getStatusCode());
        Assert.assertFalse(userResponse.isSuccess());
        Assert.assertEquals("email or password are incorrect", userResponse.getMessage());
    }
}
