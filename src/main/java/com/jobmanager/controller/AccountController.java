package com.jobmanager.controller;

import com.jobmanager.entitiy.Account;
import com.jobmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping
    public String getAccountPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Account acc = accountService.findByUsername(username);
        model.addAttribute("account", acc);
        return "account_page";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Account acc = new Account();
        model.addAttribute("acc", acc);
        return "register_page";
    }

    @PostMapping("/register")
    public String registerAccount(@ModelAttribute Account acc) {
        String username = acc.getUsername();
        if (accountService.findByUsername(username) != null) {
            return "redirect:/account/register?user_error";
        }
        acc.setRole("USER");
        acc.setPassword(encoder.encode(acc.getPassword()));
        accountService.createAccount(acc);
        return "redirect:/login?register_success";
    }
}
