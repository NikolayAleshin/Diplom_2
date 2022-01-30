import data.DataGeneratedOrder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderGetResponse;
import model.OrderRequest;
import model.OrderResponse;
import model.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import rest.RestUrl;

public class GetOrderTests extends InitializationTests {

    @Test
    @DisplayName("Check get without order authorization of /api/orders")
    @Description("Basic success test for /api/orders endpoint")
    public void getOrderWithoutOrderAuthorizationTest(){

        Response responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
        UserResponse userResponseAuthorization = responseAuthorization.getBody().as(UserResponse.class);

        Response responseOrder = restSend.getRequestAndReturnResponse(RestUrl.ORDER, userResponseAuthorization.getAccessToken());
        OrderResponse orderResponse = responseOrder.getBody().as(OrderResponse.class);

        Assert.assertEquals(200, responseOrder.getStatusCode());
        Assert.assertTrue(orderResponse.getSuccess());
        Assert.assertTrue(orderResponse.getOrders().isEmpty());
    }

    @Test
    @DisplayName("Check get order authorization of /api/orders")
    @Description("Basic success test for /api/orders endpoint")
    public void getOrderOrderAuthorizationTest(){

        Response responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
        UserResponse userResponseAuthorization = responseAuthorization.getBody().as(UserResponse.class);

        OrderRequest orderRequest = DataGeneratedOrder.generatorNewOrder();
        restSend.postRequestAndReturnResponse(orderRequest, RestUrl.ORDER, userResponseAuthorization.getAccessToken());

        Response responseOrder = restSend.getRequestAndReturnResponse(RestUrl.ORDER, userResponseAuthorization.getAccessToken());
        OrderGetResponse orderResponse = responseOrder.getBody().as(OrderGetResponse.class);

        Assert.assertEquals(200, responseOrder.getStatusCode());
        Assert.assertTrue(orderResponse.getSuccess());
        Assert.assertEquals(1, orderResponse.getOrders().size());
    }

    @Test
    @DisplayName("Check get order without authorization of /api/orders")
    @Description("Basic success test for /api/orders endpoint")
    public void getOrderOrderNotAuthorizationTest(){

        Response response = restSend.getRequestAndReturnResponse(RestUrl.ORDER);

        OrderGetResponse orderResponse = response.getBody().as(OrderGetResponse.class);

        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertFalse(orderResponse.getSuccess());
        Assert.assertEquals("You should be authorised", orderResponse.getMessage());
    }
}