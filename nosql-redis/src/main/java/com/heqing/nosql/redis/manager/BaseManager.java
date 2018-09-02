package com.heqing.nosql.redis.manager;

import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 基础管理
 * @author heqing
 * @date 2018/7/18
 */
public abstract class BaseManager {

    protected static JedisPoolConfig poolConfig = new JedisPoolConfig();

    /** 服务器IP */
    protected static String ip = "";

    /** 端口号 */
    protected static Integer port = 0;

    /** 分布式配置多个地址填写，多个之间是用,号分隔 */
    protected static String address = "";

    /** 访问密码 */
    protected static String password = "";

    /** 数据库下标 */
    protected static Integer dbIndex = 0;

    /**
     * 可用连接实例的最大数目，默认值为8；如果赋值为-1，则表示不限制；<br/>
     * 如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
     */
    protected static Integer maxMotal = 8;

    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
     * 如果超过等待时间，则直接抛出JedisConnectionException；
     */
    protected static Long maxWait = -1L;

    /** 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。 */
    protected static Integer maxIdle = 8;

    /**
     * 控制一个pool最少有多少个状态为idle(空闲的)的jedis实例。
     */
    protected static Integer minIdle = 0;

    /** 响应超时（默认2000ms）*/
    protected static Integer timeOut = 2000;

    /** 空闲连接多长时间后会被收回 */
    protected static Long minEvictableIdle = 5000L;

    /** 每30秒运行一次空闲连接回收器 */
    protected static Long timeBetweenEvictionRuns = 30000L;

    static {
        ResourceBundle budleEnv = ResourceBundle.getBundle("redis_config");

        ip = budleEnv.getString("redis.ip");
        port = Integer.parseInt(budleEnv.getString("redis.port"));
        password = budleEnv.getString("redis.password");
        address = budleEnv.getString("redis.address");
        dbIndex = Integer.parseInt(budleEnv.getString("redis.db_index"));
        maxMotal = Integer.parseInt(budleEnv.getString("redis.max_motal"));
        maxWait = Long.parseLong(budleEnv.getString("redis.max_wait"));
        maxIdle = Integer.parseInt(budleEnv.getString("redis.max_idle"));
        minIdle = Integer.parseInt(budleEnv.getString("redis.min_idle"));
        timeOut = Integer.parseInt(budleEnv.getString("redis.time_out"));
        minEvictableIdle = Long.parseLong(budleEnv.getString("redis.min_evictable_idle"));
        timeBetweenEvictionRuns = Long.parseLong(budleEnv.getString("redis.time_between_eviction_runs"));

        poolConfig.setMaxTotal(maxMotal);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdle);
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRuns);
        poolConfig.setNumTestsPerEvictionRun(-1);
    }

}
