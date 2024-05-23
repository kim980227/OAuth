package com.example.temp.repository;

import com.example.temp.entity.TokenBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBoardRepository extends JpaRepository<TokenBoard, Long> {
    Optional<TokenBoard> findByMemberEmailAndMemberId(String email,Long memberId);
}

