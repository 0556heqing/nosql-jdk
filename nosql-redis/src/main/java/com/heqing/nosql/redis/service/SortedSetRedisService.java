package com.heqing.nosql.redis.service;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.util.Map;
import java.util.Set;

/**
 * 有序集合
 * @see <a href="http://redisdoc.com/sorted_set/index.html">Redis命令 -> SortedSet（有序集合）</a>
 * @author heqing
 * @date 2018/7/19
 */
public interface SortedSetRedisService {

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br/>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br/>
     * score 值可以是整数值或双精度浮点数。<br/>
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br/>
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zadd.html">SortedSet（有序集合） -> zadd</a>
     * @param key 主键
     * @param score 分值
     * @param member 成员
     * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
     */
    Long zAdd(String key, double score, String member);

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br/>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br/>
     * score 值可以是整数值或双精度浮点数。<br/>
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br/>
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zadd.html">SortedSet（有序集合） -> zadd</a>
     * @param key 主键
     * @param scoreMembers 成员-分值
     * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
     */
    Long zAdd(String key, Map<String, Double> scoreMembers);

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br/>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br/>
     * score 值可以是整数值或双精度浮点数。<br/>
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br/>
     * 当 key 存在但不是有序集类型时，返回一个错误。<br/>
     * ZAddParams 参数解析如下：
     * <ul>
     *     <li>XX: 仅仅更新存在的成员，不添加新成员。</li>
     *     <li>NX: 不更新存在的成员。只添加新成员。</li>
     *     <li>CH: 修改返回值为发生变化的成员总数，原始是返回新添加成员的总数 (CH 是 changed 的意思)。更改的元素是新添加的成员，已经存在的成员更新分数。
     *     所以在命令中指定的成员有相同的分数将不被计算在内。注：在通常情况下，ZADD返回值只计算新添加成员的数量。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/sorted_set/zadd.html">SortedSet（有序集合） -> zadd</a>
     * @param key 主键
     * @param score 分值
     * @param member 成员
     * @param params 默认设置为null
     * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
     */
    Long zAdd(String key, double score, String member, ZAddParams params);

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br/>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br/>
     * score 值可以是整数值或双精度浮点数。<br/>
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br/>
     * 当 key 存在但不是有序集类型时，返回一个错误。<br/>
     * ZAddParams 参数解析如下：
     * <ul>
     *     <li>XX: 仅仅更新存在的成员，不添加新成员。</li>
     *     <li>NX: 不更新存在的成员。只添加新成员。</li>
     *     <li>CH: 修改返回值为发生变化的成员总数，原始是返回新添加成员的总数 (CH 是 changed 的意思)。更改的元素是新添加的成员，已经存在的成员更新分数。
     *     所以在命令中指定的成员有相同的分数将不被计算在内。注：在通常情况下，ZADD返回值只计算新添加成员的数量。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/sorted_set/zadd.html">SortedSet（有序集合） -> zadd</a>
     * @param key 主键
     * @param scoreMembers 成员-分值
     * @param params 默认设置为null
     * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
     */
    Long zAdd(String key, Map<String, Double> scoreMembers, ZAddParams params);

    /**
     * 返回有序集 key 的基数。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zcard.html">SortedSet（有序集合） -> zcard</a>
     * @param key 主键
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。当 key 不存在时，返回 0 。
     */
    Long zCard(String key);

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zcount.html">SortedSet（有序集合） -> zcount</a>
     * @param key 主键
     * @param min 最小分数
     * @param max 最大分数
     * @return score 值在 min 和 max 之间的成员的数量。
     */
    Long zCount(String key, double min, double max);

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。<br/>
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。<br/>
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。<br/>
     * 当 key 不是有序集类型时，返回一个错误。<br/>
     * score 值可以是整数值或双精度浮点数。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zincrby.html">SortedSet（有序集合） -> zincrby</a>
     * @param key 主键
     * @param score 分数
     * @param member 成员
     * @return 增加后的分数值
     */
    Double zIncrby(String key, double score, String member);

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。<br/>
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。<br/>
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。<br/>
     * 当 key 不是有序集类型时，返回一个错误。<br/>
     * score 值可以是整数值或双精度浮点数。<br/>
     * ZIncrByParams 参数解析如下：
     * <ul>
     *     <li>XX: 仅仅更新存在的成员，不添加新成员。</li>
     *     <li>NX: 不更新存在的成员。只添加新成员。</li>
     *     <li>INCR: 对成员的分数进行递增操作。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/sorted_set/zincrby.html">SortedSet（有序集合） -> zincrby</a>
     * @param key 主键
     * @param score 分数
     * @param member 成员
     * @param params 条件
     * @return 增加后的分数值
     */
    Double zIncrby(String key, double score, String member, ZIncrByParams params);

