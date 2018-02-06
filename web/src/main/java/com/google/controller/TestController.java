package com.google.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanjiahuan on 2018/2/5.
 * Description
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/print")
    @ResponseBody
    public String returnStr() {
        return "fuck you";
    }

}
