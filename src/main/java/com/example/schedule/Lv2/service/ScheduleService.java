package com.example.schedule.Lv2.service;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.entity.Schedule;
import com.example.schedule.Lv2.repository.ScheduleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Profile("Lv2")
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){

        this.scheduleRepository = scheduleRepository;
    }

    //스케줄 생성하기
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest){
        return scheduleRepository.saveSchedule(scheduleRequest)
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreateDate(),
                        schedule.getUpdateDate()
                        )
                ).orElse(null);

    }

    //전체 스케줄 조회
    public List<ScheduleResponseDto> getAllSchedules(Optional<String> name, Optional<String> updateDate){
        return scheduleRepository.findAllSchedules();
    }

    //단일 스케줄 조회
    public ScheduleResponseDto getScheduleById(Long id){
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

    public boolean updateSchedule(Long id, ScheduleRequestDto scheduleRequest, int password) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule.isPresent() && existingSchedule.get().getPassword() == password) {
            scheduleRepository.updateSchedule(id,scheduleRequest);
            return true;
        }
        return false;
    }

    public boolean deleteSchedule(Long id, int password){
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);
        if(existingSchedule.isPresent() && existingSchedule.get().getPassword() == password){
            scheduleRepository.deleteSchedule(id);
            return true;
        }
        return false;
    }
}
