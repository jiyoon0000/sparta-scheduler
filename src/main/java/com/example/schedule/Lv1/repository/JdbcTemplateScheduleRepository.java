package com.example.schedule.Lv1.repository;

import com.example.schedule.Lv1.dto.ScheduleRequestDto;
import com.example.schedule.Lv1.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcTemplateScheduleRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequest){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", scheduleRequest.getName());
        //parameters.put("password", scheduleRequest.getPassword()); 이거도 주어진 조건대로 주석처리
        parameters.put("title", scheduleRequest.getTitle());
        parameters.put("contents", scheduleRequest.getContents());
        parameters.put("createDate", LocalDateTime.now());
        parameters.put("updateDate", LocalDateTime.now());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return findScheduleById(key.longValue()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule could not be saved "));
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return (rs, rowNum) -> new ScheduleResponseDto(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createDate").toLocalDateTime(),
                rs.getTimestamp("updateDate").toLocalDateTime()
        );
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(){
        return jdbcTemplate.query("SELECT * FROM schedules ORDER BY updateDate DESC", scheduleRowMapper());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulesByNameAndUpdateDate(String name, String updateDate){
        return jdbcTemplate.query(
                "SELECT * FROM schedules WHERE name = ? AND DATE(updateDate) = ? ORDER BY updateDate DESC",
                scheduleRowMapper(),
                name, updateDate
        );
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulesByName(String name){
        return jdbcTemplate.query(
                "SELECT * From schedules WHERE name = ? ORDER BY updateDate DESC",
                scheduleRowMapper(),
                name
        );
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulesByUpdateDate(String updateDate){
        return jdbcTemplate.query(
                "SELECT * FROM schedules WHERE DATE(updateDate) = ? ORDER BY updateDate DESC",
                scheduleRowMapper(),
                updateDate
        );
    }

    @Override
    public Optional<ScheduleResponseDto> findScheduleById(Long id){
        return jdbcTemplate.query(
                "SELECT id, name, title, contents, createDate, updateDate FROM schedules WHERE id = ?",
                scheduleRowMapper(),
                id
        ).stream().findAny();
    }
}
