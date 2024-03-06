package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentVoucherTest {
    Map<String, String> paymentData;
    private  List<Order> orders;
    List<Product> products;
    @BeforeEach
    void setup() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(1);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(2);
        products.add(product2);

        this.orders = new ArrayList<>();

        Order order1 = new Order("edgcbaba-4a37-4664-83c7-f32db8620155", products ,100L, "Sudrijat");
        Order order2 = new Order("babaedgc-4a37-4664-83c7-f32db8620155", products ,100L, "Safira");
        Order order3 = new Order("aaaabbbb-4a37-4664-83c7-f32db8620155", products ,100L, "Budi");
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        this.paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }
    @Test
    void testCreateVoucherPaymentPendingStatus() {
        Payment payment = new PaymentVoucher(
                "gds313vc-4de2-1fsa-xas2-af23as4w12sa",
                "VOUCHER",
                orders.get(1),
                paymentData
        );

        assertEquals("gds313vc-4de2-1fsa-xas2-af23as4w12sa", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertSame(orders.get(1), payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentSuccessStatus() {
        Payment payment = new PaymentVoucher(
                "gds313vc-4de2-1fsa-xas2-af23as4w12sa",
                "VOUCHER",
                orders.get(1),
                paymentData,
                PaymentStatus.SUCCESS.getValue()
        );
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(1), payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("gds313vc-4de2-1fsa-xas2-af23as4w12sa", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new PaymentVoucher(
                    "gds313vc-4de2-1fsa-xas2-af23as4w12sa",
                    "VOUCHER",
                    orders.get(1),
                    paymentData,
                    "MEOW"
            );
            payment.setStatus("MEOW");
        });
    }
}
