package com.heqing.nosql.redis;

import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Client;

import java.util.List;

/**
 * @author heqing
 * @date 2018/8/5 14:23
 */
public class TestListRedis {

    @Test
    public void testbLpop() {
        // 没成功，这个命令没搞懂
        RedisUtil.getList().lPush("list:blpop1", "1", "2");
//        RedisUtil.getList().lPush("list:blpop2", "blpop2_1", "blpop2_2");

        List result = RedisUtil.getList().bLpop("list:blpop1");
        System.out.println("--->"+result);
    }

    @Test
    public void testlIndex() {
        RedisUtil.getList().lPush("list:lIndex", "test1", "test2", "test3");
        String result = RedisUtil.getList().lIndex("list:lIndex", 1);
        System.out.println("--->"+result);
    }

    @Test
    public void testlInsert() {
        Long result = RedisUtil.getList().lInsert("list:lIndex", Client.LIST_POSITION.AFTER, "test3", "test4");
        System.out.println("--->"+result);
    }

    @Test
    public void testlLen() {
        Long result = RedisUtil.getList().lLen("list:lIndex");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testlPop() {
        String result = RedisUtil.getList().lPop("list:lIndex");
        System.out.println("--->"+result);
    }

    @Test
    public void testlPush() {
        Long result = RedisUtil.getList().lPush("list:lPush", "test1", "test2", "test3");
        System.out.println("--->result = "+result);

        // 本地测试时，发现低版本只能添加一个值，多个值会报错
        result = RedisUtil.getList().lPushX("list:lPushX", "test1");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testlRange() {
        RedisUtil.getList().lPush("list:lRange", "test1", "test2", "test3", "test4", "test5");
        List<String> result = RedisUtil.getList().lRange("list:lRange", 1, 3);
        System.out.println("---> result = "+result.toString());
    }

    @Test
    public void testlRem() {
        RedisUtil.getList().lPush("list:lRem", "test1", "test1", "test2", "test2", "test3", "test3", "test4", "test4");
        long result = RedisUtil.getList().lRem("list:lRem", 1, "test2");
        System.out.println("---> result = "+result);

        result = RedisUtil.getList().lRem("list:lRem", -3, "test3");
        System.out.println("---> result = "+result);

        result = RedisUtil.getList().lRem("list:lRem", 0, "test4");
        System.out.println("---> result = "+result);
    }

    @Test
    public void testlSet() {
        RedisUtil.getList().lPush("list:lSet", "test1", "test2", "test3");
        List<String> result = RedisUtil.getList().lRange("list:lSet", 0, -1);
        System.out.println("---> result = "+result);

        RedisUtil.getList().lSet("list:lSet", 2, "test0");

        result = RedisUtil.getList().lRange("list:lSet", 0, -1);
        System.out.println("---> result = "+result);
    }

    @Test
    public void testlTrim() {
        RedisUtil.getList().lPush("list:lTrim", "test1", "test2", "test3");
        List<String> result = RedisUtil.getList().lRange("list:lTrim", 0, -1);
        System.out.println("---> result = "+result);

        RedisUtil.getList().lTrim("list:lTrim", 0, 1);

        result = RedisUtil.getList().lRange("list:lTrim", 0, -1);
        System.out.println("---> result = "+result);
    }

    @Test
    public void testrPop() {
        RedisUtil.getList().lPush("list:rPop", "test1", "test2", "test3");
        List<String> result = RedisUtil.getList().lRange("list:rPop", 0, -1);
        System.out.println("---> result = "+result);

        RedisUtil.getList().rPop("list:rPop");

        result = RedisUtil.getList().lRange("list:rPop", 0, -1);
        System.out.println("---> result = "+result);
    }

    @Test
    public void testrPush() {
        Long result = RedisUtil.getList().lPush("list:rPush", "test1", "test2", "test3");
        System.out.println("--->result = "+result);

        // 本地测试时，发现低版本只能添加一个值，多个值会报错
        result = RedisUtil.getList().lPushX("list:rPushX", "test1");
        System.out.println("--->result = "+result);
    }
}
