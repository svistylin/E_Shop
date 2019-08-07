package controller;

import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/users")
    String getm(Model model) {
        model.addAttribute("userDatabase", userService.getAllUsers());
        return "page_to_save";
    }

    @GetMapping("/register")
    String register(Model model) {
        return "registration";
    }

    @PostMapping("/register")
    String registerUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        model.addAttribute("userDatabase", userService.getAllUsers());
        return "page_to_save";
    }

    @GetMapping("/remove")
    String removeUser(@RequestParam("id") int id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("userDatabase", userService.getAllUsers());
        return "page_to_change_user";
    }

    @GetMapping("/edit")
    String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        return "page_to_change_user";
    }

    @PostMapping("/edit")
    String saveNewUserData(@ModelAttribute("user") User user, Model model) {
        userService.edit(user);
        model.addAttribute("userDatabase", userService.getAllUsers());
        return "page_to_save";
    }

    @GetMapping("/addProduct")
    String products(Model model) {
        model.addAttribute("productDatabase", productService.getAll());
        return "product";
    }

    @PostMapping("/addProduct")
    String addProduct(@ModelAttribute("product") Product product, Model model) {
        productService.addProduct(product);
        model.addAttribute("productDatabase", productService.getAll());
        return "product";
    }

    @GetMapping("/edit_product")
    String editProduc(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        return "page_to_change_product";
    }

    @PostMapping("/edit_product")
    String editProduct(@ModelAttribute("product") Product product, Model model) {
        productService.edit(product);
        model.addAttribute("productDatabase", productService.getAll());
        return "product";
    }

    @GetMapping("/delete_product")
    String deleteProduct(@RequestParam("id") int id, Model model) {
        productService.deleteProduct(id);
        model.addAttribute("productDatabase", productService.getAll());
        return "product";
    }
}
