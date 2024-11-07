package com.example.schedule.Lv2.repository;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Optional<Schedule> saveSchedule(ScheduleRequestDto scheduleRequest);
    Optional<Schedule> findById(Long id);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequest);
    void deleteSchedule(Long id);
}
