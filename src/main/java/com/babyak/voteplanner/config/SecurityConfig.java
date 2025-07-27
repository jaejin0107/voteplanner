package com.babyak.voteplanner.config;
import com.babyak.voteplanner.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/signup", "/login", "/error", "/css/**", "/js/**",  "/images/**",  "/find/**").permitAll()
                        .anyRequest().authenticated() // URL 접근 권한 설정
                )
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 URL
                        .defaultSuccessUrl("/main", true) // 로그인 성공 시 이동할 URL
                        .failureUrl("/login?error")  // ← 실패 시 리다이렉트
                        .permitAll()
                )

                .oauth2Login(oauth -> oauth
                        .loginPage("/login")  // 커스텀 로그인 페이지
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();    //비번 암호화
    }
    @Bean
    public AuthenticationManager authenticationManager( //유저 조회, 인증 성공시 Authentication 객체 반환 → 세션/쿠키에 저장
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

