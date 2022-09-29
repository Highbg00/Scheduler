package org.zerock.scheduler.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.zerock.scheduler.data.DateData;
import org.zerock.scheduler.repository.SchedulerRepository;


import java.util.ArrayList;

public class SchedulerServiceImpl implements SchedulerService{

    @Autowired
    SchedulerRepository dao;

    @Override
    public int schedule_add(ScheduleDto scheduleDto) {
        return dao.schedule_add(scheduleDto);
    }

    @Override
    public int before_schedule_add_search(ScheduleDto scheduleDto) {
        return dao.before_schedule_add_search(scheduleDto);
    }

    @Override
    public ArrayList<ScheduleDto> schedule_list(DateData dateData) {
        return dao.schedule_list(dateData);
    }

    @Override
    public ScheduleDto get(int idx) {
        return dao.get(idx);
    }

    @Override
    public int update(ScheduleDto scheduleDto) {
        return dao.update(scheduleDto);
    }

    @Override
    public int delete(ScheduleDto scheduleDto) {
        return dao.delete(scheduleDto);
    }
}
