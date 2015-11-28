package com.martinetherton.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by martin on 28/11/15.
 */
@RestController
public class Rest {

    @RequestMapping(value="/order", method= RequestMethod.GET)
    public Order hello() {
        System.out.println("Rest says hello");
        Order order = new Order("123");
        return order;
    }
}
