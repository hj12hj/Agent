package com.example.web.controller;

import com.hj.Test1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/11")
    @Test1
    public void hh(HttpServletRequest httpServletRequest){

        System.out.println(httpServletRequest);



    }


}
