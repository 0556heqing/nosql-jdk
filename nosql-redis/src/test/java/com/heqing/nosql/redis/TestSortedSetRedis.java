package com.heqing.nosql.redis;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author heqing
 * @date 2018/8/5 18:07
 */
public class TestSortedSetRedis {

    @Test
    public void testzAdd() {
        RedisUtil.getSortedSet().zAdd("sortedSet:zadd", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zadd", 20, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zadd", 30, "test3");
        Set<String> result = RedisUtil.getSortedSet().zRange("sortedSet:zadd", 0, -1);
        System.out.println("--->result = "+result.toString());

        Map sort = new HashMap();
        sort.put("test4", 40D);
        sort.put("test5", 50D);
        sort.put("test6", 60D);
        RedisUtil.getSortedSet().zAdd("sortedSet:zadd", sort);
        result = RedisUtil.getSortedSet().zRange("sortedSet:zadd", 0, -1);
        System.out.println("--->result = "+result.toString());
    }

    @Test
    public void testzCard() {
        RedisUtil.getSortedSet().zAdd("sortedSet:card", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:card", 20, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:card", 30, "test3");
        Set<String> result = RedisUtil.getSortedSet().zRange("sortedSet:card", 0, -1);
        System.out.println("--->result = "+result.toString());

        Long length = RedisUtil.getSortedSet().zCard("sortedSet:card");
        System.out.println("--->length = "+length);

        length = RedisUtil.getSortedSet().zCount("sortedSet:card", 5, 25);
        System.out.println("--->length = "+length);
    }

    @Test
    public void testzIncrby() {
        RedisUtil.getSortedSet().zAdd("sortedSet:zIncrby", 10, "test1");
        Set<String> result = RedisUtil.getSortedSet().zRange("sortedSet:zIncrby", 0, -1);
        System.out.println("--->result = "+result.toString());

        Double score = RedisUtil.getSortedSet().zIncrby("sortedSet:card", 5, "test1");
        System.out.println("--->score = "+score);
    }

    @Test
    public void testzRange() {
        RedisUtil.getSortedSet().zAdd("sortedSet:zRange", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRange", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRange", 20, "test3");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRange", 25, "test4");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRange", 30, "test5");

        Set<String> result = RedisUtil.getSortedSet().zRange("sortedSet:zRange", 0, 2);
        System.out.println("--->result = "+result.toString());

        result = RedisUtil.getSortedSet().zRangeByScore("sortedSet:zRange", 17, 27);
        System.out.println("--->result = "+result);

        result = RedisUtil.getSortedSet().zRangeByScore("sortedSet:zRange", 12, 32, 2, 2);
        System.out.println("--->result = "+result);
    }

    @Test
    public void testzRank() {
        RedisUtil.getSortedSet().zAdd("sortedSet:zRank", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRank", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRank", 20, "test3");
        Set<String> result = RedisUtil.getSortedSet().zRange("sortedSet:zRank", 0, -1);
        System.out.println("--->result = "+result.toString());

        long index = RedisUtil.getSortedSet().zRank("sortedSet:zRank", "test1");
        System.out.println("--->index = "+index);

        index = RedisUtil.getSortedSet().zRank("sortedSet:zRank", "test3");
        System.out.println("--->index = "+index);
    }

    @Test
    public void testzRem() {
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem1", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem1", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem1", 20, "test3");
        RedisUtil.getSortedSet().zRem("sortedSet:zRem", "test1");
        Set<String> result = RedisUtil.getSortedSet().zRange("sortedSet:zRem1", 0, -1);
        System.out.println("--->result = "+result.toString());

        RedisUtil.getSortedSet().zAdd("sortedSet:zRem2", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem2", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem2", 20, "test3");
        RedisUtil.getSortedSet().zRemrangeByRank("sortedSet:zRem2", 0, 1);
        result = RedisUtil.getSortedSet().zRange("sortedSet:zRem2", 0, -1);
        System.out.println("--->result = "+result.toString());

        RedisUtil.getSortedSet().zAdd("sortedSet:zRem3", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem3", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRem3", 20, "test3");
        RedisUtil.getSortedSet().zRemrangeByScore("sortedSet:zRem3", 15, 25);
        result = RedisUtil.getSortedSet().zRange("sortedSet:zRem3", 0, -1);
        System.out.println("--->result = "+result.toString());
    }

    @Test
    public void testzRevrange() {
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange1", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange1", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange1", 20, "test3");
        Set<String> result = RedisUtil.getSortedSet().zRevrange("sortedSet:zRevrange1", 0, -1);
        System.out.println("--->result = "+result.toString());

        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange2", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange2", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange2", 20, "test3");
        result = RedisUtil.getSortedSet().zRevrangeByScore("sortedSet:zRevrange2", 20, 10);
        System.out.println("--->result = "+result.toString());

        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange3", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange3", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:zRevrange3", 20, "test3");
        Long length = RedisUtil.getSortedSet().zRevrank("sortedSet:zRevrange3", "test1");
        System.out.println("--->length = "+length);
    }

    @Test
    public void testzScore() {
        Double result = RedisUtil.getSortedSet().zScore("sortedSet:zRevrange3", "test1");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testzUnionstore() {
        RedisUtil.getSortedSet().zAdd("sortedSet:union1", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:union1", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:union1", 20, "test3");

        RedisUtil.getSortedSet().zAdd("sortedSet:union2", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:union2", 15, "test3");
        RedisUtil.getSortedSet().zAdd("sortedSet:union2", 20, "test4");

        Long length = RedisUtil.getSortedSet().zUnionstore("sortedSet:union", "sortedSet:union1", "sortedSet:union2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void testzInterstore() {
        RedisUtil.getSortedSet().zAdd("sortedSet:inters1", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:inters1", 15, "test2");
        RedisUtil.getSortedSet().zAdd("sortedSet:inters1", 20, "test3");

        RedisUtil.getSortedSet().zAdd("sortedSet:inters2", 10, "test1");
        RedisUtil.getSortedSet().zAdd("sortedSet:inters2", 15, "test3");
        RedisUtil.getSortedSet().zAdd("sortedSet:inters2", 20, "test4");

        Long length = RedisUtil.getSortedSet().zInterstore("sortedSet:inters", "sortedSet:inters1", "sortedSet:inters2");
        System.out.println("--->length = "+length);
    }
}
