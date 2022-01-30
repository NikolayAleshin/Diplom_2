package rest;

public enum RestUrl {

    BASE_URL("https://stellarburgers.nomoreparties.site"),
    REGISTER_USER("/api/auth/register"),
    AUTHORIZATION_COURIER("/api/v1/courier/login"),
    ORDER_URL("/api/v1/orders"),
    DELETE_COURIER_URL("/api/v1/courier/");

    private final String id;

    RestUrl(String s) {
        this.id = s;
    }

    public String getId() {
        return id;
    }
}
