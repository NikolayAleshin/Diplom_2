package data;

import model.OrderRequest;

public class DataGeneratedOrder {

    public static OrderRequest generatorNewOrder() {

        String[] ingredients = {"61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f"};

        return new OrderRequest(ingredients);
    }

}