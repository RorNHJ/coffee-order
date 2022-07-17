package com.hyunjina.coffeeorder.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunjina.coffeeorder.dto.ChargePointReqDto;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
class PointControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * @throws Exception 
	 * @description  포인트_충전_하기_API
	 * 
	 * @case 	포인트가 0인 CUST-00000003 고객이 , 충전금액 10000원을 입력 하여 포인트를 충전 (1원=1P)
	 * @success http 응답 코드가 OK 이면 성공
	 * @success 충전 전 포인트가 0 이면 성공
	 * @success 충전 후 포인트가 10000 이면 성공
	 */
	@Test
	void 포인트_충전_하기_API() throws Exception {
		
		// given
		String customerId = "CUST-00000003" ;				// 사용자 식별값
		BigDecimal chargeAmt = new BigDecimal(10000);		// 충전 금액
		
		String content = objectMapper.writeValueAsString(ChargePointReqDto.builder()
                                                        		        .customerId(customerId)
                                                        		        .chargeAmt(chargeAmt)
                                                        		        .build()
                                                        		        );
		
		// when and then
		mockMvc.perform(post("/point")
		        .content(content)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.beforeChargePoint").value(BigDecimal.ZERO))          // 충전 전 포인트
        		.andExpect(MockMvcResultMatchers.jsonPath("$.afterChargePoint").value(new BigDecimal(10000)))     // 충전 후 포인트
        		;
		
	}
}
