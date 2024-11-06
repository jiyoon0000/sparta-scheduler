package com.example.schedule.Lv1.repository;

import com.example.schedule.Lv1.dto.ScheduleRequstDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;

import java.util.Optional;

public class ScheduleRepository {
    ScheduleResponseDto saveSchedule(ScheduleRequstDto scheduleRequest);
    Optional<ScheduleResponseDto> findScheduleById(Long id);
}
