package com.example.schedule.Lv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ScheduleRequstDto {
    //요청할 데이터
    private String name;
    private String password;
    private String title;
    private String contents;
}
