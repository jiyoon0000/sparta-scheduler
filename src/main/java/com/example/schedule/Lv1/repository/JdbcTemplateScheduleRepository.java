package com.example.schedule.Lv1.repository;

import com.example.schedule.Lv1.dto.ScheduleRequstDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequstDto scheduleRequest){

    }
}
