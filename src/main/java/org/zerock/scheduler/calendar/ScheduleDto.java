package org.zerock.scheduler.calendar;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {
    int schedule_id, schedule_num;
    String schedule_subject, schedule_desc;
    Character schedule_share, schedule_myc;
    Date schedule_date;
}
