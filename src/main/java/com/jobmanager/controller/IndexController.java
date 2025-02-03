package com.jobmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Redirects the root request to /login
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }
}
