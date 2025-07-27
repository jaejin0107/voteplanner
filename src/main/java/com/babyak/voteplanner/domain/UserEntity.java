package com.babyak.voteplanner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원 정보를 저장하는 JPA 엔티티 클래스
 */
@Entity
@Table(name = "users") // user는 DB 예약어이므로 테이블명 변경
@Getter @Setter
@NoArgsConstructor
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false, unique = true)
    private String username; // 아이디

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER; // 기본 사용자 권한

    public enum Role {
        USER, ADMIN
    }
}