package org.zerock.scheduler.controller;

import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.scheduler.calendar.ScheduleDto;

import org.zerock.scheduler.calendar.SchedulerServiceImpl;
import org.zerock.scheduler.data.DateData;
import org.zerock.scheduler.member.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
public class CalendarController {
    @Autowired
    private SchedulerServiceImpl service;

    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @RequestMapping(value = "calendar.do", method = RequestMethod.GET)
    public String calendar(Model model, HttpServletRequest request, DateData dateData, HttpSession session){

        Calendar cal = Calendar.getInstance();
        DateData calendarData;

        if(dateData.getDate().equals("") && dateData.getMonth().equals("")){
            dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),
                    String.valueOf(cal.get(Calendar.MONTH)), String.valueOf(cal.get(Calendar.DATE)), null, null);
        }

        Map<String, Integer> today_info = dateData.today_info(dateData);
        List<DateData> dateList = new ArrayList<DateData>();
        dateData.setId(((UserDTO)session.getAttribute("loginInfo")).getId());
        ArrayList<ScheduleDto> Schedule_list = service.schedule_list(dateData);

        ScheduleDto[][] schedule_data_arr = new ScheduleDto[32][4];
        if(Schedule_list.isEmpty() != true){
            int j = 0;
            for(int i = 0; i < Schedule_list.size(); i++){
                int date = Integer.parseInt(String.valueOf(Schedule_list.get(i).getSchedule_date()).substring(
String.valueOf(Schedule_list.get(i).getSchedule_date()).length() -
20,
String.valueOf(Schedule_list.get(i).getSchedule_date()).length() - 18));
                 if(i > 0){
                   int date_before = Integer.parseInt(String.valueOf(Schedule_list.get(i -
1).getSchedule_date())
                           .substring(String.valueOf(Schedule_list.get(i -
1).getSchedule_date()).length() - 20,
                                        String.valueOf(Schedule_list.get(i -
1).getSchedule_date()).length()-18));
                   if(date_before == date){
                       j = j + 1;
                   } else {
                       j = 0;
                   }
                     schedule_data_arr[date][j] = Schedule_list.get(i);
                 } else {
                     schedule_data_arr[date][j] = Schedule_list.get(i);
                 }
            }
        }

        for (int i = 1; i < today_info.get("start"); i++){
            calendarData = new DateData(null, null, null, null, null);
            dateList.add(calendarData);
        }

        for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++){
                ScheduleDto[] schedule_data_arr3 = new ScheduleDto[4];
                schedule_data_arr3 = schedule_data_arr[i];

                if(i == today_info.get("today")){
                    calendarData = new DateData(String.valueOf(dateData.getYear()),
                            String.valueOf(dateData.getMonth()),
                            String.valueOf(i), "today", schedule_data_arr3);
                }else{
                    calendarData = new DateData(String.valueOf(dateData.getYear()),
                            String.valueOf(dateData.getMonth()),
                            String.valueOf(i), "normal_date", schedule_data_arr3);
                }
                dateList.add(calendarData);
        }

        int index = 7 - dateList.size() % 7;

        if(dateList.size() % 7 != 0){
            for(int i = 0; i< index; i++){
                calendarData = new DateData(null, null, null, null, null);
                dateList.add(calendarData);
            }
        }

        model.addAttribute("dateList", dateList);
        model.addAttribute("today_info", today_info);
        return "scheduler";
    }

    @RequestMapping(value = "schedule_add.do", method = RequestMethod.GET)
    public String schedule_add(HttpServletRequest request, ScheduleDto scheduleDto, RedirectAttributes rttr){

        int count = service.before_schedule_add_search(scheduleDto);
        String message = "";

        if(count >= 4){
            message = "스케줄은 최대 4개만 등록 가능합니다.";
        } else{
            service.schedule_add(scheduleDto);
            message = "스케쥴이 등록되었습니다.";
        }

        rttr.addFlashAttribute("message",message);
        return "redirect:calendar.do";
    }

    @RequestMapping(value = "/schedule_show", method = RequestMethod.GET)
    public String schedule_show(Model model, HttpServletRequest request, @RequestParam("schedule_idx") int idx, RedirectAttributes rttr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ScheduleDto dto = service.get(idx);
        dto.setSchedule_temdate(simpleDateFormat.format(dto.getSchedule_date()));
        model.addAttribute("schedule_show",dto);
        return null;
    }

    @RequestMapping(value = "modify.do", method = RequestMethod.GET)
    public String schedule_modify(Model model, HttpServletRequest request, ScheduleDto scheduleDto, RedirectAttributes rttr){
        service.update(scheduleDto);
        model.addAttribute("schedule_modify",service.update(scheduleDto));

        return "/modify";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String schedule_delete(Model model, HttpServletRequest request, ScheduleDto scheduleDto, RedirectAttributes rttr){
        service.delete(scheduleDto);
        model.addAttribute("schedule_delete",service.delete(scheduleDto));
        return null;
    }
}
