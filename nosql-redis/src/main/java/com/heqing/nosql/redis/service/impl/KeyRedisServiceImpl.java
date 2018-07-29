package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.manager.RedisManager;
import com.heqing.nosql.redis.service.KeyRedisService;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 键
 * @author heqing
 * @date 2018/7/18
 */
public class KeyRedisServiceImpl implements KeyRedisService {

    private JedisPool pool;

    protected final static String OK = "OK";

    public KeyRedisServiceImpl() {}

    public JedisPool getJedisPool() {
        if(pool == null) {
            pool = RedisManager.getJedisPool();
        }
        return pool;
    }

    @Override
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.del(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String dump(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return new String(jedis.dump(key));
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.exists(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result =  jedis.expire(key, seconds);
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
    public Boolean expireAt(String key, long unixTime) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.expireAt(key, unixTime);
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
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.keys(pattern);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean migrate(String host, int port, String key, int destinationDb, int timeout) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.migrate(host, port, key, destinationDb, timeout);
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
    public Boolean move(String key, int dbIndex) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.move(key, dbIndex);
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
    public String objectEncoding(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.objectEncoding(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long objectIdletime(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.objectIdletime(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long objectRefcount(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.objectRefcount(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean persist(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.persist(key);
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
    public Boolean pExpire(String key, long milliseconds) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.pexpire(key, milliseconds);
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
    public Boolean pExpireAt(String key, long millisecondsTimestamp) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.pexpireAt(key, millisecondsTimestamp);
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
    public Long pttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.pttl(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String randomKey() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.randomKey();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean rename(String oldkey, String newkey) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.rename(oldkey, newkey);
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
    public Boolean renameNx(String oldkey, String newkey) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.renamenx(oldkey, newkey);
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
    public Boolean restore(String key, int ttl, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            String result = jedis.restore(key, ttl, value.getBytes());
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
    public List<String> sort(String key, SortingParams sortingParams) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            List<String> result = new ArrayList<>();
            if(sortingParams == null) {
                result = jedis.sort(key);
            } else {
                result = jedis.sort(key, sortingParams);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.ttl(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String type(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.type(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            ScanResult<String> result = null;
            if(params == null) {
                result = jedis.scan(cursor);
            } else {
                result = jedis.scan(cursor, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
