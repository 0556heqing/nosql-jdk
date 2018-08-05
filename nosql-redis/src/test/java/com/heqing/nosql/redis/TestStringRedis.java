package com.heqing.nosql.redis;

import org.junit.Test;
import redis.clients.jedis.BitOP;

import java.util.List;

/**
 * @author heqing
 * @date 2018/8/5 13:32
 */
public class TestStringRedis {

    @Test
    public void testAppend() {
        RedisUtil.getString().append("string:append", "he");
        System.out.println("--->"+RedisUtil.getString().get("string:append"));

        RedisUtil.getString().append("string:append", "qing");
        System.out.println("--->"+RedisUtil.getString().get("string:append"));
    }

    @Test
    public void testBitCount() {
        RedisUtil.getString().append("string:bitCount", "test");
        System.out.println("--->"+RedisUtil.getString().bitCount("string:bitCount"));

        System.out.println("--->"+RedisUtil.getString().bitCount("string:bitCount", 0, 1));
    }

    @Test
    public void testBitOp() {
        RedisUtil.getString().set("string:bitop1", "110");
        RedisUtil.getString().set("string:bitop2", "010");
        RedisUtil.getString().set("string:bitop3", "1");

        RedisUtil.getString().bitOp(BitOP.AND, "string:bitop:add", "string:bitop1", "string:bitop2");
        System.out.println("---> add = "+RedisUtil.getString().get("string:bitop:add"));
        RedisUtil.getString().bitOp(BitOP.OR, "string:bitop:or", "string:bitop1", "string:bitop2");
        System.out.println("---> or = "+RedisUtil.getString().get("string:bitop:or"));
        RedisUtil.getString().bitOp(BitOP.XOR, "string:bitop:xor", "string:bitop1", "string:bitop3");
        System.out.println("---> xor = "+RedisUtil.getString().get("string:bitop:xor"));
        RedisUtil.getString().bitOp(BitOP.NOT, "string:bitop:not", "string:bitop1", "string:bitop3");
        System.out.println("---> not = "+RedisUtil.getString().get("string:bitop:not"));
    }

    @Test
    public void testDecr() {
        RedisUtil.getString().set("string:decr", "10");
        System.out.println("--->"+RedisUtil.getString().get("string:decr"));

        RedisUtil.getString().decr("string:decr");
        System.out.println("--->"+RedisUtil.getString().get("string:decr"));

        RedisUtil.getString().decrBy("string:decr", 3);
        System.out.println("--->"+RedisUtil.getString().get("string:decrby"));
    }

    @Test
    public void testGet() {
        RedisUtil.getString().set("string:get", "test");
        System.out.println("--->get = "+RedisUtil.getString().get("string:get"));

        System.out.println("--->getBit = "+RedisUtil.getString().getBit("string:get", 3));

        System.out.println("--->getRange = "+RedisUtil.getString().getRange("string:get", 0, 2));

        System.out.println("--->getSet = "+RedisUtil.getString().getSet("string:get", "heqing"));
        System.out.println("--->get = "+RedisUtil.getString().get("string:get"));
    }

    @Test
    public void testIncr() {
        RedisUtil.getString().set("string:incr", "0");
        RedisUtil.getString().incr("string:incr");
        System.out.println("--->"+RedisUtil.getString().get("string:incr"));

        RedisUtil.getString().incrBy("string:incr", 5);
        System.out.println("--->"+RedisUtil.getString().get("string:incr"));

        RedisUtil.getString().incrByFloat("string:incr", 5);
        System.out.println("--->"+RedisUtil.getString().get("string:incr"));
    }

    @Test
    public void testmGet() {
        RedisUtil.getString().set("string:mget1", "he");
        RedisUtil.getString().set("string:mget2", "qing");

        List<String> result = RedisUtil.getString().mGet("string:mget1", "string:mget2");
        System.out.println("--->"+result);
    }

    @Test
    public void testmSet() {
        System.out.println("--->name1 = "+RedisUtil.getString().get("string:mSet:name1"));
        System.out.println("--->name2 = "+RedisUtil.getString().get("string:mSet:name2"));

        Boolean result = RedisUtil.getString().mSet("string:mSet:name1", "he", "string:mSet:name2", "qing");
        System.out.println("---> result = "+result);

        System.out.println("--->name1 = "+RedisUtil.getString().get("string:mSet:name1"));
        System.out.println("--->name2 = "+RedisUtil.getString().get("string:mSet:name2"));

        result = RedisUtil.getString().mSetNx("string:mSet:name1", "he", "string:mSet:name4", "qing");
        System.out.println("---> result = "+result);
    }

    @Test
    public void testpSetEx() {
        System.out.println("--->"+RedisUtil.getString().get("string:pSetEx"));

        RedisUtil.getString().pSetEx("string:pSetEx", 2000, "test");
        System.out.println("--->"+RedisUtil.getString().get("string:pSetEx"));

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--->"+RedisUtil.getString().get("string:pSetEx"));
    }

    @Test
    public void testSet() {
        System.out.println("--->"+RedisUtil.getString().get("string:set"));

        Boolean result = RedisUtil.getString().set("string:set", "test");
        System.out.println("--->result = "+result+", "+RedisUtil.getString().get("string:set"));

        result = RedisUtil.getString().set("string:set", "test", "NX");
        System.out.println("--->result = "+result+", "+RedisUtil.getString().get("string:set"));

        result = RedisUtil.getString().set("string:set", "test", "XX");
        System.out.println("--->result = "+result+", "+RedisUtil.getString().get("string:set"));
    }

    @Test
    public void testSetEx() {
        System.out.println("--->"+RedisUtil.getString().get("string:setEx"));

        RedisUtil.getString().setEx("string:setEx", 2, "test");
        System.out.println("--->"+RedisUtil.getString().get("string:setEx"));

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--->"+RedisUtil.getString().get("string:setEx"));

        Boolean result = RedisUtil.getString().setNx("string:setEx", "test");
        System.out.println("--->result = "+result+", "+RedisUtil.getString().get("string:setEx"));

        result = RedisUtil.getString().setNx("string:setEx", "test");
        System.out.println("--->result = "+result+", "+RedisUtil.getString().get("string:setEx"));
    }

    @Test
    public void testSetRange() {
        Long result = RedisUtil.getString().setRange("string:setRange1", 10, "test");
        System.out.println("--->result = "+result);

        result = RedisUtil.getString().setRange("string:setRange1");
        System.out.println("--->result = "+result);

        RedisUtil.getString().set("string:setRange2", "test");
        result = RedisUtil.getString().setRange("string:setRange2");
        System.out.println("--->result = "+result);
    }
}
