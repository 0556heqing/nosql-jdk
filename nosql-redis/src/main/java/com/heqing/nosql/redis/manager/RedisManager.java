package com.heqing.nosql.redis.manager;

import com.sun.deploy.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

/**
 * 单线程管理
 * @author heqing
 * @date 2018/7/18
 */
public class RedisManager extends BaseManager {

    private static final Logger logger = LoggerFactory.getLogger(RedisManager.class);

    private static JedisPool jedisPool = null;

    public RedisManager() { }

    static {
        try {
            if("".equals(redisPassword)) {
                redisPassword = null;
            }
            jedisPool = new JedisPool(poolConfig, redisIp, redisPort, timeOut, redisPassword, redisDb_index);

        } catch (Exception e) {
            logger.error("初始化redis服务器失败>>>" + e.getMessage(), e);
        }
    }

    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            jedisPool = new JedisPool(poolConfig, redisIp, redisPort, timeOut, redisPassword, redisDb_index);
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
