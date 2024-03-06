package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import java.util.Map;

public class PaymentCOD extends Payment{
    public PaymentCOD(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
    }

    public PaymentCOD(String id, String method, Order order, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }
    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("address").isBlank() ||
                paymentData.get("deliveryFee").isBlank()){
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;
    }
}