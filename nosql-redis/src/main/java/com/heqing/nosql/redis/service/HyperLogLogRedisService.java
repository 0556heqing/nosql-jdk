package com.heqing.nosql.redis.service;

/**
 * HyperLogLog
 * @see <a href="http://www.runoob.com/redis/redis-hyperloglog.html">Redis命令 -> HyperLogLog</a>
 * @author heqing
 * @date 2018/7/30 16:14
 */
public interface HyperLogLogRedisService extends KeyRedisService {

    /**
     * 将任意数量的元素添加到指定的 HyperLogLog 里面。<br/>
     * 作为这个命令的副作用， HyperLogLog 内部可能会被更新， 以便反映一个不同的唯一元素估计数量（也即是集合的基数）。<br/>
     * 如果 HyperLogLog 估计的近似基数（approximated cardinality）在命令执行之后出现了变化， 那么命令返回 1 ， 否则返回 0 。 如果命令执行时给定的键不存在， 那么程序将先创建一个空的 HyperLogLog 结构， 然后再执行命令。<br/>
     * 调用 PFADD 命令时可以只给定键名而不给定元素：
     * <ul>
     *     <li>如果给定键已经是一个 HyperLogLog ， 那么这种调用不会产生任何效果；</li>
     *     <li>但如果给定的键不存在， 那么命令会创建一个空的 HyperLogLog ， 并向客户端返回 1 。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/hyperloglog/pfadd.html">HyperLogLog -> pfadd</a>
     * @param key 主键名
     * @param elements 元素
     * @return 如果 HyperLogLog 的内部储存被修改了， 那么返回 true ， 否则返回 false 。
     */
    Boolean pfAdd(final String key, final String... elements);

    /**
     * 当 PFCOUNT 命令作用于单个键时， 返回储存在给定键的 HyperLogLog 的近似基数， 如果键不存在， 那么返回 0 。<br/>
     * 当 PFCOUNT 命令作用于多个键时， 返回所有给定 HyperLogLog 的并集的近似基数， 这个近似基数是通过将所有给定 HyperLogLog 合并至一个临时 HyperLogLog 来计算得出的。<br/>
     * 通过 HyperLogLog 数据结构， 用户可以使用少量固定大小的内存， 来储存集合中的唯一元素 （每个 HyperLogLog 只需使用 12k 字节内存，以及几个字节的内存来储存键本身）。<br/>
     * 命令返回的可见集合（observed set）基数并不是精确值， 而是一个带有 0.81% 标准错误（standard error）的近似值。<br/>
     * 举个例子， 为了记录一天会执行多少次各不相同的搜索查询， 一个程序可以在每次执行搜索查询时调用一次 PFADD ， 并通过调用 PFCOUNT 命令来获取这个记录的近似结果。
     *
     * @see <a href="http://redisdoc.com/hyperloglog/pfcount.html">HyperLogLog -> pfcount</a>
     * @param keys 主键名
     * @return 给定 HyperLogLog 包含的唯一元素的近似数量。
     */
    Long pfCount(String... keys);

    /**
     * 将多个 HyperLogLog 合并（merge）为一个 HyperLogLog ， 合并后的 HyperLogLog 的基数接近于所有输入 HyperLogLog 的可见集合（observed set）的并集。<br/>
     * 合并得出的 HyperLogLog 会被储存在 destkey 键里面， 如果该键并不存在， 那么命令在执行之前， 会先为该键创建一个空的 HyperLogLog 。
     *
     * @see <a href="http://redisdoc.com/hyperloglog/pfmerge.html">HyperLogLog -> pfmerge</a>
     * @param destkey 合并后的key
     * @param sourcekeys 需要合并的key
     * @return 返回 true
     */
    Boolean pfMerge(String destkey, String... sourcekeys);
}
