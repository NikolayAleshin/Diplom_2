import data.DataGeneratedOrder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderRequest;
import model.OrderResponse;
import model.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import rest.RestUrl;

public class CreateOrderTests extends InitializationTests {

    @Test
    @DisplayName("Check new correct order data authorization of /api/orders")
    @Description("Basic success test for /api/orders endpoint")
    public void newOrderWithIngredientsUserAuthorizationTest(){

        Response responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
        UserResponse userResponseAuthorization = responseAuthorization.getBody().as(UserResponse.class);

        OrderRequest orderRequest = DataGeneratedOrder.generatorNewOrder();
        Response responseNewOrder = restSend.postRequestAndReturnResponse(orderRequest, RestUrl.ORDER, userResponseAuthorization.getAccessToken());
        OrderResponse orderResponse = responseNewOrder.getBody().as(OrderResponse.class);

        Assert.assertEquals(200, responseNewOrder.getStatusCode());
        Assert.assertTrue(orderResponse.getSuccess());
        Assert.assertFalse(orderResponse.getName().isEmpty());
        Assert.assertNotNull(orderResponse.getOrder().getNumber());
    }

    @Test
    @DisplayName("Check without Ingredients order data authorization of /api/orders")
    @Description("Basic test for /api/orders endpoint")
    public void newOrderWithoutIngredientsUserAuthorizationTest(){

        Response responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
        UserResponse userResponseAuthorization = responseAuthorization.getBody().as(UserResponse.class);

        OrderRequest orderRequest = new OrderRequest();
        Response responseNewOrder = restSend.postRequestAndReturnResponse(orderRequest, RestUrl.ORDER, userResponseAuthorization.getAccessToken());
        OrderResponse orderResponse = responseNewOrder.getBody().as(OrderResponse.class);

        Assert.assertEquals(400, responseNewOrder.getStatusCode());
        Assert.assertFalse(orderResponse.getSuccess());
        Assert.assertEquals("Ingredient ids must be provided", orderResponse.getMessage());
    }

    @Test
    @DisplayName("Check Ingredients not corrected hash order data authorization of /api/orders")
    @Description("Basic test for /api/orders endpoint")
    public void newOrderNotCorrectedIngredientsUserAuthorizationTest(){

        Response responseAuthorization = restSend.postRequestAndReturnResponse(user, RestUrl.AUTHORIZATION_USER);
        UserResponse userResponseAuthorization = responseAuthorization.getBody().as(UserResponse.class);

        OrderRequest orderRequest = new OrderRequest(new String[]{"test123"});
        Response responseNewOrder = restSend.postRequestAndReturnResponse(orderRequest, RestUrl.ORDER, userResponseAuthorization.getAccessToken());

        Assert.assertEquals(500, responseNewOrder.getStatusCode());
    }

    @Test
    @DisplayName("Check with Ingredients order data not authorization of /api/orders")
    @Description("Basic test for /api/orders endpoint")
    public void newOrderWithIngredientsUserNotAuthorizationTest(){

        OrderRequest orderRequest = DataGeneratedOrder.generatorNewOrder();
        Response responseNewOrder = restSend.postRequestAndReturnResponse(orderRequest, RestUrl.ORDER);
        OrderResponse orderResponse = responseNewOrder.getBody().as(OrderResponse.class);

        Assert.assertEquals(401, responseNewOrder.getStatusCode());
        Assert.assertFalse(orderResponse.getSuccess());
        Assert.assertEquals("You should be authorised", orderResponse.getMessage());
    }
}