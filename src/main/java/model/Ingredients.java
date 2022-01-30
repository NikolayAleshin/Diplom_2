package model;

public class Ingredients {

    private String _id;

    public Ingredients(String _id) {
        this._id = _id;
    }

    public Ingredients() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}