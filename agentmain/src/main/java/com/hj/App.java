package com.hj;

import com.hj.Interceptor.RedisLock;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.web.bind.annotation.RestController;

import java.lang.instrument.Instrumentation;


public class App {

    public static void premain(String agentArgs, Instrumentation instrumentation) {


        new AgentBuilder.Default()
                .type(ElementMatchers.isAnnotatedWith(RestController.class))
                .transform((builder, type, classLoader, module) -> builder.method(ElementMatchers.isAnnotatedWith(Lock.class)).intercept(MethodDelegation.to(RedisLock.class)))
                .installOn(instrumentation);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {


    }

}
