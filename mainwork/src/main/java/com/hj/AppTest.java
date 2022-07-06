package com.hj;

@Test
public class AppTest {
    @Lock
    public String hh(){
        System.out.println("hh");
        return "111";
    }
}
