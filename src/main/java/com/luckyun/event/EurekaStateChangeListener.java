package com.luckyun.event;

import com.alibaba.fastjson.JSON;
import com.luckyun.enums.AlertEventEnum;
import com.luckyun.model.msg.AlertMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {
    /**
     * 全局核心事件交换机名称
     */
    public final static String GLOBAL_EVENT_EXCHANGE = "global_event_exchange";
    /**
     * 全局告警事件名称
     */
    public final static String GLOBAL_ALERT_EVENT = "GlobalAlertEvent";
    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @EventListener
    public void listenDwn(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
    	
    	//服务断线事件
    	try {
    		String appName = eurekaInstanceCanceledEvent.getAppName();
            String serverId = eurekaInstanceCanceledEvent.getServerId();
            this.pusblishAlert(new AlertMessage(appName, this.getClass().getName(), serverId, "EurekaInstanceCanceledEvent",AlertEventEnum.EUREKA_STATE_CHANGE));
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    }

    /**
     * 发布全局告警
     * @param alertMessage
     */
    private void pusblishAlert(AlertMessage alertMessage) {
        rabbitTemplate.convertAndSend(GLOBAL_EVENT_EXCHANGE, GLOBAL_ALERT_EVENT, JSON.toJSONString(alertMessage));
    }
}
