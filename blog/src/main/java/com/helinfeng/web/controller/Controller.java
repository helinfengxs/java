package com.helinfeng.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@Slf4j
public class Controller {
    @RequestMapping("/add")
    public String  test(){
      log.info("查询学生数量");
        return "/admin/index";
    }
}
