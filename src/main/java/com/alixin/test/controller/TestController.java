package com.alixin.test.controller;

import com.alixin.test.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    ThreadService threadService;

    @RequestMapping("/start")
    @ResponseBody
    public String start(int n) {
        threadService.startThreads(n);
        return "开始";
    }

    @RequestMapping("/ack")
    @ResponseBody
    public String acknowage() throws InterruptedException {
        //让线程休眠，超过规定等待时间
        Thread.sleep(800);
        return " OK";
    }

}
