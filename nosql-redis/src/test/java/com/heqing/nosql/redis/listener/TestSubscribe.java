package com.heqing.nosql.redis.listener;

import com.heqing.nosql.redis.RedisUtil;
import org.junit.Test;

/**
 * @author heqing
 * @date 2018/8/5 20:09
 */
public class TestSubscribe {

    @Test
    public void testSubscribe() throws Exception {
        
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        RedisUtil.getJedis().subscribe(listener, "redisChatTest");
    }
 }
