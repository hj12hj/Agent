package com.hj;

import com.hj.config.CountingInterception;
import com.hj.config.TimeInterceptor;
import com.hj.config.TimeTest;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class App {

//    public static void main(String[] args) throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {
//        List<VirtualMachineDescriptor> list = VirtualMachine.list();
//        for (VirtualMachineDescriptor vmd : list) {
//            if (vmd.displayName().endsWith("WorkApp")) {
//                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
//                virtualMachine.loadAgent("/Users/hejie/Desktop/simple-before-jvm-agent-jar-with-dependencies.jar", "cxs");
//                System.out.println("ok");
////                virtualMachine.detach();
//            }
//        }
//    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        new ByteBuddy()
                .subclass(TimeTest.class)
                .method(ElementMatchers.named("test"))
                .intercept(MethodDelegation.to(CountingInterception.class))
                .make()
                .load(App.class.getClassLoader())
                .getLoaded()
                .newInstance().test("11");
    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.named("com.hj.AppTest"))
                .transform((builder, type, classLoader, module) -> builder.method(ElementMatchers.any()).intercept(MethodDelegation.to(TimeInterceptor.class)))
                .installOn(instrumentation);
    }


    public static void agentmain(String agentArgs, Instrumentation inst) {


    }
}
