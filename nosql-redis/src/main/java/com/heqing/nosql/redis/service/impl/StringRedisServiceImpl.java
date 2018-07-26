package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.StringRedisService;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 字符串
 * @author heqing
 * @date 2018/7/19
 */
public class StringRedisServiceImpl extends KeyRedisServiceImpl implements StringRedisService {

    private final static String NULL = "null";

    @Override
    public Long append(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.append(key, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long bitCount(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.bitcount(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long bitCount(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.bitcount(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.bitop(op, destKey, srcKeys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.bitfield(key, arguments);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.decr(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long decrby(String key, long decrement) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.decrBy(key, decrement);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.get(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean getbit(String key, long offset) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.getbit(key, offset);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
