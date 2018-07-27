package com.heqing.nosql.redis.service;

import redis.clients.jedis.Client;

import java.util.List;

/**
 * 列表
 * @see <a href="http://redisdoc.com/list/index.html">Redis命令 -> List（列表）</a>
 * @author heqing
 * @date 2018/7/19
 */
public interface ListRedisService extends KeyRedisService {

    /**
     * BLPOP 是列表的阻塞式(blocking)弹出原语。<br/>
     * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br/>
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。
     *
     * @see <a href="http://redisdoc.com/list/blpop.html">List（列表） -> blpop</a>
     * @param args 主键名
     * @param args 多个域名
     * @return 如果列表为空，返回一个 nil 。<br/>
     *         否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     */
    List<String> bLpop(String... args);

    /**
     * BLPOP 是列表的阻塞式(blocking)弹出原语。<br/>
     * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br/>
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。
     *
     * @see <a href="http://redisdoc.com/list/blpop.html">List（列表） -> blpop</a>
     * @param timeout 超时时间
     * @param keys 多个主键名
     * @return 如果列表为空，返回一个 nil 。<br/>
     *         否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     */
    List<String> bLpop(int timeout, String... keys);

    /**
     * BRPOP 是列表的阻塞式(blocking)弹出原语。<br/>
     * 它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br/>
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素。
     *
     * @see <a href="http://redisdoc.com/list/brpop.html">List（列表） -> brpop</a>
     * @param args 主键名
     * @param args 多个域名
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。<br/>
     *         反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     */
    List<String> bRpop(String... args);

    /**
     * BRPOP 是列表的阻塞式(blocking)弹出原语。<br/>
     * 它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br/>
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素。
     *
     * @see <a href="http://redisdoc.com/list/brpop.html">List（列表） -> brpop</a>
     * @param timeout 超时时间
     * @param keys 多个主键名
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。<br/>
     *         反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     */
    List<String> bRpop(int timeout, String... keys);

    /**
     * BRPOPLPUSH 是 RPOPLPUSH 的阻塞版本，当给定列表 source 不为空时， BRPOPLPUSH 的表现和 RPOPLPUSH 一样。<br/>
     * 当列表 source 为空时， BRPOPLPUSH 命令将阻塞连接，直到等待超时，或有另一个客户端对 source 执行 LPUSH 或 RPUSH 命令为止。<br/>
     * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
     *
     * @see <a href="http://redisdoc.com/list/brpoplpush.html">List（列表） -> brpoplpush</a>
     * @param source 源key
     * @param destination 目标key
     * @param timeout 超时时间
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。<br/>
     *         反之，返回一个含有两个元素的列表，第一个元素是被弹出元素的值，第二个元素是等待时长。
     */
    String bRpopLpush(String source, String destination, int timeout);

    /**
     * 返回列表 key 中，下标为 index 的元素。<br/>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br/>
     * 如果 key 不是列表类型，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/lindex.html">List（列表） -> lindex</a>
     * @param key 主键
     * @param index 下标
     * @return 列表中下标为 index 的元素。<br/>
     *         如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
     */
    String lIndex(String key, long index);

    /**
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。<br/>
     * 当 pivot 不存在于列表 key 时，不执行任何操作。<br/>
     * 当 key 不存在时， key 被视为空列表，不执行任何操作。<br/>
     * 如果 key 不是列表类型，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/linsert.html">List（列表） -> linsert</a>
     * @param key 主键
     * @param where 插入位置，之前、之后
     * @param pivot 参照点
     * @param value 值
     * @return 如果命令执行成功，返回插入操作完成之后，列表的长度。<br/>
     *         如果没有找到 pivot ，返回 -1 。<br/>
     *         如果 key 不存在或为空列表，返回 0 。
     */
    Long lInsert(String key, Client.LIST_POSITION where, String pivot, String value);

    /**
     * 返回列表 key 的长度。<br/>
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 。<br/>
     * 如果 key 不是列表类型，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/llen.html">List（列表） -> llen</a>
     * @param key 主键
     * @return 列表 key 的长度。
     */
    Long lLen(String key);

