package com.example.temp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long memberId; //기본키
    private String name; //유저 이름
    private String email; //유저 구글 이메일
    private String token; //유저 프로필 사진
}