    /**
     * 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递增(从小到大)来排序。<br/>
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。<br/>
     * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。<br/>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br/>
     * 超出范围的下标并不会引起错误。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrange.html">SortedSet（有序集合） -> zrange</a>
     * @param key 主键
     * @param start 开始下标
     * @param end 结束下标
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRange(String key, long start, long end);

    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。<br/>
     * 具有相同 score 值的成员按字典序(lexicographical order)来排列(该属性是有序集提供的，不需要额外的计算)。<br/>
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrangebyscore.html">SortedSet（有序集合） -> zrangebyscore</a>
     * @param key 主键
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRangeByScore(String key, double min, double max);

    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。<br/>
     * 具有相同 score 值的成员按字典序(lexicographical order)来排列(该属性是有序集提供的，不需要额外的计算)。<br/>
     * 可选的 LIMIT 参数指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间。<br/>
     * 可选的 WITHSCORES 参数决定结果集是单单返回有序集的成员，还是将有序集成员及其 score 值一起返回。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrangebyscore.html">SortedSet（有序集合） -> zrangebyscore</a>
     * @param key 主键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 从第几个开始
     * @param count 数量
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRangeByScore(String key, double min, double max, int offset, int count);

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。<br/>
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。<br/>
     * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrank.html">SortedSet（有序集合） -> zrank</a>
     * @param key 主键
     * @param member 成员
     * @return 如果 member 是有序集 key 的成员，返回 member 的排名。<br/>
     *         如果 member 不是有序集 key 的成员，返回 nil 。
     */
    Long zRank(String key, String member);

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。<br/>
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrem.html">SortedSet（有序集合） -> zrem</a>
     * @param key 主键
     * @param members 多个成员
     * @return 被成功移除的成员的数量，不包括被忽略的成员。
     */
    Long zRem(String key, String... members);

    /**
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员。<br/>
     * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。<br/>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html">SortedSet（有序集合） -> zremrangebyrank</a>
     * @param key 主键
     * @param start 开始下标
     * @param end 结束下标
     * @return 被成功移除的成员的数量。
     */
    Long zRemrangeByRank(String key, long start, long end);

    /**
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
     * 自版本2.1.6开始， score 值等于 start end 的成员也可以不包括在内，详情请参见 ZRANGEBYSCORE 命令。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html">SortedSet（有序集合） -> zremrangebyscore</a>
     * @param key 主键
     * @param start 开始分数值
     * @param end 结束分数值
     * @return 被成功移除的成员的数量。
     */
    Long zRemrangeByScore(String key, double start, double end);

    /**
     * 对于一个所有成员的分值都相同的有序集合键 key 来说， 这个命令会移除该集合中， 成员介于 min 和 max 范围内的所有元素。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zremrangebylex.html">SortedSet（有序集合） -> zremrangebylex</a>
     * @param key 主键
     * @param min 成员名
     * @param max 成员名
     * @return 被成功移除的成员的数量。
     */
    Long zRemrangeByLex(String key, String min, String max);

    /**
     * 返回有序集 key 中，指定区间内的成员。<br/>
     * 其中成员的位置按 score 值递减(从大到小)来排列。具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。<br/>
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrevrange.html">SortedSet（有序集合） -> zrevrange</a>
     * @param key 主键
     * @param start 开始分数值
     * @param end 结束分数值
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRevrange(String key, long start, long end);

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。<br/>
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。<br/>
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html">SortedSet（有序集合） -> zrevrangebyscore</a>
     * @param key 主键
     * @param max 最大分数值
     * @param min 最小分数值
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRevrangeByScore(String key, double max, double min);

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。<br/>
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。<br/>
     * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrevrank.html">SortedSet（有序集合） -> zrevrank</a>
     * @param key 主键
     * @param member 成员
     * @return 如果 member 是有序集 key 的成员，返回 member 的排名。
     *         如果 member 不是有序集 key 的成员，返回 nil 。
     */
    Long zRevrank(String key, String member);

    /**
     * 返回有序集 key 中，成员 member 的 score 值。<br/>
     * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zscore.html">SortedSet（有序集合） -> zscore</a>
     * @param key 主键
     * @param member 成员
     * @returnmember 成员的 score 值，以字符串形式表示。
     */
    Double zScore(String key, String member);

    /**
     * 计算给定的一个或多个有序集的并集，其中给定 key 的数量必须以 numkeys 参数指定，并将该并集(结果集)储存到 destination 。<br/>
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之 和 。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zunionstore.html">SortedSet（有序集合） -> zunionstore</a>
     * @param dstkey 目标集合
     * @param sets 的一个或多个有序集
     * @returnmember 保存到 dstkey 的结果集的基数。
     */
    Long zUnionstore(String dstkey, String... sets);

