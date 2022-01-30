package model;

public class OrderRequest {

    private String[] ingredients;

    public OrderRequest(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public OrderRequest() {
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}