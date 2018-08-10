package com.heqing.nosql.mongodb.constant;

/**
 * 对字符串的进行比较的条件
 * @author heqing
 * @date 2018/8/10 14:53
 */
public enum Comparison {

    /**
     * <ul>
     *     <li>eq : 等于</li>
     *     <li>ne : 不等于</li>
     *     <li>start : 以此字符开头</li>
     *     <li>like : 存在此字符</li>
     *     <li>end : 以此字符结尾</li>
     * </ul>
     */
    eq, ne, start, like, end
}
