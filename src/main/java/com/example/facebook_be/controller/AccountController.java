package com.example.facebook_be.controller;

import com.example.facebook_be.dto.AccountRequest;
import com.example.facebook_be.dto.PaginateRequest;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/facebook")
@SessionAttributes("account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping()
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/homeAdmin")

    public ModelAndView ShowList(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                 @RequestParam(name = "size", defaultValue = "5", required = false) int size,
                                 @RequestParam(name = "username", required = false) String username,
                                 @RequestParam(name = "birthday", required = false) String birthday,HttpSession session
    ) {
        Page<Account> accountPage = accountService.findAll(  new AccountRequest(username,birthday),new PaginateRequest(page, size));
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getRole() == 0) {
        ModelAndView modelAndView = new ModelAndView("homeAdmin");
        modelAndView.addObject("pageBegin", Math.max(1, page));
        modelAndView.addObject("pageEnd", Math.min(page + 2, accountPage.getTotalPages()));

        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("size", size);
            modelAndView.addObject("account", account);
        modelAndView.addObject("accounts", accountPage.getContent());


        modelAndView.addObject("totalPages", accountPage.getTotalPages());

        return modelAndView;
  }else{
            return new ModelAndView("redirect:/facebook");

        }}
    @PostMapping("/login")
    public String login(HttpSession session, RedirectAttributes redirect, @RequestParam String identifier, @RequestParam String password) {
        Account account = accountService.login(identifier, password);
        if (account != null) {
            session.setAttribute("account", account);


            if (account.getRole() == 0) {

                return "redirect:/facebook/homeAdmin";
            } else {
                redirect.addFlashAttribute(
                        "message", "Login unsuccessful.");
                return "redirect:/facebook";
            }
        } else {
            redirect.addFlashAttribute(
                    "message", "Incorrect phone number or email or password!");
            return "redirect:/facebook";
        }
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
    @GetMapping("/block/{accountId}")
    public String blockAccount(@PathVariable Long accountId, RedirectAttributes redirectAttributes) {

            accountService.blockAccount(accountId);
            redirectAttributes.addFlashAttribute("message", "Account blocked successfully");

        return "redirect:/facebook/homeAdmin";
    }
}
