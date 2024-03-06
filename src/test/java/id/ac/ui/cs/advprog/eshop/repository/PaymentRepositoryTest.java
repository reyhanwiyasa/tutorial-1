package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();

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

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("aaaabbbb-1234-4321-2345-f32db8620155","",
                orders.get(1), paymentData);
        payments.add(payment1);

    }

    @Test
    void testAddPaymentSuccess(){
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentVoucherSuccess(){
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentDuplicatedId(){
        Payment payment1 = payments.get(0);
        Payment result = paymentRepository.save(payment1);

        Payment payment2 = new Payment(payment1.getId(), payment1.getMethod(), payment1.getOrder(), payment1.getPaymentData());

        assertThrows(IllegalStateException.class, ()->{
            paymentRepository.save(payment2);
        });
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(payments.getFirst().getId(), findResult.getId());
        assertSame(payments.getFirst().getPaymentData(), findResult.getPaymentData());
        assertSame(payments.getFirst().getOrder(), findResult.getOrder());
        assertEquals(payments.getFirst().getStatus(), findResult.getStatus());
        assertEquals(payments.getFirst().getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        assertNull(paymentRepository.findById("zczc"));
    }

    @Test
    void testGetAllPayments(){
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }
        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(1, result.size());
    }
}