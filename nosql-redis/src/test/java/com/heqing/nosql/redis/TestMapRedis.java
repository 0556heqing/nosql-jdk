package com.heqing.nosql.redis;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author heqing
 * @date 2018/8/5 15:24
 */
public class TestMapRedis {

    @Test
    public void testhDel() {
        Map test = new HashMap();
        test.put("test1", "test1");
        test.put("test2", "test2");
        RedisUtil.getHashMap().hMset("map:hdel", test);

        Map<String, String> result = RedisUtil.getHashMap().hGet("map:hdel");
        System.out.println("--->result = "+result.toString());

        RedisUtil.getHashMap().hDel("map:hdel", "test1");

        result = RedisUtil.getHashMap().hGet("map:hdel");
        System.out.println("--->result = "+result.toString());
    }

    @Test
    public void testhExists() {
        Boolean result = RedisUtil.getHashMap().hExists("map:exists", "test1");
        System.out.println("---> result = "+result);

        Map test = new HashMap();
        test.put("test1", "test1");
        test.put("test2", "test2");
        RedisUtil.getHashMap().hMset("map:exists", test);

        result = RedisUtil.getHashMap().hExists("map:exists", "test1");
        System.out.println("---> result = "+result);
    }

    @Test
    public void testhGet() {
        Map test = new HashMap();
        test.put("test1", "test1");
        test.put("test2", "test2");
        RedisUtil.getHashMap().hMset("map:hGet", test);

        String result = RedisUtil.getHashMap().hGet("map:hGet", "test1");
        System.out.println("---> result = "+result);
    }

    @Test
    public void testhincrBy() {
        Map test = new HashMap();
        test.put("test1", "1");
        test.put("test2", "1");
        RedisUtil.getHashMap().hMset("map:hincrBy", test);

        Map<String, String> result = RedisUtil.getHashMap().hGet("map:hincrBy");
        System.out.println("--->result = "+result.toString());

        RedisUtil.getHashMap().hincrBy("map:hincrBy", "test1", 5);
        RedisUtil.getHashMap().hincrByFloat("map:hincrBy", "test2", 5.4);

        result = RedisUtil.getHashMap().hGet("map:hincrBy");
        System.out.println("--->result = "+result.toString());
    }

    @Test
    public void teshKeys() {
        Map test = new HashMap();
        test.put("test1", "1");
        test.put("test2", "1");
        RedisUtil.getHashMap().hMset("map:hKeys", test);

        Set result = RedisUtil.getHashMap().hKeys("map:hKeys");
        System.out.println("--->result = "+result.toString());

        Long length = RedisUtil.getHashMap().hLen("map:hKeys");
        System.out.println("--->length = "+length);
    }

    @Test
    public void testhMget() {
        Map test = new HashMap();
        test.put("test1", "1");
        test.put("test2", "2");
        test.put("test3", "3");
        Boolean isSuccess = RedisUtil.getHashMap().hMset("map:hMget", test);
        System.out.println("--->isSuccess = "+isSuccess);

        isSuccess = RedisUtil.getHashMap().hSetNx("map:hMget", "test3", "3");
        System.out.println("--->isSuccess = "+isSuccess);

        List<String> result = RedisUtil.getHashMap().hMget("map:hMget", "test1", "test2");
        System.out.println("--->result = "+result.toString());

        result = RedisUtil.getHashMap().hVals("map:hMget");
        System.out.println("--->result = "+result.toString());
    }
}
