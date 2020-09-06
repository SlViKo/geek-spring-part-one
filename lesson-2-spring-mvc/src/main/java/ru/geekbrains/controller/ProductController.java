package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.ProductRepository;

import javax.validation.Valid;
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
        Product product = new Product();
        model.addAttribute("product", product);
        return "product";
    }


    @PostMapping("/update")
    public String updateProduct(@Valid Product product, BindingResult bindingResult) throws SQLException {
        if(bindingResult.hasErrors()) {
            return "product";
        }

        if(product.getId() == 0) {
            ProductRepository.insert(product);
        } else {
            ProductRepository.update(product);
        }
        return "redirect:/product";
    }

    @PostMapping("/insert")
    public String insertProduct(Product product) throws SQLException {
        ProductRepository.insert(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProducts(@PathVariable("id") int idProduct) throws SQLException {
        ProductRepository.delete(idProduct);
        return "redirect:/product";
    }

}
