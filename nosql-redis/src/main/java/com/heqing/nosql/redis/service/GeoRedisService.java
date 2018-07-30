package com.heqing.nosql.redis.service;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * geo（地理信息系统）
 * @see <a href="http://redisdoc.com/geo/index.html">Redis命令 -> geo</a>
 * @author heqing
 * @date 2018/7/30 16:48
 */
public interface GeoRedisService extends KeyRedisService {

    /**
     * 将给定的空间元素（纬度、经度、名字）添加到指定的键里面。 这些数据会以有序集合的形式被储存在键里面，
     * 从而使得像 GEORADIUS 和 GEORADIUSBYMEMBER 这样的命令可以在之后通过位置查询取得这些元素。<br/>
     * GEOADD 命令以标准的 x,y 格式接受参数， 所以用户必须先输入经度， 然后再输入纬度。 GEOADD
     * 能够记录的坐标是有限的： 非常接近两极的区域是无法被索引的。 精确的坐标限制由 EPSG:900913 / EPSG:3785 / OSGEO:41001 等坐标系统定义，
     * 具体如下：<br/>
     * <ul>
     *     <li>有效的经度介于 -180 度至 180 度之间。</li>
     *     <li>有效的纬度介于 -85.05112878 度至 85.05112878 度之间。</li>
     * </ul>
     * 当用户尝试输入一个超出范围的经度或者纬度时， GEOADD 命令将返回一个错误。
     *
     * @see <a href="http://redisdoc.com/geo/geoadd.html">geo -> geoadd</a>
     * @param key 主键名
     * @param longitude 经度
     * @param latitude 维度
     * @param member 元素
     * @return 新添加到键里面的空间元素数量， 不包括那些已经存在但是被更新的元素。
     */
    Long geoAdd(String key, double longitude, double latitude, String member);

