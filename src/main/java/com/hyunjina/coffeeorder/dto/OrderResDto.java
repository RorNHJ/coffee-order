package com.hyunjina.coffeeorder.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @class Name : OrderResDto.java
 * @Description : 주문등록 response Dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 13.		나현지			최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderResDto {
	
	private String orderNo;                // 주문 번호
	private BigDecimal orderPrice;         // 주문 금액
	private BigDecimal usePoint;           // 사용한 포인트
	private boolean mockApiSuccessYn;      // mock api 호출 성공여부
}
