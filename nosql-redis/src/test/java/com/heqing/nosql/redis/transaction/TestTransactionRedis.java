package com.heqing.nosql.redis.transaction;

import com.heqing.nosql.redis.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 事物
 * @see <a href="http://redisdoc.com/transaction/index.html">Transaction（事务）</a>
 * @author heqing
 * @date 2018/8/5 19:54
 */
public class TestTransactionRedis {

    @Test
    public void testMulti() {
        RedisUtil.getList().del("t1:sDiff1", "t1:sDiff2", "t2:sDiff1", "t2:sDiff2");

        // 标记事物块1。http://redisdoc.com/transaction/multi.html
        Transaction t = RedisUtil.getTransaction();
        t.sadd("t1:sDiff1", "test1", "test2", "test3");
        t.sadd("t1:sDiff2", "test1", "test3", "test4");
        // 执行事务。http://redisdoc.com/transaction/exec.html
        t.exec();
        Set<String> result = RedisUtil.getSet().sDiff("t1:sDiff1", "sort:sDiff2");
        System.out.println("--->result = "+result);


        // 标记事物块2。http://redisdoc.com/transaction/multi.html
        Transaction t2 = RedisUtil.getTransaction();
        t2.sadd("t2:sDiff1", "test1", "test2", "test3");
        t2.sadd("t2:sDiff2", "test1", "test3", "test4");
        // 取消事务。http://redisdoc.com/transaction/discard.html
        t2.discard();
        result = RedisUtil.getSet().sDiff("t2:sDiff1", "sort:sDiff2");
        System.out.println("--->result = "+result);
    }


    @Test
    public void testWatch() {
        // 监视keys
        String watchkeys = "watchkeys";
        RedisUtil.getString().set(watchkeys, "10");
        RedisUtil.getList().del("hq");

        ExecutorService executor = Executors.newFixedThreadPool(20);
        // 测试一百人同时访问
        for (int i = 0; i < 100; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    Jedis jedis = RedisUtil.getJedis();
                    try {
                        // 监控主键。http://redisdoc.com/transaction/watch.html
                        jedis.watch(watchkeys);
                        String val = jedis.get(watchkeys);
                        int valint = Integer.valueOf(val);
                        if (valint > 0) {
                            // 开启事务
                            Transaction tx = jedis.multi();
                            tx.decr(watchkeys);
                            // 提交事务，如果此时watchkeys被改动了，则返回0
                            List<Object> list = tx.exec();
                            if (list.size() > 0) {
                                System.out.println("抢购成功，当前抢购成功人数:" + (valint+1));
                            } else {
                                System.out.println("抢购失败1");
                            }
                        } else {
                            System.out.println("抢购失败2");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        jedis.close();
                    }
                }

            });
        }
        executor.shutdown();

        // 取消监控所有主键。 http://redisdoc.com/transaction/unwatch.html
        RedisUtil.getJedis().unwatch();
    }
}
