package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    private Map<String, String> paymentData;
    private  List<Order> orders;
    private List<Product> products;

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
    }

    void fillVoucherPayment(){
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentSucessfulVoucher(){
        fillVoucherPayment();
        Payment payment1 = new Payment("aaaabbbb-1234-4321-2345-f32db8620155","",
                orders.get(1), paymentData);
        assertSame(this.orders.get(1), payment1.getOrder());
        assertNull(payment1.getPaymentData());
        assertEquals("aaaabbbb-1234-4321-2345-f32db8620155", payment1.getId());
        assertEquals("", payment1.getMethod());
    }

    @Test
    void testCreatePaymentFailVoucher(){
        Map<String, String> paymentDataVoucher = new  HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOPABCABCABCABCABCABC");
        assertThrows(IllegalArgumentException.class, ()-> {new Payment("aaaabbbb-1234-4321-2345-f32db8620155","VOUCHER",
                orders.get(1), paymentDataVoucher);
        });
    }
}