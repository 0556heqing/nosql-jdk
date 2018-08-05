package com.heqing.nosql.redis;

import org.junit.Test;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Set;

/**
 * 测试redis key
 * @author heqing
 * @date 2018/8/5 9:01
 */
public class TestKeyRedis {

    @Test
    public void testDel() {
        // 删除没有的key
        Long result = RedisUtil.getKey().del("key:del1");
        System.out.println("--->del = "+result);

        // 删除一个key
        RedisUtil.getString().set("key:del2", "del2");
        result = RedisUtil.getKey().del("key:del2");
        System.out.println("--->del = "+result);

        // 删除多个key
        RedisUtil.getString().set("key:del3—1", "del3—1");
        RedisUtil.getString().set("key:del3—2", "del3—2");
        result = RedisUtil.getKey().del("key:del3—1", "key:del3—2");
        System.out.println("--->del = "+result);
    }

    @Test
    public void testDump() {
        RedisUtil.getKey().del("key:dump");

        // 序列化不存在对象
        String result = RedisUtil.getKey().dump("key:dump");
        System.out.println("--->dump = "+result);

        // 序列化存在对象
        RedisUtil.getString().set("key:dump", "heqing");
        result = RedisUtil.getKey().dump("key:dump");
        System.out.println("--->dump = "+result);
    }

    @Test
    public void testExists() {
        RedisUtil.getKey().del("key:exists");

        // key不存在
        Boolean result = RedisUtil.getKey().exists("key:exists");
        System.out.println("--->exists = "+result);

        // key存在
        RedisUtil.getString().set("key:exists", "test");
        result = RedisUtil.getKey().exists("key:exists");
        System.out.println("--->exists = "+result);
    }

    @Test
    public void testExpire() {
        RedisUtil.getString().set("key:expire", "expire");

        // 判断是否存在
        RedisUtil.getKey().expire("key:expire", 2);
        Boolean result = RedisUtil.getKey().exists("key:expire");
        System.out.println("--->expire = "+result);

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 判断是否存在
        result = RedisUtil.getKey().exists("key:expire");
        System.out.println("--->expire = "+result);
    }

    @Test
    public void testExpireAt() {
        RedisUtil.getString().set("key:expireAt", "expireAt");

        // 判断是否存在
        RedisUtil.getKey().expireAt("key:expireAt", System.currentTimeMillis()/ 1000+2);
        Boolean result = RedisUtil.getKey().exists("key:expireAt");
        System.out.println("--->expireAt = "+result);

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 判断是否存在
        result = RedisUtil.getKey().exists("key:expireAt");
        System.out.println("--->expireAt = "+result);
    }

    @Test
    public void testKeys() {
        RedisUtil.getString().set("key:heqing", "test");
        RedisUtil.getString().set("key:hello", "hello");
        RedisUtil.getString().set("key:heello", "heello");
        RedisUtil.getString().set("key:hallo", "hallo");
        RedisUtil.getString().set("key:hxllo", "hxllo");

        Set<String> result = RedisUtil.getKey().keys("*");
        System.out.println("--->keys = "+result);

        result = RedisUtil.getKey().keys("key:h?llo");
        System.out.println("--->keys = "+result);

        result = RedisUtil.getKey().keys("key:h*llo");
        System.out.println("--->keys = "+result);

        result = RedisUtil.getKey().keys("key:h[ae]llo");
        System.out.println("--->keys = "+result);
    }

    @Test
    public void testMigrate() {
//        RedisUtil.getString().set("heqing", "heqing");
        Boolean result = RedisUtil.getKey().exists("key:migrate");
        System.out.println("--->migrate = "+result);

        result = RedisUtil.getKey().migrate("10.6.152.100", 7379, "key:migrate", 0, 100000);
        System.out.println("--->result = "+result);

        result = RedisUtil.getKey().exists("key:migrate");
        System.out.println("--->migrate = "+result);
    }

    @Test
    public void testMove() {
        RedisUtil.getString().set("key:move", "test");
        Boolean result = RedisUtil.getKey().exists("key:move");
        System.out.println("--->move = "+result);

        result = RedisUtil.getKey().move("key:move", 1);
        System.out.println("--->result = "+result);

        result = RedisUtil.getKey().exists("key:move");
        System.out.println("--->move = "+result);
    }

