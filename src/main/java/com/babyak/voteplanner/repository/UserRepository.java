package com.babyak.voteplanner.repository;

import com.babyak.voteplanner.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA를 통해 DB에 접근하기 위한 사용자 리포지토리
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username); // 로그인용
    Optional<UserEntity> findByEmail(String email);       // 아이디/비밀번호 찾기용
    boolean existsByUsername(String username); // 아이디 중복 체크용
    boolean existsByEmail(String email); // 이메일 중복 체크용
}
