package com.example.schedule.Lv2.controller;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    //스케줄 생성하기
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        if (requestDto == null) {
            return ResponseEntity.badRequest().body(null);
        }
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    //전체 스케줄 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> updateDate
    ){
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules(name, updateDate);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id){
        try{
            ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
