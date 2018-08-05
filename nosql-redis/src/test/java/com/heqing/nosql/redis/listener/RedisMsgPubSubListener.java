package com.heqing.nosql.redis.listener;

import redis.clients.jedis.JedisPubSub;

/**
 * @see <a href="http://redisdoc.com/pub_sub/index.html">Pub/Sub（发布/订阅）</a>
 * @author heqing
 * @date 2018/8/5 20:04
 */
public class RedisMsgPubSubListener extends JedisPubSub {

    /**
     * 退订所有订阅的频道
     * @see <a href="http://redisdoc.com/pub_sub/unsubscribe.html">Pub/Sub -> unsubscribe</a>
     */
    @Override
    public void unsubscribe() {
        System.out.println("unsubscribe --->");
        super.unsubscribe();
    }

    /**
     * 退订给定的频道
     * @see <a href="http://redisdoc.com/pub_sub/unsubscribe.html">Pub/Sub -> unsubscribe</a>
     * @param channels 一个或多个频道
     */
    @Override
    public void unsubscribe(String... channels) {
        System.out.println("unsubscribe ---> channel:" + channels);
        super.unsubscribe(channels);
    }

    /**
     * 订阅给定的频道
     * @see <a href="http://redisdoc.com/pub_sub/subscribe.html">Pub/Sub -> subscribe</a>
     * @param channels 一个或多个频道
     */
    @Override
    public void subscribe(String... channels) {
        System.out.println("subscribe ---> channel:" + channels);
        super.subscribe(channels);
    }

    @Override
    public void punsubscribe() {
        System.out.println("punsubscribe");
        super.punsubscribe();
    }

    /**
     * 订阅一个或多个符合给定模式的频道
     * @see <a href="http://redisdoc.com/pub_sub/psubscribe.html">Pub/Sub -> psubscribe</a>
     * @param patterns 匹配符。每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
     *                news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
     */
    @Override
    public void psubscribe(String... patterns) {
        System.out.println("psubscribe ---> patterns:" + patterns);
        super.psubscribe(patterns);
    }

    /**
     * 退订阅一个或多个符合给定模式的频道
     * @see <a href="http://redisdoc.com/pub_sub/psubscribe.html">Pub/Sub -> psubscribe</a>
     * @param patterns 匹配符。每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
     *                news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
     */
    @Override
    public void punsubscribe(String... patterns) {
        System.out.println("punsubscribe ---> patterns:" + patterns);
        super.punsubscribe(patterns);
    }

    /**
     * 接收到的消息
     * @param channel 频道
     * @param message 消息内容
     */
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage ---> channel:" + channel + "receives message :" + message);
        this.unsubscribe();
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage ---> pattern:" + pattern + ", channel:" + channel+", message:"+message);
    }

    /**
     * 开始订阅时收到的消息
     * @param channel 频道
     * @param subscribedChannels 订阅
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe ---> channel:" + channel + ", subscribedChannels:" + subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPUnsubscribe ---> pattern:" + pattern + ", subscribedChannels:" + subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe ---> pattern:" + pattern + ", subscribedChannels:" + subscribedChannels);
    }

    /**
     * 取消订阅时收到的消息
     * @param channel 频道
     * @param subscribedChannels 订阅
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("onUnsubscribe ---> channel:" + channel + ", subscribedChannels" + subscribedChannels);
    }

}
