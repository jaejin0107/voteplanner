package com.babyak.voteplanner.service;
import com.babyak.voteplanner.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.babyak.voteplanner.domain.Vote;
import com.babyak.voteplanner.dto.VoteDto;
import com.babyak.voteplanner.repository.VoteRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService { // 투표 관련 데이터 처리

    private final VoteRepository voteRepository;

    public List<VoteDto> getOngoingVotesByUser(UserEntity user) {
        return voteRepository.findByUserAndDeadlineAfter(user, LocalDateTime.now())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<VoteDto> getCompletedVotesByUser(UserEntity user) {
        return voteRepository.findByUserAndDeadlineBefore(user, LocalDateTime.now())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private VoteDto toDto(Vote vote) {
        return new VoteDto(
                vote.getId(),
                vote.getTitle(),
                vote.getMeetingDate(),
                vote.getDescription()
        );
    }
}
