package com.example.facebook_be.controller.admin;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.service.admin.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/facebook")
public class loginController {
    @Autowired
    private LoginService loginService;

    @GetMapping()
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirect, @RequestParam String identifier, @RequestParam String password) {
        Account user = loginService.login(identifier, password);
        if (user != null) {
            if (user.getRole() == 1) {
                redirect.addFlashAttribute(
                        "message", "Đăng nhập thành công với tư cách là admin.");
                return "redirect:/facebook/homeAdmin";
            } else {
                redirect.addFlashAttribute(
                        "message", "Đăng nhập thành công.");
                return "redirect:/facebook/homeUser";
            }
        } else {
            redirect.addFlashAttribute(
                    "message", "Tên người dùng hoặc mật khẩu không đúng. Vui lòng thử lại.");
            return "redirect:/facebook";
        }
    }
    @GetMapping("/homeAdmin")
    public ModelAndView listCustomer() {
        Iterable<Account> users = loginService.findAllByRoleNot(1);
        ModelAndView modelAndView = new ModelAndView("/homeAdmin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }


}