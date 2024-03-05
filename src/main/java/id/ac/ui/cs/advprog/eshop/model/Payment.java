package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, "PENDING");
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED") || status.equals("PENDING")) {
            this.status = status;
            return;
        }
        throw new IllegalArgumentException("Invalid payment status");
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        this.order = order;
    }

    protected void setPaymentData(Map<String, String> paymentData) {
        if(method.equals("")){
            this.paymentData = null;
            return;
        }
        throw new IllegalArgumentException();
    }
}