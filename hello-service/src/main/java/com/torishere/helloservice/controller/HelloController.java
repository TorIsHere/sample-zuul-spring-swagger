package com.torishere.helloservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/irasshai/", method = RequestMethod.GET)
    @ResponseBody
    public String irasshai() {
        return "いらっしゃい";
    }

}
