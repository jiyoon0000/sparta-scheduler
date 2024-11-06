package com.example.schedule.Lv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class schedule {
    private long id;
    private String name;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
