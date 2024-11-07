package com.example.schedule.Lv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ScheduleReqeustDto {
    //요청할 데이터 (사용자가 입력할 데이터)
    private String name;
    //문제 조건에 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 비밀번호 제외해야 한다고 적혀있어서 주석처리
//    private String password;
    private String title;
    private String contents;
}
