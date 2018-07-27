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
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。<br/>
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     *
     * @see <a href="http://redisdoc.com/string/append.html">String(字符串) -> append</a>
     * @param key 主键名
     * @param value 值
     * @return 追加 value 之后， key 中字符串的长度。
     */
    Long append(String key, String value);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。<br/>
     * 不存在的 key 被当成是空字符串来处理，因此对一个不存在的 key 进行 BITCOUNT 操作，结果为 0 。
     *
     * @see <a href="http://redisdoc.com/string/bitcount.html">String(字符串) -> bitcount</a>
     * @param key 主键名
     * @return 被设置为 1 的位的数量。
     */
    Long bitCount(String key);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。<br/>
     * 一般情况下，给定的整个字符串都会被进行计数，通过指定额外的 start 或 end 参数，可以让计数只在特定的位上进行。<br/>
     * start 和 end 参数的设置和 GETRANGE 命令类似，都可以使用负数值： 比如 -1 表示最后一个字节， -2 表示倒数第二个字节，以此类推。<br/>
     * 不存在的 key 被当成是空字符串来处理，因此对一个不存在的 key 进行 BITCOUNT 操作，结果为 0 。
     *
     * @see <a href="http://redisdoc.com/string/bitcount.html">String(字符串) -> bitcount</a>
     * @param key 主键名
     * @param start 开始值
     * @param end 结束值
     * @return 被设置为 1 的位的数量。
     */
    Long bitCount(String key, long start, long end);

    /**
     * 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destkey 上。<br/>
     * 除了 NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入。
     *
     * @see <a href="http://redisdoc.com/string/bitop.html">String（字符串） -> bitop</a>
     * @param op AND:逻辑并, OR:逻辑或, XOR:逻辑异或, NOT :逻辑非
     * @param destKey 保存key
     * @param srcKeys 操作的key
     * @return 保存到 destkey 的字符串的长度，和输入 key 中最长的字符串长度相等。
     */
    Long bitOp(BitOP op, String destKey, String... srcKeys);

    /**
     * 可以将一个 Redis 字符串看作是一个由二进制位组成的数组， 并对这个数组中储存的长度不同的整数进行访问
     * （被储存的整数无需进行对齐）。 换句话说， 通过这个命令， 用户可以执行诸如 “对偏移量 1234 上的 5 位
     * 长有符号整数进行设置”、 “获取偏移量 4567 上的 31 位长无符号整数”等操作。 此外， BITFIELD 命令还可以
     * 对指定的整数执行加法操作和减法操作， 并且这些操作可以通过设置妥善地处理计算时出现的溢出情况。<br/>
     * BITFIELD 命令可以在一次调用中同时对多个位范围进行操作： 它接受一系列待执行的操作作为参数，
     * 并返回一个数组作为回复， 数组中的每个元素就是对应操作的执行结果。
     *
     * @see <a href="http://redisdoc.com/string/bitfield.html">String（字符串） -> bitfield</a>
     * @param key 主键
     * @param arguments 子命令
     * @return 一个数组， 数组中的每个元素对应一个被执行的子命令。 需要注意的是， OVERFLOW 子命令本身并不产生任何回复。
     */
    List<Long> bitField(String key, String... arguments);

    /**
     * 将 key 中储存的数字值减一。<br/>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。<br/>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
     * 关于递增(increment) / 递减(decrement)操作的更多信息，请参见 INCR 命令。
     *
     * @see <a href="http://redisdoc.com/string/decr.html">String（字符串） -> decr</a>
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
     * @see <a href="http://redisdoc.com/string/decrby.html">String（字符串） -> decrby</a>
     * @param key 主键
     * @param decrement 减量
     * @return 减去 decrement 之后， key 的值。
     */
    Long decrBy(String key, long decrement);

    /**
     * 返回 key 所关联的字符串值。<br/>
     * 如果 key 不存在那么返回特殊值 nil 。<br/>
     * 假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     *
     * @see <a href="http://redisdoc.com/string/get.html">String（字符串） -> get</a>
     * @param key 主键
     * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。
     */
    String get(String key);

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。<br/>
     * 当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。
     *
     * @see <a href="http://redisdoc.com/string/getbit.html">String（字符串） -> getbit</a>
     * @param key 主键
     * @param offset 偏移量
     * @return 字符串值指定偏移量上的位(bit)。
     */
    Boolean getBit(String key, long offset);

    /**
     * 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。<br/>
     * 负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。<br/>
     * GETRANGE 通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求。
     *
     * @see <a href="http://redisdoc.com/string/getrange.html">String（字符串） -> getrange</a>
     * @param key 主键
     * @param startOffset 开始坐标
     * @param endOffset 结束坐标
     * @return 截取得出的子字符串。
     */
    String getRange(String key, long startOffset, long endOffset);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。<br/>
     * 当 key 存在但不是字符串类型时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/string/getset.html">String（字符串） -> getset</a>
     * @param key 主键
     * @param value 新值
     * @return 返回给定 key 的旧值。当 key 没有旧值时，也即是， key 不存在时，返回 nil 。
     */
    String getSet(String key, String value);

    /**
     * 将 key 中储存的数字值增一。<br/>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。<br/>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br/>
     * 这是一个针对字符串的操作，因为 Redis 没有专用的整数类型，所以 key 内储存的字符串被解释为十进制 64 位有符号整数来执行 INCR 操作。
     *
     * @see <a href="http://redisdoc.com/string/incr.html">String（字符串） -> incr</a>
     * @param key 主键
     * @return 执行 INCR 命令之后 key 的值。
     */
    Long incr(String key);

    /**
     * 将 key 所储存的值加上增量 increment 。<br/>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。<br/>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。<br/>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     *
     * @see <a href="http://redisdoc.com/string/incrby.html">String（字符串） -> incrby</a>
     * @param key 主键
     * @param increment 增量
     * @return 加上 increment 之后， key 的值。
     */
    Long incrBy(String key, long increment);

    /**
     * 为 key 中所储存的值加上浮点数增量 increment <br/>
     * 如果 key 不存在，那么 INCRBYFLOAT 会先将 key 的值设为 0 ，再执行加法操作。<br/>
     * 如果命令执行成功，那么 key 的值会被更新为（执行加法之后的）新值，并且新值会以字符串的形式返回给调用者。<br/>
     * 无论是 key 的值，还是增量 increment ，都可以使用像 2.0e7 、 3e5 、 90e-2 那样的指数符号(exponential notation)来表示，
     * 但是，执行 INCRBYFLOAT 命令之后的值总是以同样的形式储存，也即是，它们总是由一个数字，一个（可选的）小数点和一个任意位的小数部分组成
     * （比如 3.14 、 69.768 ，诸如此类)，小数部分尾随的 0 会被移除，如果有需要的话，还会将浮点数改为整数（比如 3.0 会被保存成 3 ）。<br/>
     * 除此之外，无论加法计算所得的浮点数的实际精度有多长， INCRBYFLOAT 的计算结果也最多只能表示小数点的后十七位。
     * 当以下任意一个条件发生时，返回一个错误：
     *  <ul>
     *      <li>key 的值不是字符串类型(因为 Redis 中的数字和浮点数都以字符串的形式保存，所以它们都属于字符串类型）</li>
     *      <li>key 当前的值或者给定的增量 increment 不能解释(parse)为双精度浮点数(double precision floating point number）</li>
     *  </ul>
     *
     * @see <a href="http://redisdoc.com/string/incrbyfloat.html">String（字符串） -> incrbyfloat</a>
     * @param key 主键
     * @param increment 增量
     * @return 执行命令之后 key 的值。
     */
    Double incrByFloat(String key, double increment);

    /**
     * 返回所有(一个或多个)给定 key 的值。<br/>
     * 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。因此，该命令永不失败。
     *
     * @see <a href="http://redisdoc.com/string/mget.html">String（字符串） -> mget</a>
     * @param keys 主键
     * @return 一个包含所有给定 key 的值的列表。
     */
    List<String> mGet(String... keys);

    /**
     * 同时设置一个或多个 key-value 对。<br/>
     * 如果某个给定 key 已经存在，那么 MSET 会用新值覆盖原来的旧值，如果这不是你所希望的效果，请考虑使用 MSETNX 命令：
     * 它只会在所有给定 key 都不存在的情况下进行设置操作。<br/>
     * MSET 是一个原子性(atomic)操作，所有给定 key 都会在同一时间内被设置，某些给定 key 被更新而另一些给定 key 没有改变的情况，不可能发生。
     *
     * @see <a href="http://redisdoc.com/string/mset.html">String（字符串） -> mset</a>
     * @param keyValues 多个key-value 对。应为双数
     * @return 总是返回 true (因为 MSET 不可能失败)
     */
    Boolean mSet(String... keyValues);

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。<br/>
     * 即使只有一个给定 key 已存在， MSETNX 也会拒绝执行所有给定 key 的设置操作。<br/>
     * MSETNX 是原子性的，因此它可以用作设置多个不同 key 表示不同字段(field)的唯一性逻辑对象(unique logic object)，所有字段要么全被设置，要么全不被设置。
     *
     * @see <a href="http://redisdoc.com/string/msetnx.html">String（字符串） -> msetnx</a>
     * @param keyValues 多个key-value 对。应为双数
     * @return 当所有 key 都成功设置，返回 1 。如果所有给定 key 都设置失败(至少有一个 key 已经存在)，那么返回 0 。
     */
    Boolean mSetNx(String... keyValues);

    /**
     * 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。
     *
     * @see <a href="http://redisdoc.com/string/psetex.html">String（字符串） -> psetex</a>
     * @param key 主键
     * @param milliseconds 生存时间
     * @param value 值
     * @return 设置成功时返回 true, 失败返回false 。
     */
    Boolean pSetEx(String key, long milliseconds, String value);

    /**
     * 将字符串值 value 关联到 key 。<br/>
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
     *
     * @see <a href="http://redisdoc.com/string/set.html">String（字符串） -> set</a>
     * @param key 主键
     * @param value 值
     * @return 设置成功时返回 true, 失败返回false 。
     */
    Boolean set(String key, String value);

    /**
     * 将字符串值 value 关联到 key 。<br/>
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
     *
     * @see <a href="http://redisdoc.com/string/set.html">String（字符串） -> set</a>
     * @param key 主键
     * @param value 值
     * @param nxxx NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
     *             XX ：只在键已经存在时，才对键进行设置操作。
     * @return 设置成功时返回 true, 失败返回false 。
     */
    Boolean set(String key, String value, String nxxx);

    /**
     * 将字符串值 value 关联到 key 。<br/>
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
     *
     * @see <a href="http://redisdoc.com/string/set.html">String（字符串） -> set</a>
     * @param key 主键
     * @param value 值
     * @param nxxx NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
     *             XX ：只在键已经存在时，才对键进行设置操作。
     * @param expx EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
     *             PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
     * @param time 时间
     * @return 设置成功时返回 true, 失败返回false 。
     */
    Boolean set(String key, String value, String nxxx, String expx, long time);

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。位的设置或清除取决于 value 参数，可以是 0 也可以是 1 。<br/>
     * 当 key 不存在时，自动生成一个新的字符串值。<br/>
     * 字符串会进行伸展(grown)以确保它可以将 value 保存在指定的偏移量上。当字符串值进行伸展时，空白位置以 0 填充。<br/>
     * offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
     *
     * @see <a href="http://redisdoc.com/string/setbit.html">String（字符串） -> setbit</a>
     * @param key 主键
     * @param offset 偏移量
     * @param value 值
     * @return 指定偏移量原来储存的位。
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。位的设置或清除取决于 value 参数，可以是 0 也可以是 1 。<br/>
     * 当 key 不存在时，自动生成一个新的字符串值。<br/>
     * 字符串会进行伸展(grown)以确保它可以将 value 保存在指定的偏移量上。当字符串值进行伸展时，空白位置以 0 填充。<br/>
     * offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
     *
     * @see <a href="http://redisdoc.com/string/setbit.html">String（字符串） -> setbit</a>
     * @param key 主键
     * @param offset 偏移量
     * @param value 值
     * @return 指定偏移量原来储存的位。
     */
    Boolean setBit(String key, long offset, String value);

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。如果 key 已经存在， SETEX 命令将覆写旧值。 <br/>
     * SETEX 是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
     *
     * @see <a href="http://redisdoc.com/string/setex.html">String（字符串） -> setex</a>
     * @param key 主键
     * @param seconds 时间
     * @param value 值
     * @return 指定偏移量原来储存的位。
     */
    String setEx(String key, int seconds, String value);

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。 <br/>
     * 若给定的 key 已经存在，则 SETNX 不做任何动作。
     *
     * @see <a href="http://redisdoc.com/string/setnx.html">String（字符串） -> setnx</a>
     * @param key 主键
     * @param value 值
     * @return 设置成功，返回 true 。 设置失败，返回 false 。
     */
    Boolean setNx(String key, String value);

    /**
     * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。不存在的 key 当作空白字符串处理。 <br/>
     * SETRANGE 命令会确保字符串足够长以便将 value 设置在指定的偏移量上，如果给定 key 原来储存的字符串长度比偏移量小
     * (比如字符串只有 5 个字符长，但你设置的 offset 是 10 )，那么原字符和偏移量之间的空白将用零字节(zerobytes, "\x00" )来填充。
     *
     * @see <a href="http://redisdoc.com/string/setrange.html">String（字符串） -> setrange</a>
     * @param key 主键
     * @param offset 偏移量
     * @param value 值
     * @return 被 SETRANGE 修改之后，字符串的长度。
     */
    Long setRange(String key, long offset, String value);

    /**
     * 返回 key 所储存的字符串值的长度。当 key 储存的不是字符串值时，返回一个错误。
     *
     * @see <a href="http://redisdoc.com/string/setrange.html">String（字符串） -> setrange</a>
     * @param key 主键
     * @return 字符串值的长度。当 key 不存在时，返回 0 。
     */
    Long setRange(String key);
}
