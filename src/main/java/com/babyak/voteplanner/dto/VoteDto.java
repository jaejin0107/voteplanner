package com.babyak.voteplanner.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VoteDto { //개별 투표 정보 담음
    private Long id;
    private String title;
    private LocalDate meetingDate;
    private String description;
}
