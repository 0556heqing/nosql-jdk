package com.heqing.nosql.redis;

import org.junit.Test;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * @author heqing
 * @date 2018/8/5 19:54
 */
public class TestTransactionRedis {

    @Test
    public void testTransaction() {
        Transaction t = RedisUtil.getTransaction();
        t.sadd("t1:sDiff1", "test1", "test2", "test3");
        t.sadd("t1:sDiff2", "test1", "test3", "test4");
        // 执行事务
        t.exec();
        Set<String> result = RedisUtil.getSet().sDiff("t1:sDiff1", "sort:sDiff2");
        System.out.println("--->result = "+result);

        Transaction t2 = RedisUtil.getTransaction();
        t2.sadd("t2:sDiff1", "test1", "test2", "test3");
        t2.sadd("t2:sDiff2", "test1", "test3", "test4");
        // 执行事务
//        t2.exec();
        t2.discard();
        result = RedisUtil.getSet().sDiff("t2:sDiff1", "sort:sDiff2");
        System.out.println("--->result = "+result);
    }
}
