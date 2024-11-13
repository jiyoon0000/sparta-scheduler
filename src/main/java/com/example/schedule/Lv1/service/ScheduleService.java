package com.example.schedule.Lv1.service;

import com.example.schedule.Lv1.dto.ScheduleRequestDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;
import com.example.schedule.Lv1.repository.ScheduleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Lv1 프로파일에서만 활성화
@Profile("Lv1")
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    //스케줄 생성하기
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest){
        return scheduleRepository.saveSchedule(scheduleRequest);
    }

    //전체 스케줄 조회
    public List<ScheduleResponseDto> getAllSchedules(String name, String updateDate){
        //조건을 주어서 이름, 수정일 기준으로 조회
        //isPresent = optional 객체가 값을 가지고 있다면 true, 아니면 false
        //이름값과 수정일 값을 모두 가지고 있는 경우에 스케줄 조회
        if(name != null && updateDate != null){
            return scheduleRepository.findAllSchedulesByNameAndUpdateDate(name, updateDate);
            //이름값만 가지고 있는 경우에 스케줄 조회
        }else if(name != null){
            return scheduleRepository.findAllSchedulesByName(name);
            //수정일 값만 가지고 있는 경우에 스케줄 조회
        }else if(updateDate != null){
            return scheduleRepository.findAllSchedulesByUpdateDate(updateDate);
        }
        //결국 어떤 조건에서든 모든 스케줄을 조회하는데 이름과 수정일을 기준으로 조회
        return scheduleRepository.findAllSchedules();
    }

    //단일 스케줄 조회
    public ScheduleResponseDto getScheduleById(Long id){
        return scheduleRepository.findScheduleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id" + id));
    }
}