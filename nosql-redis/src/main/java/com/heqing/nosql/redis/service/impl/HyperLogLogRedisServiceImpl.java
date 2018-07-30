package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.HyperLogLogRedisService;
import redis.clients.jedis.Jedis;

/**
 * HyperLogLog
 * @author heqing
 * @date 2018/7/30 16:14
 */
public class HyperLogLogRedisServiceImpl extends KeyRedisServiceImpl implements HyperLogLogRedisService {

    @Override
    public Boolean pfAdd(String key, String... elements) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.pfadd(key, elements);
            if(1L == result) {
                return true;
            }
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long pfCount(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.pfcount(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean pfMerge(String destkey, String... sourcekeys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.pfmerge(destkey, sourcekeys);
            if(OK.equals(result)) {
                return true;
            }
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
