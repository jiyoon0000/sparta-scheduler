package com.example.schedule.Lv1.dto;

import com.example.schedule.Lv1.entity.schedule;
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
    public ScheduleResponseDto(schedule schedule){
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.title = schedule.getSchedule();
        this.contents = schedule.getContents();
        this.createDate = schedule.getCreateDate();
        this.updateDate = schedule.getUpdateDate();
    }
}
