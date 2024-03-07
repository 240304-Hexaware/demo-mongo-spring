package com.revature.demomongospring.controllers;

import com.revature.demomongospring.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//  http://www.mysite.com/api/v2/users/      URL
//  http://www.mysite.com                    HOST
//  /api/v2/users/                           URI


@RestController
public class MyController {

    private MyService service;

    @Autowired
    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong!";
    }
}
