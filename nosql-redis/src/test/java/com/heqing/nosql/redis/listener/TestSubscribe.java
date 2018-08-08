package com.heqing.nosql.redis.listener;

import com.heqing.nosql.redis.RedisUtil;
import org.junit.Test;

/**
 * @author heqing
 * @date 2018/8/5 20:09
 */
public class TestSubscribe {

    RedisMsgPubSubListener listener = new RedisMsgPubSubListener();

    @Test
    public void testSubscribe() throws Exception {
//        RedisUtil.getJedis().subscribe(listener, "testChannel");

        RedisUtil.getJedis().subscribe(listener, "test.share", "test.log");
    }

    @Test
    public void testpSubscribe() throws Exception {
        RedisUtil.getJedis().psubscribe(listener, "test.*");
    }
 }
