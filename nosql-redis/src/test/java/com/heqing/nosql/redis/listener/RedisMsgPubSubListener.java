package com.heqing.nosql.redis.listener;

import redis.clients.jedis.JedisPubSub;

/**
 * @see <a href="http://redisdoc.com/pub_sub/index.html">Pub/Sub（发布/订阅）</a>
 * @author heqing
 * @date 2018/8/5 20:04
 */
public class RedisMsgPubSubListener extends JedisPubSub {

    /**
     * 初始化订阅时候的处理
     * @see <a href="http://redisdoc.com/pub_sub/subscribe.html">Pub/Sub -> subscribe</a>
     * @param channel 频道
     * @param subscribedChannels 订阅
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("开始订阅 ---> 频道:" + channel + ", 订阅频道:" + subscribedChannels);
    }

    /**
     * 初始化按表达式的方式订阅时候的处理
     * @see <a href="http://redisdoc.com/pub_sub/psubscribe.html">Pub/Sub -> psubscribe</a>
     * @param pattern 表达式匹配符。每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
     *                news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
     * @param subscribedChannels 订阅
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("开始订阅 ---> 表达式:" + pattern + ", 订阅频道:" + subscribedChannels);
    }

    /**
     * 收到订阅消息后的处理
     * @param channel 频道
     * @param message 消息内容
     */
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("订阅消息 ---> 频道:" + channel + ", 接收到的消息:" + message);
        if("end".equals(message)) {
            unsubscribe(channel);
        }
    }

    /**
     * 收到按表达式的方式订阅消息后的处理
     * @param pattern 表达式匹配符。每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
     *                news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
     * @param channel 频道
     * @param message 消息内容
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("按表达式的方式订阅消息 ---> 表达式:" + pattern + ", 频道:" + channel+", 接收到的消息:"+message);
    }

    /**
     * 取消订阅时候的处理
     * @see <a href="http://redisdoc.com/pub_sub/unsubscribe.html">Pub/Sub -> unsubscribe</a>
     * @param channel 频道
     * @param subscribedChannels 订阅
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("取消订阅 ---> 频道:" + channel + ", 订阅频道:" + subscribedChannels);
    }

    /**
     * 取消按表达式的方式订阅时候的处理
     * @see <a href="http://redisdoc.com/pub_sub/punsubscribe.html">Pub/Sub -> punsubscribe</a>
     * @param pattern 表达式匹配符。每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
     *                news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
     * @param subscribedChannels 订阅
     */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("取消订阅 ---> 表达式:" + pattern + ", 订阅频道:" + subscribedChannels);
    }
}