    /**
     * 移除并返回列表 key 的头元素。
     *
     * @see <a href="http://redisdoc.com/list/lpop.html">List（列表） -> lpop</a>
     * @param key 主键
     * @return 列表的头元素。当 key 不存在时，返回 nil 。
     */
    String lPop(String key);

    /**
     * 将一个或多个值 value 插入到列表 key 的表头。<br/>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，
     * 列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。<br/>
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。<br/>
     * 当 key 存在但不是列表类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/lpush.html">List（列表） -> lpush</a>
     * @param key 主键
     * @param string 一个或多个值
     * @return 列表的长度。
     */
    Long lPush(String key, String... string);

    /**
     * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。<br/>
     * 和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
     *
     * @see <a href="http://redisdoc.com/list/lpushx.html">List（列表） -> lpushx</a>
     * @param key 主键
     * @param string 一个或多个值
     * @return 列表的长度。
     */
    Long lPushX(String key, String... string);

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。<br/>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @see <a href="http://redisdoc.com/list/lrange.html">List（列表） -> lrange</a>
     * @param key 主键
     * @param start 开始下标
     * @param end 结束下标
     * @return 包含指定区间内的元素的列表。
     */
    List<String> lRange(String key, long start, long end);

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。<br/>
     * count 的值可以是以下几种：
     *  <ul>
     *      <li>count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。</li>
     *      <li>count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。</li>
     *      <li>count = 0 : 移除表中所有与 value 相等的值。</li>
     *  </ul>
     *
     * @see <a href="http://redisdoc.com/list/lrem.html">List（列表） -> lrem</a>
     * @param key 主键
     * @param count 控制方向
     * @param value 值
     * @return 被移除元素的数量。因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     */
    Long lRem(String key, long count, String value);

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value 。<br/>
     * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/lset.html">List（列表） -> lset</a>
     * @param key 主键
     * @param index 下标
     * @param value 值
     * @return 被移除元素的数量。因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     */
    String lSet(String key, long index, String value);

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。<br/>
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。<br/>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br/>
     * 当 key 不是列表类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/ltrim.html">List（列表） -> ltrim</a>
     * @param key 主键
     * @param start 开始下标
     * @param end 结束下标
     * @return 成功返回true，失败返回false;
     */
    Boolean lTrim(String key, long start, long end);

    /**
     * 移除并返回列表 key 的尾元素。
     *
     * @see <a href="http://redisdoc.com/list/rpop.html">List（列表） -> rpop</a>
     * @param key 主键
     * @return 列表的尾元素。当 key 不存在时，返回 nil 。
     */
    String rPop(String key);

    /**
     * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：
     * <ul>
     *     <li>将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。</li>
     *     <li>将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。</li>
     * </ul>
     * 举个例子，你有两个列表 source 和 destination ， source 列表有元素 a, b, c ， destination 列表有元素 x, y, z ，
     * 执行 RPOPLPUSH source destination 之后， source 列表包含元素 a, b ， destination 列表包含元素 c, x, y, z ，
     * 并且元素 c 会被返回给客户端。<br/>
     * 如果 source 不存在，值 nil 被返回，并且不执行其他动作。<br/>
     * 如果 source 和 destination 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作。
     *
     * @see <a href="http://redisdoc.com/list/rpoplpush.html">List（列表） -> rpoplpush</a>
     * @param srckey 源key
     * @param dstkey 目标key
     * @return 被弹出的元素。
     */
    String rPopLpush(String srckey, String dstkey);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。<br/>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，
     * 得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。<br/>
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。<br/>
     * 当 key 存在但不是列表类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/list/rpush.html">List（列表） -> rpush</a>
     * @param key 主键
     * @param string 一个或多个值
     * @return 列表的长度。
     */
    Long rPush(String key, String... string);

    /**
     * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。<br/>
     * 和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做。
     *
     * @see <a href="http://redisdoc.com/list/rpushx.html">List（列表） -> rpushx</a>
     * @param key 主键
     * @param string 一个或多个值
     * @return 列表的长度。
     */
    Long rPushX(String key, String... string);
}