    /**
     * 计算给定的一个或多个有序集的并集，其中给定 key 的数量必须以 numkeys 参数指定，并将该并集(结果集)储存到 destination 。<br/>
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之 和 。<br/>
     * <b>WEIGHTS</b> <br/>
     * 使用 WEIGHTS 选项，你可以为 每个 给定有序集 分别 指定一个乘法因子(multiplication factor)，
     * 每个给定有序集的所有成员的 score 值在传递给聚合函数(aggregation function)之前都要先乘以该有序集的因子。<br/>
     * 如果没有指定 WEIGHTS 选项，乘法因子默认设置为 1 。
     * <b>AGGREGATE</b> <br/>
     * 使用 AGGREGATE 选项，你可以指定并集的结果集的聚合方式。<br/>
     * 默认使用的参数 SUM ，可以将所有集合中某个成员的 score 值之 和 作为结果集中该成员的 score 值；
     * 使用参数 MIN ，可以将所有集合中某个成员的 最小 score 值作为结果集中该成员的 score 值；
     * 而参数 MAX 则是将所有集合中某个成员的 最大 score 值作为结果集中该成员的 score 值。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zunionstore.html">SortedSet（有序集合） -> zunionstore</a>
     * @param dstkey 目标集合
     * @param params 条件
     * @param sets 的一个或多个有序集
     * @returnmember 保存到 dstkey 的结果集的基数。
     */
    Long zUnionstore(String dstkey, ZParams params, String... sets);

    /**
     * 计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 destination 。<br/>
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zinterstore.html">SortedSet（有序集合） -> zinterstore</a>
     * @param dstkey 目标集合
     * @param sets 的一个或多个有序集
     * @returnmember 保存到 dstkey 的结果集的基数。
     */
    Long zInterstore(String dstkey, String... sets);

    /**
     * 计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 destination 。<br/>
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和。<br/>
     * <b>WEIGHTS</b> <br/>
     * 使用 WEIGHTS 选项，你可以为 每个 给定有序集 分别 指定一个乘法因子(multiplication factor)，
     * 每个给定有序集的所有成员的 score 值在传递给聚合函数(aggregation function)之前都要先乘以该有序集的因子。<br/>
     * 如果没有指定 WEIGHTS 选项，乘法因子默认设置为 1 。
     * <b>AGGREGATE</b> <br/>
     * 使用 AGGREGATE 选项，你可以指定并集的结果集的聚合方式。<br/>
     * 默认使用的参数 SUM ，可以将所有集合中某个成员的 score 值之 和 作为结果集中该成员的 score 值；
     * 使用参数 MIN ，可以将所有集合中某个成员的 最小 score 值作为结果集中该成员的 score 值；
     * 而参数 MAX 则是将所有集合中某个成员的 最大 score 值作为结果集中该成员的 score 值。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zinterstore.html">SortedSet（有序集合） -> zinterstore</a>
     * @param dstkey 目标集合
     * @param sets 的一个或多个有序集
     * @param params 条件
     * @returnmember 保存到 dstkey 的结果集的基数。
     */
    Long zInterstore(String dstkey, ZParams params, String... sets);

    /**
     * 迭代有序集合中的元素（包括元素成员和元素分值）。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zscan.html">SortedSet（有序集合） -> zScan</a>
     * @param key 主键
     * @param cursor 光标
     * @param params 排序条件，默认可传null
     * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
     */
    ScanResult<Tuple> zScan(String key, String cursor, ScanParams params);

    /**
     * 当有序集合的所有成员都具有相同的分值时， 有序集合的元素会根据成员的字典序（lexicographical ordering）来进行排序，
     * 而这个命令则可以返回给定的有序集合键 key 中， 值介于 min 和 max 之间的成员。<br/>
     * 如果有序集合里面的成员带有不同的分值， 那么命令返回的结果是未指定的（unspecified）。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrangebylex.html">SortedSet（有序集合） -> zrangebylex</a>
     * @param key 主键
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRangeByLex(String key, String min, String max);

    /**
     * 当有序集合的所有成员都具有相同的分值时， 有序集合的元素会根据成员的字典序（lexicographical ordering）来进行排序，
     * 而这个命令则可以返回给定的有序集合键 key 中， 值介于 min 和 max 之间的成员。<br/>
     * 如果有序集合里面的成员带有不同的分值， 那么命令返回的结果是未指定的（unspecified）。
     * 可选的 LIMIT 参数指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间。<br/>
     * 可选的 WITHSCORES 参数决定结果集是单单返回有序集的成员，还是将有序集成员及其 score 值一起返回。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zrangebylex.html">SortedSet（有序集合） -> zrangebylex</a>
     * @param key 主键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 从第几个开始
     * @param count 数量
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    Set<String> zRangeByLex(String key, String min, String max, int offset, int count);

    /**
     * 对于一个所有成员的分值都相同的有序集合键 key 来说， 这个命令会返回该集合中， 成员介于 min 和 max 范围内的元素数量。<br/>
     * 这个命令的 min 参数和 max 参数的意义和 ZRANGEBYLEX 命令的 min 参数和 max 参数的意义一样。
     *
     * @see <a href="http://redisdoc.com/sorted_set/zlexcount.html">SortedSet（有序集合） -> zlexcount</a>
     * @param key 主键
     * @param min 成员名
     * @param max 成员名
     * @return 指定范围内的元素数量。
     */
    Long zLexCount(String key, String min, String max);
}
