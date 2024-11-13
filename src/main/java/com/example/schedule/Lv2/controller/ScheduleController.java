package com.example.schedule.Lv2.controller;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.service.ScheduleService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("Lv2")
@RestController
@RequestMapping("/api/schedules") // /api/schedules : 데이터를 json 형태로 주고 받기 위한 api 요청
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
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(){
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    //스케줄 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id){
            //id를 기준으로 조회
            ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
            return ResponseEntity.ok(schedule); //조회된 스케줄 반환
    }

    //스케줄 수정
    //특정 id의 스케줄을 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto,
            //비밀번호를 파라미터로 받아서 비밀번호 검증
            @RequestParam int password) {
        boolean updated = scheduleService.updateSchedule(id, requestDto, password);
        //삼항 연산자를 이용해 update가 true or false 를 확인한 후 각각에 맞는 응답을 반환
        return updated ? ResponseEntity.ok("Schedule update successfully") //true 일 경우 업데이트가 성공적으로 되었다.
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password"); //false 일 경우 패스워드가 틀렸다.
    }

    //스케줄 삭제
    //특정 id의 스케줄을 수정
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestParam int password) {
        //비밀번호 검증
        boolean deleted = scheduleService.deleteSchedule(id,password);
        return deleted ? ResponseEntity.ok("Schedule delete successfully") // true 일 경우 성공적으로 삭제 되었다.
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password"); //false 일 경우 패스워드가 틀렸다.
    }
}