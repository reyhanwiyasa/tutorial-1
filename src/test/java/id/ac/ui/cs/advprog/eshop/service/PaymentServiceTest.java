package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Spy
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp(){
        orders = new ArrayList<>();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order);

        payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP00000000AAA");
        Payment payment1 = new Payment("aaaabbbb-1234-4321-2345-f32db8620155","",
                orders.getFirst(), paymentData);
        payments.add(payment1);

    }

    @Test
    void testAddPayment(){
        Payment payment1 = payments.getFirst();
        doReturn(payment1).when(paymentRepository).save(any(Payment.class));
        payment1 = paymentService.addPayment(payment1.getOrder(), "", payment1.getPaymentData());

        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment findResult = paymentService.getPayment(payment1.getId());

        assertEquals(payment1.getId(),findResult.getId() );
        assertEquals(payment1.getMethod(), findResult.getMethod() );
        assertEquals(payment1.getStatus(), findResult.getStatus() );
    }

    @Test
    void testSetStatusSuccessful(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP00000000AAA");
        Payment payment1 = new Payment("aaaabbbb-1234-4321-2345-f32db8620155","",
                orders.getFirst(), paymentData);

        assertEquals(PaymentStatus.PENDING.getValue(),payment1.getStatus());
        paymentService.setStatus(payment1, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment1.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment1.getOrder().getStatus());

        paymentService.setStatus(payment1, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(),payment1.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment1.getOrder().getStatus());
    }

    @Test
    void testSetStatusFail(){

        Payment payment1 = payments.getFirst();
        assertThrows(IllegalArgumentException.class, ()->
                paymentService.setStatus(payment1, "zczc")
        );
    }

    @Test
    void testGetPaymentIfFound(){
        Payment payment1 = payments.getFirst();
        payment1.setStatus(PaymentStatus.PENDING.getValue());
        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment paymentFound = paymentService.getPayment(payment1.getId());
        assertEquals(payment1.getId(), paymentFound.getId());
        assertEquals("",paymentFound.getMethod());
        assertEquals(payment1.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGetPaymentIfNotFound(){
        doReturn(null).when(paymentRepository).findById("zczc");
        Payment payment = paymentService.getPayment("zczc");
        assertNull(payment);
    }

    @Test
    void testGetAllPayment(){
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> payment = paymentService.getAllPayment();
        assertSame(payments,payment);
    }
}