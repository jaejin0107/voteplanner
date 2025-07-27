package com.babyak.voteplanner.service;

import com.babyak.voteplanner.domain.UserEntity;
import com.babyak.voteplanner.dto.SignupForm;
import com.babyak.voteplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * 회원가입 처리 로직을 담당하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$");

    public UserEntity register(SignupForm form) {
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (!form.isTermsAgree()) {
            throw new IllegalArgumentException("약관에 동의해야 가입할 수 있습니다.");
        }

        if (!PASSWORD_PATTERN.matcher(form.getPassword()).matches()) {
            throw new IllegalArgumentException("비밀번호는 영문+숫자 조합 8~16자여야 합니다.");
        }

        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        UserEntity user = new UserEntity();
        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword())); // 비밀번호 암호화

        return userRepository.save(user);
    }

    // 아이디/비밀번호 찾기용 메서드들 (향후 구현)
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 가입된 계정이 없습니다."));
    }
}