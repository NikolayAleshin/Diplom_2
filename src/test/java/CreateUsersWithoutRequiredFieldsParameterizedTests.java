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
public class CreateUsersWithoutRequiredFieldsParameterizedTests extends InitializationTests {

    private User user;

    public CreateUsersWithoutRequiredFieldsParameterizedTests(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierData() {
        return new Object[][]{
                {new User(null, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10))},
                {new User(RandomStringUtils.randomAlphabetic(10),null, RandomStringUtils.randomAlphabetic(10))},
                {new User(RandomStringUtils.randomAlphabetic(10),RandomStringUtils.randomAlphabetic(10), null)},
                {new User(null,null, null)},
        };
    }

    @Test
    @DisplayName("Check created new user without required fields of /api/auth/register")
    @Description("Required fields test for /api/auth/register endpoint")
    public void checkUserWithoutRequiredFieldsTest(){

        Response responseRegistrationUser = restSend.postRequestAndReturnResponse(user, RestUrl.REGISTER_USER);
        UserResponse userResponse = responseRegistrationUser.getBody().as(UserResponse.class);

        Assert.assertEquals(403, responseRegistrationUser.getStatusCode());
        Assert.assertFalse(userResponse.isSuccess());
        Assert.assertEquals("Email, password and name are required fields", userResponse.getMessage());

    }
}