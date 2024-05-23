package com.example.temp.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class TokenBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    private Long memberId;
    private String memberEmail;
    private String token;

    @Builder
    public TokenBoard(Long id, Long memberId, String memberEmail, String token) {
        this.id = id;
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.token = token;
    }
}
