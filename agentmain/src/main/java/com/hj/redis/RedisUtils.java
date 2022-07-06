package com.hj.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static JedisPool pool = null;


    public static Jedis getJedis() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(8);
            config.setMaxTotal(18);
            pool = new JedisPool(config, "10.55.254.135", 6379, 2000, "123456");
        }
        return pool.getResource();
    }

}
