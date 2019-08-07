package controller;

import model.Code;
import model.Orders;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import service.BasketService;
import service.CodeService;
import service.MailService;
import service.OrderService;
import service.ProductService;
import util.Generator;

@Controller
@RequestMapping("/user")
public class UserController {

    private ProductService productService;
    private BasketService basketService;
    private OrderService orderService;
    private CodeService codeService;
    private MailService mailService;

    @Autowired
    public UserController(ProductService productService, BasketService basketService,
                          OrderService orderService, CodeService codeService,
                          MailService mailService) {
        this.productService = productService;
        this.basketService = basketService;
        this.orderService = orderService;
        this.codeService = codeService;
        this.mailService = mailService;
    }

    @GetMapping("/products")
    String allProduct(@SessionAttribute("user") User user, Model model) {
        model.addAttribute("productDatabase", productService.getAll());
        model.addAttribute("box",basketService.getCountOfElements(user));
        return "buy_product";
    }

    @GetMapping("/add_to_box")
    String addToBox(@RequestParam int id, Model model, @SessionAttribute("user") User user) {
        Product product = productService.getById(id).get();
        basketService.addProduct(user, product);
        model.addAttribute("box", basketService.getCountOfElements(user));
        model.addAttribute("productDatabase", productService.getAll());
        return "buy_product";
    }

    @GetMapping("/buy")
    String buyProduct(Model model) {
        return "form_to_confirm_order";
    }

    @PostMapping("/buy")
    String buyProducts(@ModelAttribute("order") Orders order, Model model,
                       @SessionAttribute("user") User user) {
        order.setUser(user);
        orderService.createOrder(order);
        Code code = new Code(Generator.getVerificationCode(), order);
        codeService.add(code);
        mailService.send(user.getEmail(), String.valueOf(code.getCode()));
        return "confirm_password";
    }

    @GetMapping("/confirm")
    String confirmOrder(@SessionAttribute("user") User user,
                        @RequestParam("code") String code, Model model) {
        int code1 = codeService.getCode(orderService.getOrderUser(user).get());
        if (String.valueOf(code1).equals(code)) {
            model.addAttribute("errorMsg", "success");
        } else {
            model.addAttribute("errorMsg", "incorrect code");
        }
        return "confirm_password";
    }
}
