package com.example.facebook_be.controller.admin;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.service.admin.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect, @RequestParam String identifier, @RequestParam String password) {
        Account user = loginService.login(identifier, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            Cookie cookie = new Cookie("user", identifier);
            cookie.setMaxAge(7 * 24 * 60 * 60); // set cookie to expire in 7 days
            response.addCookie(cookie);

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
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/facebook";
    }



    @PostMapping("/create")
    public String registerNewAccount(@RequestBody Account account, RedirectAttributes redirect) {
        try {
            loginService.registerAccount(account);
            redirect.addFlashAttribute("message", "Tạo tài khoản thành công.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/facebook/homeAdmin";
    }
}