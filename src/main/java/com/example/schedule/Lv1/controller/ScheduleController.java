package com.example.schedule.Lv1.controller;

import com.example.schedule.Lv1.dto.ScheduleRequestDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;
import com.example.schedule.Lv1.service.ScheduleService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("Lv1")//Lv1 프로파일이 활성화될 때 컨트롤러 활성화
@RestController //Restful 웹 서비스
@RequestMapping("/api/schedules")//기본 경로 설정
//Controller class = 사용자의 요청을 받고 응답을 주는 클래스
public class ScheduleController {

    private final ScheduleService scheduleService;

    //this 생성자 사용
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    //스케줄 생성하기
    @PostMapping//생성이니까 post 사용
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        //요청이 null인 경우 badrequest 응답 (400)
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //전체 스케줄 조회
    @GetMapping//데이터를 가져와야하니까 get 사용
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(
            @RequestParam String name, //조회할때 작성자명을 조건으로 조회
            @RequestParam String updateDate //조회할 때 수정일을 조건으로 조회
    ){
        //조건에 맞는 전체 스케줄 조회
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules(name, updateDate);
        return ResponseEntity.ok(schedules);
    }

    //스케줄 단건 조회
    @GetMapping("/{id}")//이거도 데이터를 가져와야하니까 get 사용
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id){
            //스케줄을 조회할때 id로 찾기
            //찾을 수 없다면 예외발생
        ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

}
