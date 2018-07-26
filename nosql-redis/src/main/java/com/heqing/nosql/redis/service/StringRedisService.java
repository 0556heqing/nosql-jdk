package com.heqing.nosql.redis.service;

import redis.clients.jedis.BitOP;

import java.util.List;

/**
 * 字符串
 * @see <a href="http://redisdoc.com/string/index.html">Redis命令 -> String（字符串）</a>
 * @author heqing
 * @date 2018/7/19
 */
public interface StringRedisService extends KeyRedisService {

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     *
     * @see <a href="http://redisdoc.com/string/append.html">String(字符串) -> APPEND</a>
     * @param key 主键名
     * @param value 值
     * @return 追加 value 之后， key 中字符串的长度。
     */
    Long append(String key, String value);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     * 不存在的 key 被当成是空字符串来处理，因此对一个不存在的 key 进行 BITCOUNT 操作，结果为 0 。
     *
     * @see <a href="http://redisdoc.com/string/bitcount.html">String(字符串) -> BITCOUNT</a>
     * @param key 主键名
     * @return 被设置为 1 的位的数量。
     */
    Long bitCount(String key);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     * 一般情况下，给定的整个字符串都会被进行计数，通过指定额外的 start 或 end 参数，可以让计数只在特定的位上进行。
     * start 和 end 参数的设置和 GETRANGE 命令类似，都可以使用负数值： 比如 -1 表示最后一个字节， -2 表示倒数第二个字节，以此类推。
     * 不存在的 key 被当成是空字符串来处理，因此对一个不存在的 key 进行 BITCOUNT 操作，结果为 0 。
     *
     * @see <a href="http://redisdoc.com/string/bitcount.html">String(字符串) -> BITCOUNT</a>
     * @param key 主键名
     * @param start 开始值
     * @param end 结束值
     * @return 被设置为 1 的位的数量。
     */
    Long bitCount(String key, long start, long end);

    /**
     * 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destkey 上。
     * 除了 NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入。
     *
     * @see <a href="http://redisdoc.com/string/bitop.html">String(字符串) -> BITOP</a>
     * @param op AND:逻辑并, OR:逻辑或, XOR:逻辑异或, NOT :逻辑非
     * @param destKey 保存key
     * @param srcKeys 操作的key
     * @return 保存到 destkey 的字符串的长度，和输入 key 中最长的字符串长度相等。
     */
    Long bitop(BitOP op, String destKey, String... srcKeys);

    /**
     * 可以将一个 Redis 字符串看作是一个由二进制位组成的数组， 并对这个数组中储存的长度不同的整数进行访问
     * （被储存的整数无需进行对齐）。 换句话说， 通过这个命令， 用户可以执行诸如 “对偏移量 1234 上的 5 位
     * 长有符号整数进行设置”、 “获取偏移量 4567 上的 31 位长无符号整数”等操作。 此外， BITFIELD 命令还可以
     * 对指定的整数执行加法操作和减法操作， 并且这些操作可以通过设置妥善地处理计算时出现的溢出情况。<br/>
     * BITFIELD 命令可以在一次调用中同时对多个位范围进行操作： 它接受一系列待执行的操作作为参数，
     * 并返回一个数组作为回复， 数组中的每个元素就是对应操作的执行结果。
     *
     * @see <a href="http://redisdoc.com/string/bitfield.html">String(字符串) -> BITFIELD</a>
     * @param key 主键
     * @param arguments 子命令
     * @return 一个数组， 数组中的每个元素对应一个被执行的子命令。 需要注意的是， OVERFLOW 子命令本身并不产生任何回复。
     */
    List<Long> bitfield(String key, String... arguments);

    /**
     * 将 key 中储存的数字值减一。<br/>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。<br/>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
     * 关于递增(increment) / 递减(decrement)操作的更多信息，请参见 INCR 命令。
     *
     * @see <a href="http://redisdoc.com/string/decr.html">String(字符串) -> DECR</a>
     * @param key 主键
     * @return 执行 DECR 命令之后 key 的值。
     */
    Long decr(String key);

    /**
     * 将 key 所储存的值减去减量 decrement 。<br/>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。<br/>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
     * 关于更多递增(increment) / 递减(decrement)操作的更多信息，请参见 INCR 命令。
     *
     * @see <a href="http://redisdoc.com/string/decrby.html">String(字符串) -> DECRBY</a>
     * @param key 主键
     * @return 减去 decrement 之后， key 的值。
     */
    Long decrby(String key, long decrement);

    /**
     * 返回 key 所关联的字符串值。<br/>
     * 如果 key 不存在那么返回特殊值 nil 。<br/>
     * 假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     *
     * @see <a href="http://redisdoc.com/string/get.html">String(字符串) -> GET</a>
     * @param key 主键
     * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。
     */
    String get(String key);

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。<br/>
     * 当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。
     *
     * @see <a href="http://redisdoc.com/string/getbit.html">String(字符串) -> GETBIT</a>
     * @param key 主键
     * @param offset 偏移量
     * @return 字符串值指定偏移量上的位(bit)。
     */
    Boolean getbit(String key, long offset);
}
