package com.heqing.nosql.redis.service;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 哈希表
 * @see <a href="http://redisdoc.com/hash/index.html">Redis命令 -> Hash（哈希表）</a>
 * @author heqing
 * @date 2018/7/19
 */
public interface HashMapRedisService extends KeyRedisService {

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @see <a href="http://redisdoc.com/hash/hdel.html">Hash（哈希表） -> hdel</a>
     * @param key 主键名
     * @param field 多个域名
     * @return 被成功移除的域的数量，不包括被忽略的域。
     */
    Long hDel(String key, String... field);

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     *
     * @see <a href="http://redisdoc.com/hash/hexists.html">Hash（哈希表） -> hexists</a>
     * @param key 主键名
     * @param field 域名
     * @return 如果哈希表含有给定域，返回 true, 如果哈希表不含有给定域，或 key 不存在，返回 false 。
     */
    Boolean hExists(String key, String field);

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @see <a href="http://redisdoc.com/hash/hget.html">Hash（哈希表） -> hget</a>
     * @param key 主键名
     * @param field 域名
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    String hGet(String key, String field);

    /**
     * 返回哈希表 key 中，所有的域和值。<br/>
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     *
     * @see <a href="http://redisdoc.com/hash/hgetall.html">Hash（哈希表） -> hgetall</a>
     * @param key 主键名
     * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
     */
    Map<String, String> hGet(String key);

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。<br/>
     * 增量也可以为负数，相当于对给定域进行减法操作。<br/>
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br/>
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。<br/>
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。<br/>
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     *
     * @see <a href="http://redisdoc.com/hash/hincrby.html">Hash（哈希表） -> hincrby</a>
     * @param key 主键名
     * @param field 域名
     * @param increment 增量
     * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    Long hincrBy(String key, String field, long increment);

    /**
     * 为哈希表 key 中的域 field 加上浮点数增量 increment 。<br/>
     * 如果哈希表中没有域 field ，那么 HINCRBYFLOAT 会先将域 field 的值设为 0 ，然后再执行加法操作。<br/>
     * 如果键 key 不存在，那么 HINCRBYFLOAT 会先创建一个哈希表，再创建域 field ，最后再执行加法操作。<br/>
     * 当以下任意一个条件发生时，返回一个错误：
     *  <ul>
     *      <li>域 field 的值不是字符串类型(因为 redis 中的数字和浮点数都以字符串的形式保存，所以它们都属于字符串类型）</li>
     *      <li>域 field 当前的值或给定的增量 increment 不能解释(parse)为双精度浮点数(double precision floating point number)</li>
     *  </ul>
     *
     * @see <a href="http://redisdoc.com/hash/hincrbyfloat.html">Hash（哈希表） -> hincrbyfloat</a>
     * @param key 主键名
     * @param field 域名
     * @param increment 增量
     * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    Double hincrByFloat(String key, String field, double increment);

    /**
     * 返回哈希表 key 中的所有域。
     *
     * @see <a href="http://redisdoc.com/hash/hkeys.html">Hash（哈希表） -> hkeys</a>
     * @param key 主键名
     * @return 一个包含哈希表中所有域的表。当 key 不存在时，返回一个空表。
     */
    Set<String> hKeys(String key);

    /**
     * 返回哈希表 key 中域的数量。
     *
     * @see <a href="http://redisdoc.com/hash/hlen.html">Hash（哈希表） -> hlen</a>
     * @param key 主键名
     * @return 哈希表中域的数量。当 key 不存在时，返回 0 。
     */
    Long hLen(String key);

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。<br/>
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。<br/>
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     *
     * @see <a href="http://redisdoc.com/hash/hmget.html">Hash（哈希表） -> hmget</a>
     * @param key 主键名
     * @param field 多个域名
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    List<String> hMget(String key, String... field);

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。<br/>
     * 此命令会覆盖哈希表中已存在的域。<br/>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @see <a href="http://redisdoc.com/hash/hmset.html">Hash（哈希表） -> hmset</a>
     * @param key 主键名
     * @param hash 多个域值
     * @return 如果命令执行成功，返回 OK 。当 key 不是哈希表(hash)类型时，返回一个错误。
     */
    String hMset(String key, Map<String, String> hash);

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。<br/>
     * 若域 field 已经存在，该操作无效。<br/>
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     *
     * @see <a href="http://redisdoc.com/hash/hsetnx.html">Hash（哈希表） -> hsetnx</a>
     * @param key 主键名
     * @param field 域名
     * @param value 值
     * @return 设置成功，返回 true 。如果给定域已经存在且没有操作被执行，返回 false 。
     */
    Boolean hSetNx(String key, String field, String value);

    /**
     * 返回哈希表 key 中所有域的值。
     *
     * @see <a href="http://redisdoc.com/hash/hvals.html">Hash（哈希表） -> hvals</a>
     * @param key 主键名
     * @return 一个包含哈希表中所有值的表。当 key 不存在时，返回一个空表。
     */
    List<String> hVals(String key);

    /**
     * 迭代迭代哈希键中的键值对。
     *
     * @see <a href="http://redisdoc.com/hash/hscan.html">Hash（哈希表） -> hScan</a>
     * @param key 主键
     * @param cursor 光标
     * @param params 排序条件，默认可传null
     * @return 返回的每个元素都是一个键值对，一个键值对由一个键和一个值组成。
     */
    ScanResult<Map.Entry<String, String>> hScan(String key, String cursor, ScanParams params);

}
