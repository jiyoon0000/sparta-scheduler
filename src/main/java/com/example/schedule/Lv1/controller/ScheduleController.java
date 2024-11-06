package com.example.schedule.Lv1.controller;

import com.example.schedule.Lv1.dto.ScheduleRequstDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;
import com.example.schedule.Lv1.repository.JdbcTemplateScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final JdbcTemplateScheduleRepository scheduleRepository;

    public ScheduleController(JdbcTemplateScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequstDto requstDto){
        ScheduleResponseDto responseDto = scheduleRepository.saveSchedule(requstDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
