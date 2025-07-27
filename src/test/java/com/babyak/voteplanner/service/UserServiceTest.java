package com.babyak.voteplanner.service;
import com.babyak.voteplanner.domain.UserEntity;
import com.babyak.voteplanner.dto.SignupForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;

    @Test
    void 회원가입_정상() {
        SignupForm form = new SignupForm();
        form.setName("테스트");
        form.setEmail("test@example.com");
        form.setPassword("1234");
        form.setPasswordConfirm("1234");
        form.setTermsAgree(true);

        UserEntity saved = userService.register(form);

        assertEquals("테스트", saved.getName());
        assertEquals("test@example.com", saved.getEmail());
    }
}