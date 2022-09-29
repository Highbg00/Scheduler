package org.zerock.scheduler.calendar;

import org.springframework.stereotype.Service;
import org.zerock.scheduler.data.DateData;

import java.util.ArrayList;

@Service
public interface SchedulerService {
    int schedule_add(ScheduleDto scheduleDto);
    int before_schedule_add_search(ScheduleDto scheduleDto);
    ArrayList<ScheduleDto> schedule_list(DateData dateData);

    ScheduleDto get(int idx);

    int update(ScheduleDto scheduleDto);

    int delete(ScheduleDto scheduleDto);
}
