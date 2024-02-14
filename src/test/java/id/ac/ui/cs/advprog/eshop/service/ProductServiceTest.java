package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TestCreateProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(100);
        product.setProductId(UUID.randomUUID().toString());

        when(productRepository.create(any(Product.class))).thenReturn(product);
        Product createdProduct = service.create(product);

        verify(productRepository).create(product);
        assertEquals(product, createdProduct);
    }

    @Test
    public void TestDeleteProduct() {
        String productId = UUID.randomUUID().toString();
        service.delete(productId);
        verify(productRepository).delete(eq(productId));
    }

    @Test
    public void TestEditProduct() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Test Product");
        product.setProductQuantity(100);

        service.edit(product);
        verify(productRepository).edit(any(Product.class));
    }

    @Test
    public void TestFindProductById() {
        String productId = UUID.randomUUID().toString();
        Product expectedProduct = new Product();
        expectedProduct.setProductId(productId);
        expectedProduct.setProductName("Test Product");
        expectedProduct.setProductQuantity(100);

        when(productRepository.findById(productId)).thenReturn(expectedProduct);
        Product actualProduct = service.findProductById(productId);

        assertEquals(expectedProduct, actualProduct);
        verify(productRepository).findById(productId);
    }

    @Test
    public void TestFindAll() {

        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId(UUID.randomUUID().toString());
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);

        List<Product> expectedProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(expectedProducts.iterator());
        List<Product> actualProducts = service.findAll();

        assertEquals(expectedProducts.size(), actualProducts.size());
        assertTrue(actualProducts.containsAll(expectedProducts));
        verify(productRepository).findAll();
    }
}
