package com.heqing.nosql.redis;

import com.heqing.nosql.redis.manager.RedisManager;
import com.heqing.nosql.redis.manager.ShardedJedisManager;
import com.heqing.nosql.redis.service.*;
import com.heqing.nosql.redis.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis工具类
 * @author heqing
 * @date 2018/7/18
 */
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static KeyRedisService jedisKeyOperations;
    private static StringRedisService jedisStringOperations;
    private static HashMapRedisService jedisHashMapOperations;
    private static ListRedisService jedisListOperations;
    private static SetRedisService jedisSetOperations;
    private static SortedSetRedisService jedisSortedSetOperations;

    /**
     * 该方法用于获取 Jedis 线程池
     */
    public static JedisPool getJedisPool() {
        return RedisManager.getJedisPool();
    }

    /**
     * 该方法用于关闭 Jedis 线程池
     */
    public static void closeJedisPool() {
        RedisManager.closeJedisPool();
    }

    /**
     * 该方法用于销毁 Jedis 线程池
     */
    public static void destroyJedisPool() {
        RedisManager.destroyJedisPool();
    }

    /**
     * 该方法用于获取分布式 ShardedJedis 线程池
     */
    public static ShardedJedisPool getShardedJedisPool() {
        return ShardedJedisManager.getShardedJedisPool();
    }

    /**
     * 该方法用于关闭分布式 ShardedJedis 线程池
     */
    public static void closeShardedJedisPool() {
        ShardedJedisManager.closeShardedJedisPool();
    }

    /**
     * 该方法用于销毁分布式 ShardedJedis 线程池
     */
    public static void destroyShardedJedisPool() {
        ShardedJedisManager.destroyShardedJedisPool();
    }

     /**
     * 该方法用于获取操作 Key 的方法集合
     */
    public static KeyRedisService getKey() {
        if(jedisKeyOperations == null) {
            jedisKeyOperations = new KeyRedisServiceImpl();
        }
        return jedisKeyOperations;
    }

    /**
     * 该方法用于获取操作 String 的方法集合
     */
    public static StringRedisService getString() {
        if(jedisStringOperations == null) {
            jedisStringOperations = new StringRedisServiceImpl();
        }
        return jedisStringOperations;
    }

    /**
     * 该方法用于获取操作 HashMap 的方法集合
     */
    public static HashMapRedisService getHashMap() {
        if(jedisHashMapOperations == null) {
            jedisHashMapOperations = new HashMapRedisServiceImplImpl();
        }
        return jedisHashMapOperations;
    }

    /**
     * 该方法用于获取操作 List 的方法集合
     */
    public static ListRedisService getList() {
        if(jedisListOperations == null) {
            jedisListOperations = new ListRedisServiceImpl();
        }
        return jedisListOperations;
    }

    /**
     * 该方法用于获取操作 Set(无序) 的方法集合
     */
    public static SetRedisService getSet() {
        if(jedisSetOperations == null) {
            jedisSetOperations = new SetRedisServiceImpl();
        }
        return jedisSetOperations;
    }

    /**
     * 该方法用于获取操作 SortSet(有序) 的方法集合
     */
    public static SortedSetRedisService getSortedSet() {
        if (jedisSortedSetOperations == null) {
            jedisSortedSetOperations = new SortedSetRedisServiceImpl();
        }
        return jedisSortedSetOperations;
    }
}