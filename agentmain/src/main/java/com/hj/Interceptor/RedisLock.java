package com.hj.Interceptor;

import com.hj.redis.RedisUtils;
import net.bytebuddy.implementation.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 *
 * @author hj
 * @version 1.0
 * @since Created at 2022/7/3 10:31 上午
 */
public class RedisLock {

    /**
     * redis lock
     *
     * @param method   待处理方法
     * @param callable 原方法执行
     * @return 执行结果
     */
    @RuntimeType
    public static Object intercept(@This Object target, @Origin Method method, @SuperCall Callable<?> callable, @AllArguments Object[] args) throws Exception {
        Jedis jedis = RedisUtils.getJedis();
        String key = "";
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) arg;
                key = httpServletRequest.getRequestURI().toString();
            }
        }
        //加锁
        String s = jedis.get(key);
        while (s != null) {
            Thread.sleep(1000);
            s = jedis.get(key);
            System.out.println("等待.....");
            System.out.println(s);
        }
        jedis.set(key,"1");


        try {
            Object call = callable.call();
            return call;
        } catch (Exception e) {
            // 进行异常信息上报
            System.out.println("方法执行发生异常" + e.getMessage());
            throw e;
        } finally {
            jedis.del(key);
        }
    }
}