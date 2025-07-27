package com.babyak.voteplanner.controller;

import com.babyak.voteplanner.dto.MainPageResponse;
import com.babyak.voteplanner.dto.VoteDto;
import com.babyak.voteplanner.domain.UserEntity;
import com.babyak.voteplanner.service.LoginUserDetails;
import com.babyak.voteplanner.service.UserService;
import com.babyak.voteplanner.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final VoteService voteService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<MainPageResponse> getMainPage(@AuthenticationPrincipal LoginUserDetails loginUser) {
        UserEntity user = loginUser.getUser();  // 로그인한 사용자 정보 가져오기

        List<VoteDto> ongoingVotes = voteService.getOngoingVotesByUser(user);
        List<VoteDto> completedVotes = voteService.getCompletedVotesByUser(user);

        MainPageResponse response = new MainPageResponse(user.getName(), ongoingVotes, completedVotes);
        return ResponseEntity.ok(response);
    }
}

