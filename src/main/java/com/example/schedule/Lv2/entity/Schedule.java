package com.example.schedule.Lv2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter //데이터 읽어오기
@AllArgsConstructor //생성자
@Setter
@NoArgsConstructor
public class Schedule {
    //schedule 에서 필요한 데이터
    private Long id; //생성자
    private String name; //시용자 이름
    private int password; //사용자 비밀번호
    private String title; //스케줄 제목
    private String contents; //스케줄 내용
    //작성일, 수정일 저장하기 위해 LocalDateTime 사용
    //LocalDateTime : 날짜와 시간을 모두 포함, LocalDate+LocalTime
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
