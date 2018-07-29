package com.heqing.nosql.redis.service;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Set;

/**
 * 集合
 * @see <a href="http://redisdoc.com/set/index.html">Redis命令 -> Set（集合）</a>
 * @author heqing
 * @date 2018/7/19
 */
public interface SetRedisService {

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。<br/>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。<br/>
     * 当 key 不是集合类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/set/sadd.html">Set（集合） -> sadd</a>
     * @param key 主键名
     * @param members 需要添加的元素
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
     */
    Long sAdd(String key, String... members);

    /**
     * 返回集合 key 的基数(集合中元素的数量)。
     *
     * @see <a href="http://redisdoc.com/set/scard.html">Set（集合） -> scard</a>
     * @param key 主键名
     * @return 集合的基数。当 key 不存在时，返回 0 。
     */
    Long sard(String key);

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。<br/>
     * 不存在的 key 被视为空集。
     *
     * @see <a href="http://redisdoc.com/set/sdiff.html">Set（集合） -> sdiff</a>
     * @param keys 多个主键名
     * @return 一个包含差集成员的列表。
     */
    Set<String> sDiff(String... keys);

    /**
     * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。<br/>
     * destination 可以是 key 本身。如果 destination 集合已经存在，则将其覆盖。
     *
     * @see <a href="http://redisdoc.com/set/sdiffstore.html">Set（集合） -> sdiffstore</a>
     * @param destination 保存的key
     * @param keys 多个主键名
     * @return 结果集中的元素数量。
     */
    Long sDifftore(String destination, String... keys);

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合的交集。<br/>
     * 不存在的 key 被视为空集。<br/>
     * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     *
     * @see <a href="http://redisdoc.com/set/sinter.html">Set（集合） -> sinter</a>
     * @param keys 多个主键名
     * @return 交集成员的列表。
     */
    Set<String> sInter(String... keys);

    /**
     * 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。<br/>
     * 如果 destination 集合已经存在，则将其覆盖。<br/>
     * destination 可以是 key 本身。
     *
     * @see <a href="http://redisdoc.com/set/sinterstore.html">Set（集合） -> sinterstore</a>
     * @param destination 保存的key
     * @param keys 多个主键名
     * @return 结果集中的元素数量。
     */
    Long sInterStore(String destination, String... keys);

    /**
     * 判断 member 元素是否集合 key 的成员。
     *
     * @see <a href="http://redisdoc.com/set/sismember.html">Set（集合） -> sismember</a>
     * @param key 主键名
     * @param member 元素名
     * @return 如果 member 元素是集合的成员，返回 true。如果 member 元素不是集合的成员，或 key 不存在，返回 false 。
     */
    Boolean sisMember(String key, String member);

    /**
     * 返回集合 key 中的所有成员。<br/>
     * 不存在的 key 被视为空集合。
     *
     * @see <a href="http://redisdoc.com/set/smembers.html">Set（集合） -> smembers</a>
     * @param key 主键名
     * @return 集合中的所有成员。
     */
    Set<String> sMember(String key);

    /**
     * 将 member 元素从 source 集合移动到 destination 集合。<br/>
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。
     * 否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。<br/>
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。<br/>
     * 当 source 或 destination 不是集合类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/set/smove.html">Set（集合） -> smove</a>
     * @param srckey 源集合
     * @param dstkey 目标集合
     * @param member 元素名
     * @return 如果 member 元素被成功移除，返回 true 。<br/>
     *         如果 member 元素不是 source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回 0 。
     */
    Boolean sMove(String srckey, String dstkey, String member);

    /**
     * 移除并返回集合中的一个随机元素。<br/>
     * 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 SRANDMEMBER 命令。
     *
     * @see <a href="http://redisdoc.com/set/spop.html">Set（集合） -> spop</a>
     * @param key 主键名
     * @return 被移除的随机元素。当 key 不存在或 key 是空集时，返回 nil 。
     */
    String sPop(String key);

    /**
     * 移除并返回集合中的一个随机元素。<br/>
     * 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 SRANDMEMBER 命令。
     *
     * @see <a href="http://redisdoc.com/set/spop.html">Set（集合） -> spop</a>
     * @param key 主键名
     * @param count 移除的数量
     * @return 被移除的随机元素集合。当 key 不存在或 key 是空集时，返回 nil 。
     */
    Set<String> sPop(String key, long count);

    /**
     * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。<br/>
     * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
     *
     * @see <a href="http://redisdoc.com/set/srandmember.html">Set（集合） -> srandmember</a>
     * @param key 主键名
     * @return 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。<br/>
     *         如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组。
     */
    String sRandember(String key);

    /**
     * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。<br/>
     * <ul>
     *     <li>如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。
     *         如果 count 大于等于集合基数，那么返回整个集合。</li>
     *     <li>如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。</li>
     * </ul>
     * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
     *
     * @see <a href="http://redisdoc.com/set/srandmember.html">Set（集合） -> srandmember</a>
     * @param key 主键名
     * @param count 正负数
     * @return 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。<br/>
     *         如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组。
     */
    List<String> sRandember(String key, int count);

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。<br/>
     * 当 key 不是集合类型，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/set/srem.html">Set（集合） -> srem</a>
     * @param key 主键名
     * @param members 元素名
     * @return 被成功移除的元素的数量，不包括被忽略的元素。
     */
    Long sRem(String key, String... members);

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合的并集。<br/>
     * 不存在的 key 被视为空集。
     *
     * @see <a href="http://redisdoc.com/set/sunion.html">Set（集合） -> sunion</a>
     * @param keys 主键名
     * @return 并集成员的列表。
     */
    Set<String> sUnion(String... keys);

    /**
     * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。<br/>
     * destination 可以是 key 本身。如果 destination 已经存在，则将其覆盖。
     *
     * @see <a href="http://redisdoc.com/set/sunionstore.html">Set（集合） -> sunionstore</a>
     * @param destination 保存的key
     * @param keys 主键名
     * @return 结果集中的元素数量。
     */
    Long sUnionStore(String destination, String... keys);

    /**
     * 迭代集合键中的元素。
     *
     * @see <a href="http://redisdoc.com/set/sscan.html">Set（集合） -> sscan</a>
     * @param key 主键
     * @param cursor 光标
     * @param params 排序条件，默认可传null
     * @return 返回的每个元素都是一个集合成员。
     */
    ScanResult<String> sScan(String key, String cursor, ScanParams params);
}
