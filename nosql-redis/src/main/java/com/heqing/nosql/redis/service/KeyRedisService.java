package com.heqing.nosql.redis.service;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 键
 * @see <a href="http://redisdoc.com/key/index.html">Redis命令 -> Key（键）</a>
 * @author heqing
 * @date 2018/7/18
 */
public interface KeyRedisService {

    /**
     * 删除给定的一个或多个 key 。不存在的 key 会被忽略。
     *
     * @see <a href="http://redisdoc.com/key/del.html">Key（键） -> del</a>
     * @param keys 需要删除的主键名
     * @return 被删除 key 的数量
     */
    Long del(String... keys);

    /**
     * 序列化给定 key ，并返回被序列化的值，使用 RESTORE 命令可以将这个值反序列化为 Redis 键。<br/>
     * 序列化的值不包括任何生存时间信息。<br/>
     * 序列化生成的值有以下几个特点：
     * <ul>
     *     <li>它带有 64 位的校验和，用于检测错误， RESTORE 在进行反序列化之前会先检查校验和。</li>
     *     <li>值的编码格式和 RDB 文件保持一致。</li>
     *     <li>RDB 版本会被编码在序列化值当中，如果因为 Redis 的版本不同造成 RDB 格式不兼容，那么 Redis 会拒绝对这个值进行反序列化操作。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/key/dump.html">Key（键） -> dump</a>
     * @param key 主键名
     * @return 如果 key 不存在，那么返回 null 。否则，返回序列化之后的值。
     */
    String dump(String key);

    /**
     * 用于检查给定 key 是否存在。
     *
     * @see <a href="http://redisdoc.com/key/exists.html">Key（键） -> exists</a>
     * @param key 主键名
     * @return 若 key 存在返回 true ，否则返回 false 。
     */
    Boolean exists(String key);

    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。<br/>
     * 生存时间可以通过使用 DEL 命令来删除整个 key 来移除，或者被 SET 和 GETSET 命令覆写(overwrite)，
     * 如果一个命令只是修改带生存时间的 key 的值，而不是用一个新的 key 值来代替它的话，那么生存时间不会被改变。
     * 比如说，对一个 key 执行 INCR 命令，对一个列表进行 LPUSH 命令，或者对一个哈希表执行 HSET 命令，这类操作都不会修改 key 本身的生存时间。<br/>
     * 另一方面，如果使用 RENAME 对一个 key 进行改名，那么改名后的 key 的生存时间和改名前一样。
     * RENAME 命令的另一种可能是，尝试将一个带生存时间的 key 改名成另一个带生存时间的 another_key ，
     * 这时旧的 another_key (以及它的生存时间)会被删除，然后旧的 key 会改名为 another_key ，因此，新的 another_key 的生存时间也和原本的 key 一样。<br/>
     * 使用 PERSIST 命令可以在不删除 key 的情况下，移除 key 的生存时间，让 key 重新成为一个『持久的』(persistent) key 。
     *
     * @see <a href="http://redisdoc.com/key/expire.html">Key（键） -> expire</a>
     * @param key 主键名
     * @param seconds 过期时间（以秒为单位）
     * @return 若 key 存在返回 true ，否则返回 false 。
     */
    Boolean expire(String key, int seconds);

    /**
     * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。<br/>
     * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
     *
     * @see <a href="http://redisdoc.com/key/expireat.html">Key（键） -> expireat</a>
     * @param key 主键名
     * @param unixTime UNIX 时间戳(unix timestamp)
     * @return 设置成功返回 true 。当 key 不存在或者不能为 key 设置过期时间时返回 false 。
     */
    Boolean expireAt(String key, long unixTime);

