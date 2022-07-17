package com.hyunjina.coffeeorder.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunjina.coffeeorder.api.MockCollectPlatformApi;
import com.hyunjina.coffeeorder.code.ErrorCode;
import com.hyunjina.coffeeorder.config.BizException;
import com.hyunjina.coffeeorder.dto.MockPlatformResDto;
import com.hyunjina.coffeeorder.dto.OrderReqDto;

/**
 * @class Name : MockCollectOrderPlatformService.java
 * @Description :  데이터 수집 플랫폼 외부 api 서비스
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 13.		나현지			최초 생성
 */
@Service
public class MockCollectOrderPlatformService {
	private static final Logger logger = LoggerFactory.getLogger(MockCollectOrderPlatformService.class);
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	@Autowired
	private MockCollectPlatformApi mcpApi;
	
	
	/**
	 * @author 나현지
	 * @throws JsonProcessingException 
	 * @date 2022. 7. 13.
	 * @description 주문 내역을 데이터 수집 플랫폼으로 실시간 전송 
	 * echo 서버이기 때문에 요청 데이터와 응답데이터가 일치함
	 * 응답데이터의 주문번호와 요청데이터의 주문번호가 같으면 true, 다르면 false를 응답
	 */
	@Transactional
	public boolean  sendOrderData(OrderReqDto orderReqDto) {
		
		try {
			MockPlatformResDto resData = mcpApi.sendOrderData(orderReqDto);
			
			if(resData != null && resData.getData() != null)
				return resData.getData().getOrderNo().equals(orderReqDto.getOrderNo());
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return false;
	}
}
