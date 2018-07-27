package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.ListRedisService;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 列表
 * @author heqing
 * @date 2018/7/19
 */
public class ListRedisServiceImpl extends KeyRedisServiceImpl implements ListRedisService {

    @Override
    public List<String> bLpop(String... args) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.blpop(args);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> bLpop(int timeout, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.blpop(timeout, keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> bRpop(String... args) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.brpop(args);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> bRpop(int timeout, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.brpop(timeout, keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String bRpopLpush(String source, String destination, int timeout) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.brpoplpush(source, destination, timeout);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String lIndex(String key, long index) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lindex(key, index);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lInsert(String key, Client.LIST_POSITION where, String pivot, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.linsert(key, where, pivot, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lLen(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.llen(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String lPop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lpop(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lPush(String key, String... string) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lpush(key, string);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lPushX(String key, String... string) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lpushx(key, string);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> lRange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lrange(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lRem(String key, long count, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lrem(key, count, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String lSet(String key, long index, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.lset(key, index, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean lTrim(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.ltrim(key, start, end);
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

    @Override
    public String rPop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpop(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String rPopLpush(String srckey, String dstkey) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpoplpush(srckey, dstkey);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long rPush(String key, String... string) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpush(key, string);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long rPushX(String key, String... string) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.rpushx(key, string);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
