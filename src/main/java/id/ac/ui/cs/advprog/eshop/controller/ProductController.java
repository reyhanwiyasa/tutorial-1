package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("")
    public String homePage(Model model){
        return "homepage";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createproduct";
    }
    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:list";
    }
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable("id") String productId, Model model) {
        Product product = service.findProductById(productId);
        model.addAttribute("product", product);
        return "editproduct";
    }
    @PostMapping("/product/edit")
    public String editProduct(@ModelAttribute Product product){
        service.edit(product);
        return "redirect:list";
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") String productId){
        service.delete(productId);
        return "redirect:../list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productlist";
    }
}