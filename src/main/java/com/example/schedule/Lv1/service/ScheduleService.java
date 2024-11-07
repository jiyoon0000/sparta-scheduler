package com.example.schedule.Lv1.service;

import com.example.schedule.Lv1.dto.ScheduleReqeustDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;
import com.example.schedule.Lv1.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    //스케줄 생성하기
    public ScheduleResponseDto createSchedule(ScheduleReqeustDto scheduleReqeust){
        return scheduleRepository.saveSchedule(scheduleReqeust);
    }

    //전체 스케줄 조회
    public List<ScheduleResponseDto> getAllSchedules(Optional<String> name, Optional<String> updateDate){
        return scheduleRepository.findAllSchedules(name,updateDate);
    }

    //단일 스케줄 조회
    public Optional<ScheduleResponseDto> getScheduleById(Long id){
        return scheduleRepository.findScheduleById(id);
    }

}
