package com.heqing.nosql.redis.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 分布式管理
 * @author heqing
 * @date 2018/7/18
 */
public class ShardedJedisManager extends  BaseManager {

    private static final Logger logger = LoggerFactory.getLogger(ShardedJedisManager.class);

    private static ShardedJedisPool shardedJedisPool = null;

    public ShardedJedisManager() { }

    public static ShardedJedisPool getShardedJedisPool() {
        if (shardedJedisPool == null) {
            try {
                List<JedisShardInfo> jdsInfoList = new ArrayList<>();

                // 数据库地址端口
                String delimiter = ", *";
                for (String hostport : address.split(delimiter)) {
                    if (hostport == null || "".equals(hostport)) {
                        continue;
                    }
                    String[] addr = hostport.trim().split(":");
                    if(addr.length == 2) {
                        JedisShardInfo info = new JedisShardInfo(addr[0], addr[1]);
                        // 有密码加入AUTH
                        if (!"".equals(password)) {
                            info.setPassword(password);
                        }
                        jdsInfoList.add(info);
                    }
                }
                shardedJedisPool = new ShardedJedisPool(poolConfig, jdsInfoList);

            } catch (Exception e) {
                logger.error("初始化 ShardedJedis 服务器失败>>>" + e.getMessage(), e);
            }
        }
        return shardedJedisPool;
    }

    public static void closeShardedJedisPool() {
        shardedJedisPool.close();
    }

    public static void destroyShardedJedisPool() {
        shardedJedisPool.destroy();
    }

}
