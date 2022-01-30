import data.DataGeneratedUsers;
import io.restassured.response.Response;
import model.User;
import org.junit.Before;
import rest.RestSend;
import rest.RestUrl;

public class InitializationTests {

    public RestSend restSend;
    public User user;
    public Response responseRegistration;


    @Before
    public void init() {
        restSend = new RestSend();
        user = DataGeneratedUsers.generatorNewUser();
        responseRegistration = restSend.postRequestAndReturnResponse(user, RestUrl.REGISTER_USER);
    }
}
