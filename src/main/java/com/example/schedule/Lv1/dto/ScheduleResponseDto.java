package com.example.schedule.Lv1.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class ScheduleResponseDto {
    //요청했을 때 응답할 데이터
    private Long id; //스케줄 id
    private String name; //사용자 이름
    private String title; //제목
    private String contents; //내용
    private LocalDateTime createDate; //작성일
    private LocalDateTime updateDate; //수정일

    //this 를 사용하여 객체 내부 생성자 및 메서드에서 해당 객체의 생성자 호출
    //필드 초기화
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
