package com.revature.demomongospring.controllers;

import com.revature.demomongospring.exceptions.ItemNotFoundException;
import com.revature.demomongospring.models.Associate;
import com.revature.demomongospring.services.MyService;
import org.bson.types.ObjectId;
import org.hibernate.cache.spi.support.AbstractDomainDataRegion;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//  http://www.mysite.com/api/v2/users/      URL
//  http://www.mysite.com                    HOST
//  /api/v2/users/                           URI


@RestController
public class MyController {

    private MyService service;

    @Autowired
    public MyController(MyService service) {
        System.out.println("Controller Constructor");
        this.service = service;
    }

    @GetMapping(value = "/ping")
    public String ping() {
        return "pong!";
    }


    //Path Variable, QueryParam
    //     www.site.com/context-path/users/?firstName=kyle

    @GetMapping("/associates/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Associate getById(@PathVariable String id) throws ItemNotFoundException {
        return service.findById(new ObjectId(id));
    }



    @GetMapping("/associates")
    @ResponseStatus(HttpStatus.OK)
    public Associate getByFirstName(@RequestParam("firstName") String firstName) throws ItemNotFoundException {
        return service.findByFirstName(firstName);
    }


    //Exception Handler
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String queryItemNotFound(ItemNotFoundException e) {
        //TODO: change this out for a log message
        System.out.println(e.getMessage());//we would want to log this instead in the real world
        return e.getMessage();
    }

}
