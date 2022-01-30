package model;

import java.util.List;

public class OrderGetResponse {

    private String name;

    private Boolean success;

    private String message;

    private Order order;

    private List<OrderRequest> orders;

    private String total;

    private String totalToday;

    public OrderGetResponse() {
    }

    public OrderGetResponse(String name, Boolean success, String message, Order order, List<OrderRequest> orders, String total, String totalToday) {
        this.name = name;
        this.success = success;
        this.message = message;
        this.order = order;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderRequest> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRequest> orders) {
        this.orders = orders;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(String totalToday) {
        this.totalToday = totalToday;
    }

}
