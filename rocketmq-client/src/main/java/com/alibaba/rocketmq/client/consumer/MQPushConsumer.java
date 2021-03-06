/**
 * $Id: MQPushConsumer.java 1831 2013-05-16 01:39:51Z shijia.wxr $
 */
package com.alibaba.rocketmq.client.consumer;

import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.exception.MQClientException;


/**
 * 消费者，被动方式消费
 * 
 * @author shijia.wxr<vintage.wang@gmail.com>
 */
public interface MQPushConsumer extends MQConsumer {
    /**
     * 启动服务，调用之前确保registerMessageListener与subscribe都已经调用<br>
     * 或者已经通过Spring注入了相关配置
     * 
     * @throws MQClientException
     */
    public void start() throws MQClientException;


    /**
     * 关闭服务，一旦关闭，此对象将不可用
     */
    public void shutdown();


    /**
     * 注册消息监听器，一个Consumer只能有一个监听器
     * 
     * @param messageListener
     */
    public void registerMessageListener(final MessageListener messageListener);


    /**
     * 订阅消息，方法可以调用多次来订阅不同的Topic，也可覆盖之前Topic的订阅过滤表达式
     * 
     * @param topic
     *            消息主题
     * @param subExpression
     *            订阅过滤表达式字符串，broker依据此表达式进行过滤。目前只支持或运算<br>
     *            eg: "tag1 || tag2 || tag3"<br>
     *            如果subExpression等于null或者*，则表示全部订阅
     * @param listener
     *            消息回调监听器
     * @throws MQClientException
     */
    public void subscribe(final String topic, final String subExpression) throws MQClientException;


    /**
     * 取消订阅，从当前订阅组内注销，消息会被订阅组内其他订阅者订阅
     * 
     * @param topic
     *            消息主题
     */
    public void unsubscribe(final String topic);


    /**
     * 消费线程挂起，暂停消费
     */
    public void suspend();


    /**
     * 消费线程恢复，继续消费
     */
    public void resume();
}
