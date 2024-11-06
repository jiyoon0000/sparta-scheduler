package com.example.schedule.Lv1.dto;

import com.example.schedule.Lv1.entity.schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    //요청했을 때 응답할 데이터
    private long id;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public ScheduleResponseDto(schedule schedule){
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.title = schedule.getSchedule();
        this.contents = schedule.getContents();
        this.createDate = schedule.getCreateDate();
        this.updateDate = schedule.getUpdateDate();
    }
}
