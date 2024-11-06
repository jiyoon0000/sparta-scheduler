package com.example.schedule.Lv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter //데이터 읽어오기
@AllArgsConstructor //생성자

public class schedule {
    //schedule 에서 필요한 데이터
    private long id;
    private String name;
    private String password;
    private String title;
    private String contents;
    //작성일, 수정일 저장하기 위해 LocalDateTime 사용
    //LocalDateTime : 날짜와 시간을 모두 포함, LocalDate+LocalTime
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
