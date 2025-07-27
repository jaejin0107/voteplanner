package com.babyak.voteplanner.controller;

import com.babyak.voteplanner.service.LoginUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("user", userDetails.getUser());
        }
        return "main";
    }
}


