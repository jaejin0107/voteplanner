package com.babyak.voteplanner.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MainPageResponse { //메인화면 응답 데이터 포맷
    private String userName;
    private List<VoteDto> ongoingVotes;
    private List<VoteDto> completedVotes;
}
