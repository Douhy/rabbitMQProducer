package com.xiaodou.test;

import com.xiaodou.producer.ProducerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class ProcedureTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 交换器类型为：直接模式 使用默认交换器 “”
     */
    @Test
    public void sendDirectMsg(){
        //参数一：交换器名称  直接模式使用默认交换器 ""
        //参数二：路由关键字  直接模式填写为队列名称
        //参数三：消息（数据）
        rabbitTemplate.convertAndSend("", "queue1", "这是直接消息");
    }

    /**
     * 交换器类型为：分列模式
     * 在平台上完成队列跟交换器绑定
     */
    @Test
    public void sendFanoutMsg(){
        //参数一：交换器名称 创建自定义交换器名称
        //参数二：路由key  不需要写
        //参数三：消息（数据）
        rabbitTemplate.convertAndSend("Exchange1", "", "这是分列消息");
    }

    /**
     * 交换器类型为：主题模式
     * 在平台上完成队列跟交换器绑定；绑定同时设置队列关心话题
     */
    @Test
    public void sendTopicMsg(){
        //参数一：交互器名称
        //参数二：路由key 数据对应标签
        //参数三：消息（数据）
//        rabbitTemplate.convertAndSend("ExchangeXYZ", "user.log", "这是主题消息：用户日志");
//        rabbitTemplate.convertAndSend("ExchangeXYZ", "user.info", "这是主题消息：用户信息");
        rabbitTemplate.convertAndSend("ExchangeXYZ", "order.info", "这是主题消息：订单信息");
    }

}
