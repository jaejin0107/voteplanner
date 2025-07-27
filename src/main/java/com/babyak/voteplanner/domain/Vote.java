package com.babyak.voteplanner.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate meetingDate;

    private LocalDateTime createdAt;

    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity createdBy; // 비회원이면 null 허용

}