    /**
     * 查找所有符合给定模式 pattern 的 key 。<br/>
     * KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key ，你最好还是用 Redis 的集合结构(set)来代替。
     * <ul>
     *     <li>KEYS * 匹配数据库中所有 key </li>
     *     <li>KEYS h?llo 匹配 hello ， hallo 和 hxllo 等</li>
     *     <li>KEYS h*llo 匹配 hllo 和 heeeeello 等</li>
     *     <li>KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo </li>
     * </ul>
     * @see <a href="http://redisdoc.com/key/keys.html">Key（键） -> keys</a>
     * @param pattern 条件
     * @return 设符合给定模式的 key 列表 (Array)。
     */
    Set<String> keys(String pattern);

    /**
     * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，一旦传送成功， key 保证会出现在目标实例上，而当前实例上的 key 会被删除。<br/>
     * 这个命令是一个原子操作，它在执行的时候会阻塞进行迁移的两个实例，直到以下任意结果发生：迁移成功，迁移失败，等到超时。<br/>
     * 命令的内部实现是这样的：它在当前实例对给定 key 执行 DUMP 命令 ，将它序列化，然后传送到目标实例，目标实例再使用 RESTORE 对数据进行反序列化，
     * 并将反序列化所得的数据添加到数据库中；当前实例就像目标实例的客户端那样，只要看到 RESTORE 命令返回 OK ，它就会调用 DEL 删除自己数据库上的 key 。<br/>
     * timeout 参数以毫秒为格式，指定当前实例和目标实例进行沟通的最大间隔时间。
     * 这说明操作并不一定要在 timeout 毫秒内完成，只是说数据传送的时间不能超过这个 timeout 数。<br/>
     * MIGRATE 命令需要在给定的时间规定内完成 IO 操作。如果在传送数据时发生 IO 错误，或者达到了超时时间，那么命令会停止执行，并返回一个特殊的错误： IOERR 。<br/>
     * 当 IOERR 出现时，有以下两种可能：
     * <ul>
     *     <li>key 可能存在于两个实例</li>
     *     <li>key 可能只存在于当前实例</li>
     * </ul>
     * 唯一不可能发生的情况就是丢失 key ，因此，如果一个客户端执行 MIGRATE 命令，并且不幸遇上 IOERR 错误，那么这个客户端唯一要做的就是检查自己数据库上的 key 是否已经被正确地删除。<br/>
     * 如果有其他错误发生，那么 MIGRATE 保证 key 只会出现在当前实例中。（当然，目标实例的给定数据库上可能有和 key 同名的键，不过这和 MIGRATE 命令没有关系）。<br/>
     * <ul>
     *     <li>COPY ：不移除源实例上的 key 。</li>
     *     <li>REPLACE ：替换目标实例上已存在的 key 。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/key/migrate.html">Key（键） -> migrate</a>
     * @param host 地址
     * @param port 端口号
     * @param key 主键名
     * @param destinationDb 数据库下标
     * @param timeout 超时时间
     * @return 移动成功返回 true ，失败则返回 false 。
     */
    Boolean migrate(String host, int port, String key, int destinationDb, int timeout);

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中。<br/>
     * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，或者 key 不存在于当前数据库，那么 MOVE 没有任何效果。<br/>
     * 因此，也可以利用这一特性，将 MOVE 当作锁(locking)原语(primitive)。
     *
     * @see <a href="http://redisdoc.com/key/move.html">Key（键） -> move</a>
     * @param key 主键名
     * @param dbIndex 数据库下标
     * @return 当过期时间移除成功时，返回 true 。如果 key 不存在或 key 没有设置过期时间，返回 false 。
     */
    Boolean move(String key, int dbIndex);

    /**
     * 返回给定 key 锁储存的值所使用的内部表示(representation)<br/>
     * 对象可以以多种方式编码：
     * <ul>
     *     <li>字符串可以被编码为 raw (一般字符串)或 int (用字符串表示64位数字是为了节约空间)。</li>
     *     <li>列表可以被编码为 ziplist 或 linkedlist 。 ziplist 是为节约大小较小的列表空间而作的特殊表示。</li>
     *     <li>集合可以被编码为 intset 或者 hashtable 。 intset 是只储存数字的小集合的特殊表示。</li>
     *     <li>哈希表可以编码为 zipmap 或者 hashtable 。 zipmap 是小哈希表的特殊表示。</li>
     *     <li>有序集合可以被编码为 ziplist 或者 skiplist 格式。 ziplist 用于表示小的有序集合，而 skiplist 则用于表示任何大小的有序集合。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/key/object.html">Key（键） -> objectEncoding</a>
     * @param key 主键名
     * @return 相应的编码类型。
     */
    String objectEncoding(String key);

