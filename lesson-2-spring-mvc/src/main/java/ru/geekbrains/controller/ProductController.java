package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.ProductRepository;

import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository ProductRepository;

    @GetMapping
    public String allProducts(Model model) throws SQLException {
        List<Product> allProducts = ProductRepository.getAllProduct();
        model.addAttribute("products", allProducts);
        return "products";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) throws SQLException {
        Product product = ProductRepository.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/create")
    public String createProduct(Model model) throws SQLException {
        model.addAttribute("product", new Product(0, "", 0));
        return "productCreate";
    }


    @PostMapping("/update")
    public String updateProduct(Product product) throws SQLException {
        ProductRepository.update(product);
        return "redirect:/product";
    }

    @PostMapping("/insert")
    public String insertProduct(Product product) throws SQLException {
        ProductRepository.insert(product);
        return "redirect:/product";
    }

    @PostMapping("/delete")
    public String deleteProducts(int id) throws SQLException {
        ProductRepository.delete(id);
        return "redirect:/product";
    }

}
