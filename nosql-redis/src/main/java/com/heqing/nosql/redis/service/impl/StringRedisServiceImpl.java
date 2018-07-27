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
    public Long bitOp(BitOP op, String destKey, String... srcKeys) {
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
    public List<Long> bitField(String key, String... arguments) {
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
    public Long decrBy(String key, long decrement) {
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
    public Boolean getBit(String key, long offset) {
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

    @Override
    public String getRange(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String getSet(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.getSet(key, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.incr(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incrBy(String key, long increment) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.incrBy(key, increment);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double incrByFloat(String key, double increment) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.incrByFloat(key, increment);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> mGet(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.mget(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean mSet(String... keyValues) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.mset(keyValues);
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
    public Boolean mSetNx(String... keyValues) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.msetnx(keyValues);
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
    public Boolean pSetEx(String key, long milliseconds, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.psetex(key, milliseconds, value);
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
    public Boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.set(key, value);
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
    public Boolean set(String key, String value, String nxxx) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.set(key, value, nxxx);
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
    public Boolean set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.set(key, value, nxxx, expx, time);
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
    public Boolean setBit(String key, long offset, boolean value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.setbit(key, offset, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean setBit(String key, long offset, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.setbit(key, offset, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String setEx(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.setex(key, seconds, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean setNx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.setnx(key, value);
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
    public Long setRange(String key, long offset, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.setrange(key, offset, value);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setRange(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.strlen(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
