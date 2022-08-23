package org.zerock.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Log4j2
public class MainController {
    @GetMapping({"/","/list"})
    public String list(){

        log.info("list.....");

        return "/scheduler/list";
    }
}