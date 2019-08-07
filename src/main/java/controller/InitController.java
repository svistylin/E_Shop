package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.MailService;
import service.UserService;
import util.PasswordHashGenerator;

import java.util.Locale;
import java.util.Optional;

@Controller
@SessionAttributes("user")
public class InitController {

    private UserService userService;
    private MailService mailService;

    public InitController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @Autowired
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("/")
    public String userForm(Locale locale, Model model) {
        return "index";
    }

    @GetMapping("/asd")
    public String asd(Locale locale, Model model) {
        mailService.send("misha.krechkivskiy0982@gmail.com", "12345");
        return "index";
    }

    @PostMapping("/signin")
    public String saveUser(@ModelAttribute("user") User userModel, Model model) {
        String password = userModel.getPassword();
        Optional<User> check = userService.check(userModel);
        if (check.isPresent()) {
            User user1 = check.get();
            String salt = userModel.getSalt();
            String code = PasswordHashGenerator.getCode(salt, password);
            userModel.setId(user1.getId());
            userModel.setRole(user1.getRole());
            if (user1.getPassword().equals(code)) {
                if (user1.getRole().equals("admin")) {
                    return "redirect:/admin/users";
                } else {
                    return "redirect:/user/products";
                }
            }
        }
        return "redirect:/";
    }
}
