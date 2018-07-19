package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.manager.RedisManager;
import com.heqing.nosql.redis.service.KeyRedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 键
 * @author heqing
 * @date 2018/7/18
 */
public class KeyRedisServiceImpl implements KeyRedisService {

    private JedisPool pool;

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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result =  jedis.expire(key, seconds);
            if(result == 1L) {
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.expireAt(key, unixTime);
            if(result == 1L) {
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            String result = jedis.migrate(host, port, key, destinationDb, timeout);
            if("OK".equals(result)) {
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.move(key, dbIndex);
            if(result == 1L) {
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.objectEncoding(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public long objectIdletime(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.objectIdletime(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public long objectRefcount(String key) {
        Jedis jedis = null;
        try {
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.persist(key);
            if(result == 1L) {
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            Long result = jedis.pexpireAt(key, millisecondsTimestamp);
            if(result == 1L) {
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
            if(jedis == null) {
                jedis = getJedisPool().getResource();
            }
            return jedis.pttl(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }


}
