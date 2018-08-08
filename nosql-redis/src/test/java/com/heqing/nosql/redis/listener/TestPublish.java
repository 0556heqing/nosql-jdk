package com.heqing.nosql.redis.listener;

import com.heqing.nosql.redis.RedisUtil;
import org.junit.Test;

/**
 * @author heqing
 * @date 2018/8/5 20:10
 */
public class TestPublish {

    @Test
    public void testPublish() throws Exception {
        RedisUtil.getJedis().publish("testChannel", "heqing");

        RedisUtil.getJedis().publish("test.share", "heqing");
    }

}
