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
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){

        this.scheduleService = scheduleService;
    }

    //스케줄 생성하기
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequest){
        ScheduleResponseDto response = scheduleService.createSchedule(scheduleRequest);
        if(response == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(response);
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto,
            @RequestParam int password) {
        boolean updated = scheduleService.updateSchedule(id, requestDto, password);
        return updated ? ResponseEntity.ok("Schedule update successfully")
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestParam int password) {
        boolean deleted = scheduleService.deleteSchedule(id,password);
        return deleted ? ResponseEntity.ok("Schedule delete successfully")
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
    }
}