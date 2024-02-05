package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        String uniqueId = UUID.randomUUID().toString();
        product.setProductId(uniqueId);
        productData.add(product);
        return product;
    }
    public void delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
    public void edit(Product editedProduct) {
        Product findProduct = findById(editedProduct.getProductId());
        if (findProduct != null) {
            findProduct.setProductName(editedProduct.getProductName());
            findProduct.setProductQuantity(editedProduct.getProductQuantity());
        }
    }
    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null); // Returns null if no product is found
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}