package com.example.schedule.Lv2.repository;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequest);
    Optional<ScheduleResponseDto> findScheduleById(Long id);

    //메소드 정의
    List<ScheduleResponseDto> findAllSchedulesByNameAndUpdateDate(String name, String updateDate);
    List<ScheduleResponseDto> findAllSchedulesByName(String name);
    List<ScheduleResponseDto> findAllSchedulesByUpdateDate(String updateDate);
    List<ScheduleResponseDto> findAllSchedules();
}
