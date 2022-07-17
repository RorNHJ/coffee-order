package com.hyunjina.coffeeorder.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunjina.coffeeorder.dto.OrderReqDto;
import com.hyunjina.coffeeorder.dto.QyByMenuReqDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * @throws Exception 
	 * @description  사용자 식별값, 메뉴ID를 입력 받아 주문을 하고 결제를 진행
	 * @case 	CUST-00000001 고객이 MENU-00000001 메뉴 1개와 MENU-00000003 메뉴 2개를 주문
	 * MENU-00000001의 개당 가격은 4200원
	 * MENU-00000003의 개당 가격은 4500원
	 * 
	 * 주문 내역을 데이터 수집 플랫폼으로 실시간 전송하는 로직을 추가합니다.
	 * (Mock API 등을 사용하여 사용자 식별값, 메뉴ID, 결제금액을 전송합니다.)
	 * @success http 응답 코드가 OK 이면 성공
	 * @success 주문번호가 존재하면 성공
	 * @success 주문금액이 13200 이면 성공
	 * @success 사용포인트가  13200 이면 성공
	 * @success mock 데이터 수집플랫폼 api 호출 성공여부가 true 이면 성공
	 */
	

	@Test
	void 커피_주문_결제_하기_API() throws Exception {
		
		// given
		String customerId = "CUST-00000001" ;						// 사용자 식별값
		List<QyByMenuReqDto> menuIdList = new ArrayList<>();		// 메뉴ID 
		menuIdList.add(QyByMenuReqDto.builder().menuId("MENU-00000001").qy(new BigDecimal(1)).build());
		menuIdList.add(QyByMenuReqDto.builder().menuId("MENU-00000003").qy(new BigDecimal(2)).build());
		
		String content = objectMapper.writeValueAsString(OrderReqDto.builder()
                                                    		        .customerId(customerId)
                                                    		        .menuList(menuIdList)
                                                    		        .build()
                                                    		        );

		// when and then
		mockMvc.perform(post("/order")
		        .content(content)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.orderNo").exists())                          // 주문번호
        		.andExpect(MockMvcResultMatchers.jsonPath("$.orderPrice").value(new BigDecimal(13200)))   // 주문 금액
        		.andExpect(MockMvcResultMatchers.jsonPath("$.usePoint").value(new BigDecimal(13200)))     // 사용한 포인트
        		.andExpect(MockMvcResultMatchers.jsonPath("$.mockApiSuccessYn").value(true))              // mock api 호출 성공여부
        		;
		
	}

}
