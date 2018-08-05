package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.SetRedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Set;

/**
 * 集合
 * @author heqing
 * @date 2018/7/19
 */
public class SetRedisServiceImpl extends KeyRedisServiceImpl implements SetRedisService {

    @Override
    public Long sAdd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sadd(key, members);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sCard(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.scard(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> sDiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sdiff(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sDifftore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sdiffstore(destination, keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> sInter(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sinter(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sInterStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sinterstore(destination, keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean sisMember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sismember(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> sMember(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.smembers(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean sMove(String srckey, String dstkey, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = jedis.smove(srckey, dstkey, member);
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
    public String sPop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.spop(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> sPop(String key, long count) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.spop(key, count);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String sRandember(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.srandmember(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> sRandember(String key, int count) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.srandmember(key, count);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sRem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.srem(key, members);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> sUnion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sunion(keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sUnionStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.sunionstore(destination, keys);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public ScanResult<String> sScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            ScanResult<String> result = null;
            if(params == null) {
                result = jedis.sscan(key, cursor);
            } else {
                result = jedis.sscan(key, cursor, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
