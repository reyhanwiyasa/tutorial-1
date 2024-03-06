package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;


import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        this.status=PaymentStatus.PENDING.getValue();
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, "PENDING");
    }

    public void setStatus(String status) {
        if(PaymentStatus.contains(status)){
            this.status=status;
        }else{
            throw new IllegalArgumentException();
        }
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