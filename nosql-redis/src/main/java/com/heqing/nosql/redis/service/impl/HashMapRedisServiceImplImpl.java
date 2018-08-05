package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.HashMapRedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 哈希表
 * @author heqing
 * @date 2018/7/19
 */
public class HashMapRedisServiceImplImpl extends KeyRedisServiceImpl implements HashMapRedisService {

    @Override
    public Long hDel(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hdel(key, field);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean hExists(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hexists(key, field);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hGet(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hget(key, field);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Map<String, String> hGet(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hgetAll(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hincrBy(String key, String field, long increment) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hincrBy(key, field, increment);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double hincrByFloat(String key, String field, double increment) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hincrByFloat(key, field, increment);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> hKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hkeys(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hLen(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hlen(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> hMget(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hmget(key, field);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean hMset(String key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            if(OK.equals(jedis.hmset(key, hash))) {
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
    public Boolean hSetNx(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.hsetnx(key, field, value);
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
    public List<String> hVals(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.hvals(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            ScanResult<Map.Entry<String, String>> result = null;
            if(params == null) {
                result = jedis.hscan(key, cursor);
            } else {
                result = jedis.hscan(key, cursor, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

}
