package org.zerock.scheduler.calendar;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class ScheduleDto {
    int schedule_idx, schedule_num;
    String schedule_subject, schedule_desc, schedule_share, schedule_mycolor, schedule_temdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date schedule_date;

    @Override
    public String toString() {
        return "ScheduleDto [schedule_id=" + schedule_idx + ", schedule_num=" + schedule_num + ", schedule_subject="
                + schedule_subject + ", schedule_desc=" + schedule_desc + ", schedule_date=" + schedule_date + ", schedule_share="+schedule_share
                +", schedule_mycolor="+schedule_mycolor +"]";
    }
}
