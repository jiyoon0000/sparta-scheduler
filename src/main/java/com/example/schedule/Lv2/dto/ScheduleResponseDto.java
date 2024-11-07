package com.example.schedule.Lv2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class ScheduleResponseDto {
    //요청했을 때 응답할 데이터
    private Long id;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    //Schedule로 지정했어야 했는데, 둘 다 소문자로 하니까 헷갈린다.
    //this 를 사용하여 객체 내부 생성자 및 메서드에서 해당 객체의 생성자 호출
    public ScheduleResponseDto(
            Long id, String name, String title, String contents, LocalDateTime createDate, LocalDateTime updateDate
            ){
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
