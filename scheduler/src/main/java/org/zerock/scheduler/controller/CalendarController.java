package org.zerock.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.scheduler.data.DateData;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Service
@Log4j2
public class CalendarController {
    @Autowired
    public SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @RequestMapping(value = "calendar.do", method = RequestMethod.GET)
    public String calendar(Model model, HttpServletRequest request, DateData dateData){

        Calendar cal = Calendar.getInstance();
        DateData calendarData;

        if(dateData.getDate().equals("") && dateData.getMonth().equals("")){
            dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),
                    String.valueOf(cal.get(Calendar.MONTH)), String.valueOf(cal.get(Calendar.DATE)), null, null);
        }

        return "";
    }
}
