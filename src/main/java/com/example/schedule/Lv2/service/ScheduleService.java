package com.example.schedule.Lv2.service;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public List<ScheduleResponseDto> getAllSchedules(Optional<String> name, Optional<String> updateDate){
        if(name.isPresent() && updateDate.isPresent()){
            return scheduleRepository.findAllSchedulesByNameAndUpdateDate(name.get(), updateDate.get());
        }else if(name.isPresent()){
            return scheduleRepository.findAllSchedulesByName(name.get());
        }else if(updateDate.isPresent()){
            return scheduleRepository.findAllSchedulesByUpdateDate(updateDate.get());
        }
        return scheduleRepository.findAllSchedules();
    }

    //단일 스케줄 조회
    public ScheduleResponseDto getScheduleById(Long id){
        return scheduleRepository.findScheduleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id" + id));
    }

}
