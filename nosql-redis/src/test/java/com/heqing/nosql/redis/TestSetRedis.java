package com.heqing.nosql.redis;

import org.junit.Test;

import java.util.Set;

/**
 * @author heqing
 * @date 2018/8/5 15:57
 */
public class TestSetRedis {

    @Test
    public void testsAdd() {
        RedisUtil.getSet().sAdd("sort:sadd", "test1", "test2", "test3");
        Set<String> result = RedisUtil.getSet().sMember("sort:sadd");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testsCard() {
        Long result = RedisUtil.getSet().sCard("sort:sadd");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testsDiff() {
        RedisUtil.getSet().sAdd("sort:sDiff1", "test1", "test2", "test3");
        RedisUtil.getSet().sAdd("sort:sDiff2", "test1", "test3", "test4");
        Set<String> result = RedisUtil.getSet().sDiff("sort:sDiff1", "sort:sDiff2");
        System.out.println("--->result = "+result);

        Long length = RedisUtil.getSet().sDifftore("sort:sDiff", "sort:sDiff1", "sort:sDiff2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void testsInter() {
        RedisUtil.getSet().sAdd("sort:sInter1", "test1", "test2", "test3");
        RedisUtil.getSet().sAdd("sort:sInter2", "test1", "test3", "test4");
        Set<String> result = RedisUtil.getSet().sInter("sort:sInter1", "sort:sInter2");
        System.out.println("--->result = "+result);

        Long length = RedisUtil.getSet().sInterStore("sort:sInter", "sort:sInter1", "sort:sInter2");
        System.out.println("--->length = "+length);
    }

    @Test
    public void testsisMember() {
        RedisUtil.getSet().sAdd("sort:sisMember", "test1", "test2", "test3");
        Boolean isMember = RedisUtil.getSet().sisMember("sort:sisMember", "test1");
        System.out.println("--->isMember = "+isMember);

        Set<String> member = RedisUtil.getSet().sMember("sort:sisMember");
        System.out.println("--->member = "+member.toString());
    }

    @Test
    public void testsMove() {
        RedisUtil.getSet().sAdd("sort:move1", "test1", "test2", "test3");
        RedisUtil.getSet().sMove("sort:move1", "sort:move2", "test1");
    }

    @Test
    public void testsPop() {
        RedisUtil.getSet().sAdd("sort:pop", "test1", "test2", "test3", "test4", "test5");
        Set<String> member = RedisUtil.getSet().sMember("sort:pop");
        System.out.println("--->member = "+member.toString());

        RedisUtil.getSet().sPop("sort:pop");
        member = RedisUtil.getSet().sMember("sort:pop");
        System.out.println("--->member = "+member.toString());

        RedisUtil.getSet().sPop("sort:pop", 2);
        member = RedisUtil.getSet().sMember("sort:pop");
        System.out.println("--->member = "+member.toString());
    }

    @Test
    public void testsRandember() {
        RedisUtil.getSet().sAdd("sort:randember", "test1", "test2", "test3", "test4", "test5");
        Set<String> member = RedisUtil.getSet().sMember("sort:randember");
        System.out.println("--->member = "+member.toString());

        System.out.println("--->member = "+RedisUtil.getSet().sRandember("sort:randember"));

        System.out.println("--->member = "+RedisUtil.getSet().sRandember("sort:randember", 2));
    }

    @Test
    public void testsRem() {
        RedisUtil.getSet().sAdd("sort:rem", "test1", "test2", "test3", "test4", "test5");
        Long length = RedisUtil.getSet().sRem("sort:rem", "test1", "test2");
        System.out.println("--->length = "+length);

        Set<String> member = RedisUtil.getSet().sMember("sort:rem");
        System.out.println("--->member = "+member.toString());
    }

    @Test
    public void testsUnion() {
        RedisUtil.getSet().sAdd("sort:sUnion1", "test1", "test2", "test3");
        RedisUtil.getSet().sAdd("sort:sUnion2", "test1", "test3", "test4");
        Set<String> result = RedisUtil.getSet().sUnion("sort:sUnion1", "sort:sUnion2");
        System.out.println("--->result = "+result);

        Long length = RedisUtil.getSet().sUnionStore("sort:sUnion", "sort:sUnion1", "sort:sUnion2");
        System.out.println("--->length = "+length);
    }
}
