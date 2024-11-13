package com.example.schedule.Lv2.service;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.entity.Schedule;
import com.example.schedule.Lv2.repository.ScheduleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Profile("Lv2")
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        //this 생성자를 통해 scheduleRepository를 받음
        this.scheduleRepository = scheduleRepository;
    }

    //스케줄 생성하기
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest){
        return scheduleRepository.saveSchedule(scheduleRequest)
                //.map : optional 객체에 값을 변환하는 기능을 추가
                //람다식 사용
                //값이 존재하지 않으면 실행되지 않고 optional 값이 없는 경우가 됨
                //schedule 객체를 받아서 ScheduleResponseDto 객체로 변환
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreateDate(),
                        schedule.getUpdateDate()
                        )
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Falied to create schedule")); //optional 값이 없는 경우 null 값 반환
    }

    //전체 스케줄 조회
    public List<ScheduleResponseDto> getAllSchedules() {
        // 내림차순으로 정렬된 스케줄 목록 가져오기
        // Schedule 리스트를 ScheduleResponseDto 리스트로 변환
        return scheduleRepository.findAllSchedules();
    }


    //단일 스케줄 조회
    public ScheduleResponseDto getScheduleById(Long id){
        //특정 id로 조회, 없으면 스케줄이 없다고 반환
        return scheduleRepository.findById(id)
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreateDate(),
                        schedule.getUpdateDate()
                ))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found"));
    }

    //스케줄 수정
    public boolean updateSchedule(Long id, ScheduleRequestDto scheduleRequest, int password) {
       Schedule existingSchedule = scheduleRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found"));
        //스케줄이 존재하고, 비밀번호가 일치할 때 수정이 가능하다
        if (existingSchedule.getPassword() == password) {
            scheduleRepository.updateSchedule(id,scheduleRequest);
            return true; //수정 성공
        }
        return false;
    }

    //스케줄 삭제
    public boolean deleteSchedule(Long id, int password){
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found"));
        //스케줄이 존재하고 비밀번호가 일치하면 삭제 가능
        if(existingSchedule.getPassword() == password){
            scheduleRepository.deleteSchedule(id);
            return true;
        }
        return false;
    }
}