    /**
     * 返回给定 key 自储存以来的空转时间(idle， 没有被读取也没有被写入)，以秒为单位。
     *
     * @see <a href="http://redisdoc.com/key/object.html">Key（键） -> objectIdletime</a>
     * @param key 主键名
     * @return 没有被读取也没有被写入的秒数。
     */
    Long objectIdletime(String key);

    /**
     * 返回给定 key 引用所储存的值的次数。此命令主要用于除错<br/>
     *
     * @see <a href="http://redisdoc.com/key/object.html">Key（键） -> objectRefcount</a>
     * @param key 主键名
     * @return 储存的值的次数。
     */
    Long objectRefcount(String key);

    /**
     * 用于移除给定 key 的过期时间，使得 key 永不过期
     *
     * @see <a href="http://redisdoc.com/key/persist.html">Key（键） -> persist</a>
     * @param key 主键名
     * @return 当过期时间移除成功时，返回 true 。如果 key 不存在或 key 没有设置过期时间，返回 false 。
     */
    Boolean persist(String key);

    /**
     * 这个命令和 EXPIRE 命令的作用类似，但是它以毫秒为单位设置 key 的生存时间，而不像 EXPIRE 命令那样，以秒为单位。
     *
     * @see <a href="http://redisdoc.com/key/pexpire.html">Key（键） -> pexpire</a>
     * @param key 主键名
     * @param millisecondsTimestamp 毫秒级时间戳
     * @return 当过期时间设置成功时，返回 true 。如果 key 不存在或 key 没办法设置过期时间，返回 false 。
     */
    Boolean pExpire(String key, long millisecondsTimestamp);

    /**
     * 这个命令和 EXPIREAT 命令类似，但它以毫秒为单位设置 key 的过期 unix 时间戳，而不是像 EXPIREAT 那样，以秒为单位。
     *
     * @see <a href="http://redisdoc.com/key/pexpireat.html">Key（键） -> pexpireat</a>
     * @param key 主键名
     * @param millisecondsTimestamp 毫秒级时间戳
     * @return 当过期时间设置成功时，返回 true 。如果 key 不存在或 key 没办法设置过期时间，返回 false 。
     */
    Boolean pExpireAt(String key, long millisecondsTimestamp);

    /**
     * 这个命令类似于 TTL 命令，但它以毫秒为单位返回 key 的剩余生存时间，而不是像 TTL 命令那样，以秒为单位。
     *
     * @see <a href="http://redisdoc.com/key/pttl.html">Key（键） -> pttl</a>
     * @param key 主键名
     * @return 当 key不存在时，返回 -2。当key存在但没有设置剩余生存时间时，返回 -1。否则，以秒为单位返回 key 的剩余生存时间。
     *         注意：在Redis2.8以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
     */
    Long pttl(String key);

    /**
     * 从当前数据库中随机返回(不删除)一个 key 。
     *
     * @see <a href="http://redisdoc.com/key/randomkey.html">Key（键） -> randomkey</a>
     * @return 当数据库不为空时，返回一个 key 。 当数据库为空时，返回 nil 。
     */
    String randomKey();

    /**
     * 将 key 改名为 newkey 。
     * 当 key 和 newkey 相同，或者 key 不存在时，返回一个错误。
     * 当 newkey 已经存在时， RENAME 命令将覆盖旧值。
     *
     * @see <a href="http://redisdoc.com/key/rename.html">Key（键） -> rename</a>
     * @param oldkey 旧主键名
     * @param newkey 新主键名
     * @return 改名成功时返回true，失败时候返回false。
     */
    Boolean rename(String oldkey, String newkey);

