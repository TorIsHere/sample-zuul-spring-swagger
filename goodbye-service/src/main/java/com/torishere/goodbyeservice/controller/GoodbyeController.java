package com.torishere.goodbyeservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class GoodbyeController {

    @RequestMapping(value = "/goodbye/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String goodbye(@PathVariable("name") String name) {
        return "goodbye " + name;
    }

    @RequestMapping(value = "/sayonara/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sayonara(@PathVariable("name") String name) {
        return "さよなら " + name;
    }
}
