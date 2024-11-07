package com.example.schedule.Lv1.repository;

import com.example.schedule.Lv1.dto.ScheduleReqeustDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(ScheduleReqeustDto scheduleRequest);
    Optional<ScheduleResponseDto> findScheduleById(Long id);
    List<ScheduleResponseDto> findAllSchedules(Optional<String> name, Optional<String> updateDate);
}
