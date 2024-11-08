package com.example.schedule.Lv2.repository;

import com.example.schedule.Lv2.dto.ScheduleRequestDto;
import com.example.schedule.Lv2.dto.ScheduleResponseDto;
import com.example.schedule.Lv2.entity.Schedule;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Profile("Lv2")
@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcTemplateScheduleRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Schedule> saveSchedule(ScheduleRequestDto scheduleRequest){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", scheduleRequest.getName());
        parameters.put("password", scheduleRequest.getPassword());
        parameters.put("title", scheduleRequest.getTitle());
        parameters.put("contents", scheduleRequest.getContents());
        parameters.put("createDate", LocalDateTime.now());
        parameters.put("updateDate", LocalDateTime.now());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return findById(key.longValue());
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
    public Optional<Schedule> findById(Long id){
        return jdbcTemplate.query(
                "SELECT * FROM schedules WHERE id = ?",
                (rs,rowNum) -> {
                    Schedule schedule = new Schedule();
                    schedule.setId(rs.getLong("id"));
                    schedule.setName(rs.getString("name"));
                    schedule.setPassword(rs.getInt("password"));
                    schedule.setTitle(rs.getString("title"));
                    schedule.setContents(rs.getString("contents"));
                    schedule.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
                    schedule.setUpdateDate(rs.getTimestamp("updateDate").toLocalDateTime());
                    return schedule;
                }, id).stream().findFirst();
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequest){
        String sql = "UPDATE schedules SET name = ?, title = ?, updateDate = ? WHERE id = ?";
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(
                sql,
                scheduleRequest.getName(),
                scheduleRequest.getTitle(),
                now,
                id);
        return findById(id)
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreateDate(),
                        schedule.getUpdateDate()
                )).orElse(null);
    }

    public void deleteSchedule(Long id){
        String sql = "DELETE FROM schedules WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
