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

/**
 * Handles requests related to creating an account (registration)
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * This functions returns the account page of the user.
     * Showing details of that account.
     *
     * @param model       Needed send data to display / template
     * @param userDetails Enables access to the user who is currently logged in.
     * @return String which indicates the name of the template to sent to the client.
     */
    @GetMapping
    public String getAccountPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Account acc = accountService.findByUsername(username);
        model.addAttribute("account", acc);
        return "account_page";
    }

    /**
     * Handles the request to return the registration page.
     *
     * @param model to send data to display
     * @return String indicating the template
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Account acc = new Account();
        model.addAttribute("acc", acc);
        return "register_page";
    }

    /**
     * This functions creates a new account. If a username
     * is already taken redirect with an error parameter.
     * If unique, redirect to login page with success parameter
     *
     * @param acc Account object serialized from the submitted form.
     * @return String indicating the template to return.
     */
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
