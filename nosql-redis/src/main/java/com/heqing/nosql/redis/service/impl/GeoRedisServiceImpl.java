package com.heqing.nosql.redis.service.impl;

import com.heqing.nosql.redis.service.GeoRedisService;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * geo（地理信息系统）
 * @author heqing
 * @date 2018/7/30 16:50
 */
public class GeoRedisServiceImpl extends KeyRedisServiceImpl implements GeoRedisService {

    @Override
    public Long geoAdd(String key, double longitude, double latitude, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.geoadd(key, longitude, latitude, member);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.geoadd(key, memberCoordinateMap);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoCoordinate> geoPos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.geopos(key, members);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double geoDist(String key, String member1, String member2) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.geodist(key, member1, member2);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.geodist(key, member1, member2, unit);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius,
                                             GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.georadius(key, longitude, latitude, radius, unit);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
                                             GeoUnit unit, GeoRadiusParam param) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.georadius(key, longitude, latitude, radius, unit, param);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.georadiusByMember(key, member, radius, unit);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.georadiusByMember(key, member, radius, unit, param);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> geohash(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            return jedis.geohash(key, members);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