    /**
     * 将给定的空间元素（纬度、经度、名字）添加到指定的键里面。 这些数据会以有序集合的形式被储存在键里面，
     * 从而使得像 GEORADIUS 和 GEORADIUSBYMEMBER 这样的命令可以在之后通过位置查询取得这些元素。<br/>
     * GEOADD 命令以标准的 x,y 格式接受参数， 所以用户必须先输入经度， 然后再输入纬度。 GEOADD
     * 能够记录的坐标是有限的： 非常接近两极的区域是无法被索引的。 精确的坐标限制由 EPSG:900913 / EPSG:3785 / OSGEO:41001 等坐标系统定义，
     * 具体如下：<br/>
     * <ul>
     *     <li>有效的经度介于 -180 度至 180 度之间。</li>
     *     <li>有效的纬度介于 -85.05112878 度至 85.05112878 度之间。</li>
     * </ul>
     * 当用户尝试输入一个超出范围的经度或者纬度时， GEOADD 命令将返回一个错误。
     *
     * @see <a href="http://redisdoc.com/geo/geoadd.html">geo -> geoadd</a>
     * @param key 主键名
     * @param memberCoordinateMap 成员和经纬度
     * @return 新添加到键里面的空间元素数量， 不包括那些已经存在但是被更新的元素。
     */
    Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap);

    /**
     * 从键里面返回所有给定位置元素的位置（经度和纬度）。<br/>
     * 因为 GEOPOS 命令接受可变数量的位置元素作为输入， 所以即使用户只给定了一个位置元素， 命令也会返回数组回复。
     *
     * @see <a href="http://redisdoc.com/geo/geopos.html">geo -> geoadd</a>
     * @param key 主键名
     * @param members 成员
     * @return GEOPOS 命令返回一个数组， 数组中的每个项都由两个元素组成： 第一个元素为给定位置元素的经度， 而第二个元素则为给定位置元素的纬度。<br/>
     *         当给定的位置元素不存在时， 对应的数组项为空值。
     */
    List<GeoCoordinate> geoPos(String key, String... members);

    /**
     * 返回两个给定位置之间的距离。默认单位米。<br/>
     * 如果两个位置之间的其中一个不存在， 那么命令返回空值。<br/>
     * GEODIST 命令在计算距离时会假设地球为完美的球形， 在极限情况下， 这一假设最大会造成 0.5% 的误差。
     *
     * @see <a href="http://redisdoc.com/geo/geodist.html">geo -> geodist</a>
     * @param key 主键名
     * @param member1 成员1
     * @param member2 成员2
     * @return 计算出的距离会以双精度浮点数的形式被返回。 如果给定的位置元素不存在， 那么命令返回空值。
     */
    Double geoDist(String key, String member1, String member2);

    /**
     * 返回两个给定位置之间的距离。默认单位米。<br/>
     * 如果两个位置之间的其中一个不存在， 那么命令返回空值。<br/>
     * 指定单位的参数 unit 必须是以下单位的其中一个：
     * <ul>
     *     <li>m 表示单位为米。</li>
     *     <li>km 表示单位为千米。</li>
     *     <li>mi 表示单位为英里。</li>
     *     <li>ft 表示单位为英尺。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/geo/geodist.html">geo -> geodist</a>
     * @param key 主键名
     * @param member1 成员1
     * @param member2 成员2
     * @param unit 距离单位
     * @return 计算出的距离会以双精度浮点数的形式被返回。 如果给定的位置元素不存在， 那么命令返回空值。
     */
    Double geoDist(String key, String member1, String member2, GeoUnit unit);

    /**
     * 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。<br/>
     * 指定单位的参数 unit 必须是以下单位的其中一个：
     * <ul>
     *     <li>m 表示单位为米。</li>
     *     <li>km 表示单位为千米。</li>
     *     <li>mi 表示单位为英里。</li>
     *     <li>ft 表示单位为英尺。</li>
     * </ul>
     *
     *
     * @see <a href="http://redisdoc.com/geo/georadius.html">geo -> georadius</a>
     * @param key 主键名
     * @param longitude 精度
     * @param latitude 维度
     * @param radius 距离
     * @param unit 距离单位
     * @return 该段距离内的数据
     */
    List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius,
                                      GeoUnit unit);

    /**
     * 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。<br/>
     * 指定单位的参数 unit 必须是以下单位的其中一个：
     * <ul>
     *     <li>m 表示单位为米。</li>
     *     <li>km 表示单位为千米。</li>
     *     <li>mi 表示单位为英里。</li>
     *     <li>ft 表示单位为英尺。</li>
     * </ul>
     * 在给定以下可选项时， 命令会返回额外的信息：
     * <ul>
     *     <li>WITHDIST ： 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。 距离的单位和用户给定的范围单位保持一致。</li>
     *     <li>WITHCOORD ： 将位置元素的经度和维度也一并返回。</li>
     *     <li>WITHHASH ： 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值。 这个选项主要用于底层应用或者调试， 实际中的作用并不大。</li>
     * </ul>
     * 命令默认返回未排序的位置元素。 通过以下两个参数， 用户可以指定被返回位置元素的排序方式：
     * <ul>
     *     <li>ASC ： 根据中心的位置， 按照从近到远的方式返回位置元素。</li>
     *     <li>DESC ： 根据中心的位置， 按照从远到近的方式返回位置元素。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/geo/georadius.html">geo -> georadius</a>
     * @param key 主键名
     * @param longitude 精度
     * @param latitude 维度
     * @param radius 距离
     * @param unit 距离单位
     * @param param 查询条件
     * @return 该段距离内的数据
     */
    List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
                                      GeoUnit unit, GeoRadiusParam param);

    /**
     * 这个命令和 GEORADIUS 命令一样， 都可以找出位于指定范围内的元素， 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，
     * 而不是像 GEORADIUS 那样， 使用输入的经度和纬度来决定中心点。
     * 指定单位的参数 unit 必须是以下单位的其中一个：
     * <ul>
     *     <li>m 表示单位为米。</li>
     *     <li>km 表示单位为千米。</li>
     *     <li>mi 表示单位为英里。</li>
     *     <li>ft 表示单位为英尺。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/geo/georadiusbymember.html">geo -> georadiusbymember</a>
     * @param key 主键名
     * @param member 成员
     * @param radius 距离
     * @param unit 距离单位
     * @return 一个数组， 数组中的每个项表示一个范围之内的位置元素。
     */
    List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit);

    /**
     * 这个命令和 GEORADIUS 命令一样， 都可以找出位于指定范围内的元素， 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，
     * 而不是像 GEORADIUS 那样， 使用输入的经度和纬度来决定中心点。
     * 指定单位的参数 unit 必须是以下单位的其中一个：
     * <ul>
     *     <li>m 表示单位为米。</li>
     *     <li>km 表示单位为千米。</li>
     *     <li>mi 表示单位为英里。</li>
     *     <li>ft 表示单位为英尺。</li>
     * </ul>
     * 在给定以下可选项时， 命令会返回额外的信息：
     * <ul>
     *     <li>WITHDIST ： 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。 距离的单位和用户给定的范围单位保持一致。</li>
     *     <li>WITHCOORD ： 将位置元素的经度和维度也一并返回。</li>
     *     <li>WITHHASH ： 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值。 这个选项主要用于底层应用或者调试， 实际中的作用并不大。</li>
     * </ul>
     * 命令默认返回未排序的位置元素。 通过以下两个参数， 用户可以指定被返回位置元素的排序方式：
     * <ul>
     *     <li>ASC ： 根据中心的位置， 按照从近到远的方式返回位置元素。</li>
     *     <li>DESC ： 根据中心的位置， 按照从远到近的方式返回位置元素。</li>
     * </ul>
     *
     * @see <a href="http://redisdoc.com/geo/georadiusbymember.html">geo -> georadiusbymember</a>
     * @param key 主键名
     * @param member 成员
     * @param radius 距离
     * @param unit 距离单位
     * @param param 查询条件
     * @return 一个数组， 数组中的每个项表示一个范围之内的位置元素。
     */
    List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param);

    /**
     * 返回一个或多个位置元素的 Geohash 表示
     *
     * @see <a href="http://redisdoc.com/geo/geohash.html">geo -> geohash</a>
     * @param key 主键名
     * @param members 成员
     * @return 一个数组， 数组的每个项都是一个 geohash 。 命令返回的 geohash 的位置与用户给定的位置元素的位置一一对应。
     */
    List<String> geohash(String key, String... members);
}
