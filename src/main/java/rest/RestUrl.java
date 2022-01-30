package rest;

public enum RestUrl {

    BASE_URL("https://stellarburgers.nomoreparties.site"),
    REGISTER_USER("/api/auth/register"),
    AUTHORIZATION_USER("/api/auth/login"),
    EDIT_DATA_USER("/api/auth/user"),
    ORDER("/api/orders");

    private final String id;

    RestUrl(String s) {
        this.id = s;
    }

    public String getId() {
        return id;
    }
}
