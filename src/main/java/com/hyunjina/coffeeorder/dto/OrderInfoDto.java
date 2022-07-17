package com.hyunjina.coffeeorder.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @class Name : OrderInfoDto.java
 * @Description : 주문 정보 return dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderInfoDto {
	private String orderNo;                // 주문번호
	private String customerId;             // 고객아이디
	private BigDecimal orderPrice;         // 주문 가격
	private BigDecimal payPrice;           // 결제 가격
	private BigDecimal usePoint;           // 사용 포인트
	private String orderStateCode;         // 주문 상태 Code
	private String orderStateName;         // 주문 상태 name
	private List<MenuInfoDto> orderMenu;   // 주문 메뉴 내역
}