    /**
     * 当且仅当 newkey 不存在时，将 key 改名为 newkey 。
     * 当 key 不存在时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/key/renamenx.html">Key（键） -> renamenx</a>
     * @param oldkey 旧主键名
     * @param newkey 新主键名
     * @return 改名成功时返回true，失败时候返回false。
     */
    Boolean renameNx(String oldkey, String newkey);

    /**
     * 反序列化给定的序列化值，并将它和给定的 key 关联。<br/>
     * 参数 ttl 以毫秒为单位为 key 设置生存时间；如果 ttl 为 0 ，那么不设置生存时间。<br/>
     * RESTORE 在执行反序列化之前会先对序列化值的 RDB 版本和数据校验和进行检查，如果 RDB 版本不相同或者数据不完整的话，那么 RESTORE 会拒绝进行反序列化，并返回一个错误。<br/>
     * 如果键 key 已经存在， 并且给定了 REPLACE 选项， 那么使用反序列化得出的值来代替键 key 原有的值； 相反地， 如果键 key 已经存在， 但是没有给定 REPLACE 选项， 那么命令返回一个错误。
     *
     * @see <a href="http://redisdoc.com/key/restore.html">Key（键） -> restore</a>
     * @param key 主键名
     * @param ttl 生存时间，以毫秒为单位
     * @param value 序列化值
     * @return 如果反序列化成功时返回true，失败时候返回false。
     */
    Boolean restore(String key, int ttl, String value);

    /**
     * 对、数据结构为 列表、集合、有序集合 的key进行排序。排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
     *
     * @see <a href="http://redisdoc.com/key/sort.html">Key（键） -> sort</a>
     * @param key 主键名
     * @param sortingParams 排序条件，默认可传null
     * @return 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
     */
    List<String> sort(String key, SortingParams sortingParams);

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间。
     *
     * @see <a href="http://redisdoc.com/key/ttl.html">Key（键） -> ttl</a>
     * @param key 旧主键名
     * @return 当 key 不存在时，返回 -2 。
     *         当 key 存在但没有设置剩余生存时间时，返回 -1 。
     *         否则，以秒为单位，返回 key 的剩余生存时间。
     */
    Long ttl(String key);

    /**
     * 返回 key 所储存的值的类型
     *
     * @see <a href="http://redisdoc.com/key/type.html">Key（键） -> type</a>
     * @param key 旧主键名
     * @return <ul>
     *           <li>none (key不存在)</li>
     *           <li>string (字符串)</li>
     *           <li>list (列表)</li>
     *           <li>set (集合)</li>
     *           <li>none (key不存在)</li>
     *           <li>hash (哈希表)</li>
     *         </ul>
     */
    String type(String key);

    /**
     * 迭代当前数据库中的数据库键。
     *
     * @see <a href="http://redisdoc.com/key/scan.html">Key（键） -> scan</a>
     * @param cursor 光标
     * @param params 排序条件，默认可传null
     * @return 返回的每个元素都是一个数据库键。
     */
    ScanResult<String> scan(String cursor, ScanParams params);

    /**
     * 迭代集合键中的元素。
     *
     * @see <a href="http://redisdoc.com/key/scan.html">Key（键） -> sScan</a>
     * @param key 主键
     * @param cursor 光标
     * @param params 排序条件，默认可传null
     * @return 返回的每个元素都是一个集合成员。
     */
    ScanResult<String> sScan(String key, String cursor, ScanParams params);

    /**
     * 迭代有序集合中的元素（包括元素成员和元素分值）。
     *
     * @see <a href="http://redisdoc.com/key/scan.html">Key（键） -> zScan</a>
     * @param key 主键
     * @param cursor 光标
     * @param params 排序条件，默认可传null
     * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
     */
    ScanResult<Tuple> zScan(String key, String cursor, ScanParams params);
}
