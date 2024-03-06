package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCODTest {
    List<Payment> payments;

    List<Order> orders;

    List<Product> products;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();

        Order order1 = new Order("edgcbaba-4a37-4664-83c7-f32db8620155", products ,100L, "Sudrijat");
        Order order2 = new Order("babaedgc-4a37-4664-83c7-f32db8620155", products ,100L, "Safira");
        Order order3 = new Order("aaaabbbb-4a37-4664-83c7-f32db8620155", products ,100L, "Andi");
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Test
    void testCreatePaymentCODSuccess(){
        Map<String, String> paymentDataCOD = new HashMap<>();
        paymentDataCOD.put("address", "rumah");
        paymentDataCOD.put("deliveryFee", "1");
        Payment payment1 = new PaymentCOD("aaaabbbb-1234-4321-2345-f32db8620155",orders.getFirst(), "COD", paymentDataCOD);
        assertSame(this.orders.getFirst(), payment1.getOrder());
        assertEquals(paymentDataCOD, payment1.getPaymentData());
        assertEquals("aaaabbbb-1234-4321-2345-f32db8620155", payment1.getId());
        assertEquals("COD", payment1.getMethod());
    }

    @Test
    void testCreatePaymentFailAddress(){
        Map<String, String> paymentDataCOD = new HashMap<>();
        paymentDataCOD.put("address", "");
        paymentDataCOD.put("deliveryFee", "1");
        assertThrows(IllegalArgumentException.class, ()-> {new PaymentCOD("aaaabbbb-1234-4321-2345-f32db8620155",orders.getFirst(),
                "COD", paymentDataCOD);
        });
    }

    @Test
    void testCreatePaymentFailDeliveryFee(){
        Map<String, String> paymentDataCOD = new HashMap<>();
        paymentDataCOD.put("address", "rumah");
        paymentDataCOD.put("deliveryFee", "");
        assertThrows(IllegalArgumentException.class, ()-> {new PaymentCOD("aaaabbbb-1234-4321-2345-f32db8620155",orders.get(1),
                "COD", paymentDataCOD);
        });
    }
}