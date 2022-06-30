package com.hj.config;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class CountingInterception {

    @RuntimeType
    public static Object intercept(@SuperCall Callable<?> callable, @Origin  Method method, @AllArguments  Object[] args) throws Exception {

//        String name = method.getAnnotation(Counted.class).name();
        Object call = callable.call();
        return call;
    }
}