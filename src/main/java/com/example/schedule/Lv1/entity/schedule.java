package com.example.schedule.Lv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter //데이터 읽어오기
@AllArgsConstructor //생성자

public class schedule {
    //schedule 에서 필요한 데이터
    private Long id; //고유 식별자
    private String name;
    private int password;
    private String title;
    private String contents;
    //작성일, 수정일 저장하기 위해 LocalDateTime 사용
    //LocalDateTime : 날짜와 시간을 모두 포함, LocalDate+LocalTime
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