    @Test
    public void testObjectEncoding() {
        RedisUtil.getString().set("key:objectEncoding", "test");

        String result = RedisUtil.getKey().objectEncoding("key:objectEncoding");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testObjectRefcount() {
        Long result = RedisUtil.getKey().objectRefcount("key:objectRefcount");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testPersist() {
        RedisUtil.getString().set("key:persist", "test");

        // 判断是否存在
        RedisUtil.getKey().expire("key:persist", 2);
        Boolean result = RedisUtil.getKey().exists("key:persist");
        System.out.println("--->expire = "+result);

        // 设置不过期
        RedisUtil.getKey().persist("key:persist");

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 判断是否存在
        result = RedisUtil.getKey().exists("key:persist");
        System.out.println("--->expire = "+result);
    }

    @Test
    public void testPExpire() {
        RedisUtil.getString().set("key:pExpire", "test");

        // 判断是否存在
        RedisUtil.getKey().pExpire("key:pExpire", 2000);
        Boolean result = RedisUtil.getKey().exists("key:pExpire");
        System.out.println("--->expire = "+result);

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 判断是否存在
        result = RedisUtil.getKey().exists("key:pExpire");
        System.out.println("--->expire = "+result);
    }

    @Test
    public void testPExpireAt() {
        RedisUtil.getString().set("key:pExpireAt", "test");

        // 判断是否存在
        RedisUtil.getKey().pExpireAt("key:pExpireAt", System.currentTimeMillis()+2000);
        Boolean result = RedisUtil.getKey().exists("key:pExpireAt");
        System.out.println("--->expire = "+result);

        // 线程等待3秒
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 判断是否存在
        result = RedisUtil.getKey().exists("key:pExpireAt");
        System.out.println("--->expire = "+result);
    }

    @Test
    public void testPttl() {
        RedisUtil.getString().set("key:pttl", "test");
        RedisUtil.getKey().pExpire("key:pttl", 2000);

        Long time = RedisUtil.getKey().pttl("key:pttl");
        System.out.println("--->time = "+time);

        // 线程等待3秒
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        time = RedisUtil.getKey().pttl("key:pttl");
        System.out.println("--->time = "+time);
    }

    @Test
    public void testRandomKey() {
        String key = RedisUtil.getKey().randomKey();
        System.out.println("--->randomKey = "+key);

        key = RedisUtil.getKey().randomKey();
        System.out.println("--->randomKey = "+key);

        key = RedisUtil.getKey().randomKey();
        System.out.println("--->randomKey = "+key);
    }

    @Test
    public void testRename() {
        RedisUtil.getString().set("key:rename1", "test");
        System.out.println("--->name1 = "+RedisUtil.getKey().exists("key:rename1")+", name2 = "+RedisUtil.getKey().exists("key:rename2"));

        RedisUtil.getKey().rename("key:name1", "key:name2");
        System.out.println("--->name1 = "+RedisUtil.getKey().exists("key:rename1")+", name2 = "+RedisUtil.getKey().exists("key:rename2"));
    }

    @Test
    public void testRenameNx() {
        RedisUtil.getKey().del("key:renameNx1", "key:renameNx2");
        Boolean result= RedisUtil.getKey().renameNx("key:renameNx1", "key:renameNx2");
        System.out.println("--->result = "+result);

        RedisUtil.getString().set("key:renameNx1", "test");
        result= RedisUtil.getKey().renameNx("key:renameNx1", "key:renameNx2");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testRestore() {
        Boolean result = RedisUtil.getKey().restore("key:restore", 60, "test");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testSort() {
        RedisUtil.getList().lPush("key:sort", "1", "4", "6", "3", "0");

        List<String> result = RedisUtil.getKey().sort("key:sort", null);
        System.out.println("--->result = "+result.toString());

        SortingParams sortingParams = new SortingParams();
        sortingParams.desc();
        sortingParams.limit(0, 3);
        result = RedisUtil.getKey().sort("key:sort", sortingParams);
        System.out.println("--->result = "+result.toString());
    }

    @Test
    public void ttl() {
        RedisUtil.getString().set("key:ttl", "test");
        RedisUtil.getKey().expire("key:ttl", 2);

        Long time = RedisUtil.getKey().ttl("key:ttl");
        System.out.println("--->time = "+time);

        // 线程等待3秒
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        time = RedisUtil.getKey().ttl("key:ttl");
        System.out.println("--->time = "+time);
    }

    @Test
    public void type() {
        RedisUtil.getString().set("key:type", "test");
        String result = RedisUtil.getKey().type("key:type");
        System.out.println("--->result = "+result);

        result = RedisUtil.getKey().type("key:sort");
        System.out.println("--->result = "+result);
    }

}
