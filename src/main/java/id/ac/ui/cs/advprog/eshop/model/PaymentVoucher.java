package id.ac.ui.cs.advprog.eshop.model;



import java.util.Map;

public class PaymentVoucher extends  Payment{
    public PaymentVoucher(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
    }

    public PaymentVoucher(String id, String method, Order order, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }
    @Override
    protected void setPaymentData(Map<String, String> paymentData) {

    }
}