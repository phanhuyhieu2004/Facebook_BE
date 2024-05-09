package com.example.facebook_be.controller;

import com.example.facebook_be.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AccountController {
    @GetMapping("/login")
    public ModelAndView showFormLogin(){
        return new ModelAndView("/login","account",new Account());
    }
}
