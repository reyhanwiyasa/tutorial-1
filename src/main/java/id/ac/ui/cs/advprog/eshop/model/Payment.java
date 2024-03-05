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

    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {

    }

    public void setStatus(String status) {
    }

    private void setOrder(Order order) {
    }

    protected void setPaymentData(Map<String, String> paymentData) {
    }
}