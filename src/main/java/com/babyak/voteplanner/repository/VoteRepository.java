package com.babyak.voteplanner.repository;
import com.babyak.voteplanner.domain.UserEntity;
import com.babyak.voteplanner.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.createdBy = :user AND v.deadline > :now")
    List<Vote> findByUserAndDeadlineAfter(@Param("user") UserEntity user, @Param("now") LocalDateTime now);

    @Query("SELECT v FROM Vote v WHERE v.createdBy = :user AND v.deadline <= :now")
    List<Vote> findByUserAndDeadlineBefore(@Param("user") UserEntity user, @Param("now") LocalDateTime now);
}

