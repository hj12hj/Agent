package com.example.web.controller;

import com.hj.Lock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/11")
    @Lock
    public void hh(HttpServletRequest httpServletRequest){

        System.out.println(httpServletRequest);



    }


}
