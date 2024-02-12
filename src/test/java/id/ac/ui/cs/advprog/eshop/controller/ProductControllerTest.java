package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    Product product;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService mockService;


    @Test
    public void testHomePage() throws  Exception{
        mockMvc.perform(get(""))
                .andExpect(view().name("HomePage"));
    }
    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")));
    }

    @Test
    public void testCreateProductPost() throws Exception{
        String uniqueId = UUID.randomUUID().toString();
        mockMvc.perform(post("/product/create")
                .param("productName", "kerencoi")
                .param("productQuantity", "100")
                .param("productId", uniqueId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    public void testShowEditForm() throws Exception {
        String uniqueId = UUID.randomUUID().toString();
        Product testProduct = new Product();
        testProduct.setProductId(uniqueId);
        testProduct.setProductName("nge tes cuy");
        testProduct.setProductQuantity(100);

        when(mockService.findProductById(uniqueId)).thenReturn(testProduct);
        mockMvc.perform(get("/product/edit/{id}", uniqueId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit Your Product")))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", testProduct));
    }

    @Test
    public void testEditProduct() throws Exception{
        String uniqueId = UUID.randomUUID().toString();
        mockMvc.perform(post("/product/edit")
                                .param("productId", uniqueId)
                                .param("productName", "nge tes edit cuy")
                                .param("productQuantity", "100")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
        verify(mockService).edit(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws  Exception{
        String testProductId = UUID.randomUUID().toString();
        mockMvc.perform(post("/product/delete/{id}", testProductId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));
        verify(mockService).delete(testProductId);
    }

    @Test
    public void testProductListPage() throws Exception{
        // Given
        Product testProduct1 = new Product();
        String uniqueID1 = UUID.randomUUID().toString();
        String uniqueID2 = UUID.randomUUID().toString();
        Product testProduct2 = new Product();
        testProduct1.setProductName("Produk pertama");
        testProduct1.setProductQuantity(100);
        testProduct1.setProductId(uniqueID1);

        testProduct2.setProductName("Produk kedua");
        testProduct2.setProductQuantity(200);
        testProduct2.setProductId(uniqueID2);
        List<Product> expectedProducts = Arrays.asList(testProduct1, testProduct2);
        when(mockService.findAll()).thenReturn(expectedProducts);

        // When & Then
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(model().attributeExists("products")) // Verify the model contains an attribute named "products"
                .andExpect(model().attribute("products", hasSize(2))) // Verify the size of the products list
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("productId", is(uniqueID1)),
                                hasProperty("productName", is("Produk pertama")),
                                hasProperty("productQuantity", is(100))
                        )
                )))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("productId", is(uniqueID2)),
                                hasProperty("productName", is("Produk kedua")),
                                hasProperty("productQuantity", is(200))
                        )
                )));
    }

}
