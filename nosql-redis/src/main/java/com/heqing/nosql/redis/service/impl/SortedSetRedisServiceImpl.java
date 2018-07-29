package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.SortedSetRedisService;
import redis.clients.jedis.*;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.util.Map;
import java.util.Set;

/**
 * 有序集合
 * @author heqing
 * @date 2018/7/19
 */
public class SortedSetRedisServiceImpl extends KeyRedisServiceImpl implements SortedSetRedisService {

    @Override
    public Long zAdd(String key, double score, String member) {
        return zAdd(key, score, member, null);
    }

    @Override
    public Long zAdd(String key, Map<String, Double> scoreMembers) {
        return zAdd(key, scoreMembers, null);
    }

    @Override
    public Long zAdd(String key, double score, String member, ZAddParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = null;
            if(params == null) {
                result = jedis.zadd(key, score, member);
            } else {
                result = jedis.zadd(key, score, member, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zAdd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = null;
            if(params == null) {
                result = jedis.zadd(key, scoreMembers);
            } else {
                result = jedis.zadd(key, scoreMembers, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zCard(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zcard(key);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zCount(String key, double min, double max) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zcount(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double zIncrby(String key, double score, String member) {
        return zIncrby(key, score, member, null);
    }

    @Override
    public Double zIncrby(String key, double score, String member, ZIncrByParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Double result = null;
            if(params == null) {
                result = jedis.zincrby(key, score, member);
            } else {
                result = jedis.zincrby(key, score, member, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrange(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRangeByScore(String key, double min, double max) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrangeByScore(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRangeByScore(String key, double min, double max, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zRank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrank(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zRem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrem(key, members);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zRemrangeByRank(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zRemrangeByScore(String key, double start, double end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zRemrangeByLex(String key, String min, String max) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zremrangeByLex(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRevrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrevrange(key, start, end);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRevrangeByScore(String key, double max, double min) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zRevrank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrevrank(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double zScore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zscore(key, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zUnionstore(String dstkey, String... sets) {
        return zUnionstore(dstkey, null, sets);
    }

    @Override
    public Long zUnionstore(String dstkey, ZParams params, String... sets) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = null;
            if(result == null) {
                result = jedis.zunionstore(dstkey, sets);
            } else {
                result = jedis.zunionstore(dstkey, params, sets);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zInterstore(String dstkey, String... sets) {
        return zInterstore(dstkey, null, sets);
    }

    @Override
    public Long zInterstore(String dstkey, ZParams params, String... sets) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Long result = null;
            if(result == null) {
                result = jedis.zinterstore(dstkey, sets);
            } else {
                result = jedis.zinterstore(dstkey, params, sets);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public ScanResult<Tuple> zScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            ScanResult<Tuple> result = null;
            if(params == null) {
                result = jedis.zscan(key, cursor);
            } else {
                result = jedis.zscan(key, cursor, params);
            }
            return result;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRangeByLex(String key, String min, String max) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrangeByLex(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> zRangeByLex(String key, String min, String max, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zrangeByLex(key, min, max, offset, count);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long zLexCount(String key, String min, String max) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.zlexcount(key, min, max);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

}
