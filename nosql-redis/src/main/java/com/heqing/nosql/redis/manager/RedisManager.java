package com.heqing.nosql.redis.manager;

import redis.clients.jedis.*;

/**
 * 单线程管理
 * @author heqing
 * @date 2018/7/18
 */
public class RedisManager extends BaseManager {

    private static JedisPool jedisPool = null;

    public RedisManager() { }

    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            jedisPool = new JedisPool(poolConfig, ip, port, timeOut, password, dbIndex);
        }
        return jedisPool;
    }

    public static void closeJedisPool() {
        jedisPool.close();
    }

    public static void destroyJedisPool() {
        jedisPool.destroy();
    }

}
