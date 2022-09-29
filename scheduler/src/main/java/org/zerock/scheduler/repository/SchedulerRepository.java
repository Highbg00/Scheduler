package org.zerock.scheduler.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.scheduler.calendar.ScheduleDto;
import org.zerock.scheduler.data.DateData;
import org.zerock.scheduler.entity.Schduler;

import java.util.ArrayList;

@Mapper
public interface SchedulerRepository extends JpaRepository<Schduler, String>, QuerydslPredicateExecutor<Schduler> {
    int schedule_add(ScheduleDto scheduleDto);
    int before_schedule_add_search(ScheduleDto scheduleDto);
    ArrayList<ScheduleDto> schedule_list(DateData dateData);

    ScheduleDto get (int idx);

    int update(ScheduleDto scheduleDto);

    int delete(ScheduleDto scheduleDto);
}
