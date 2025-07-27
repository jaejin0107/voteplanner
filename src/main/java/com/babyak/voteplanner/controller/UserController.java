package com.babyak.voteplanner.controller;

import com.babyak.voteplanner.dto.SignupForm;
import com.babyak.voteplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 회원가입 페이지 처리용 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 페이지 렌더링
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // templates/login.html
    }

    // 회원가입 폼 페이지 렌더링
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    // 회원가입 제출 처리
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute SignupForm signupForm, Model model) {
        try {
            userService.register(signupForm);
            return "redirect:/login"; // 가입 성공 후 로그인 페이지로 이동
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
}
