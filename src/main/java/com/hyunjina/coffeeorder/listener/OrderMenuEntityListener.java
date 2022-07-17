package com.hyunjina.coffeeorder.listener;


import javax.persistence.PostPersist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hyunjina.coffeeorder.entity.OrderMenuEntity;
import com.hyunjina.coffeeorder.kafka.AbstractEvent;
import com.hyunjina.coffeeorder.util.ApplicationContextProvider;

/**
 * @class Name : OrderMenuEntityListener.java
 * @Description : 주문-메뉴 관계 엔티티 리스너
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 11.		나현지			최초 생성
 */
public class OrderMenuEntityListener  {
	private static final Logger logger = LoggerFactory.getLogger(OrderMenuEntityListener.class);
	
	private final ObjectMapper mapper = new ObjectMapper();
    /**
     * @author 나현지
     * @date 2022. 7. 11.
     * @description 메뉴 주문 시, 이벤트 발행
     */
    @PostPersist
    public void postPersist(OrderMenuEntity e) throws JsonProcessingException {
    
		StreamBridge streamBridge = ApplicationContextProvider.getApplicationContext().getBean(StreamBridge.class);
		boolean result = streamBridge.send("order-create",mapper.registerModule(new JavaTimeModule())
                                                		        .writeValueAsString( AbstractEvent.builder()
                                                                            		                .id(e.getOrderNo().getOrderNo())
                                                                            		                .id2(e.getMenuId().getMenuId())
                                                                            		                .frstCreDt(e.getFrstCreDt())
                                                                            		                .lastUpdDt(e.getLastUpdDt())
                                                                            		                .frstCreUsrId(e.getFrstCreUsrId())
                                                                            		                .lastUpdUsrId(e.getLastUpdUsrId())
                                                                            		                .build()
                                                                            		                )
                                                		        );
		logger.debug("order-create 토픽으로 이벤트 메시지 send 성공 여부 : " + result);
	}
}