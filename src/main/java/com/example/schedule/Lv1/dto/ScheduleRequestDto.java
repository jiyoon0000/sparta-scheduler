package com.example.schedule.Lv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //모든 필드를 받는 생성자 생성

public class ScheduleRequestDto {
    //요청할 데이터 (사용자가 입력할 데이터)
    private String name; // 사용자 이름
    private int password; // 비밀번호(요청 데이터에만 사용)
    private String title; // 스케줄 제목
    private String contents; // 스케줄 내용
}
