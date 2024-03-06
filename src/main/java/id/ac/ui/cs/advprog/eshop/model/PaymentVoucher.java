package id.ac.ui.cs.advprog.eshop.model;



import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

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
        int numOfNumerics = 0;
        for (int i=0; i<paymentData.get("voucherCode").length(); i++){
            if (Character.isDigit(paymentData.get("voucherCode").charAt(i))){
                numOfNumerics+=1;
            }
        }
        if (paymentData.get("voucherCode").length()!=16 ||
                !paymentData.get("voucherCode").startsWith("ESHOP") ||
                numOfNumerics!=8){
            this.status = PaymentStatus.REJECTED.getValue();
            throw new IllegalArgumentException();
        }
        this.paymentData=paymentData;
    }
}