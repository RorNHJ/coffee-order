package com.hyunjina.coffeeorder.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @class Name : OrderReqDto.java
 * @Description : 주문등록 request Dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderReqDto {
	
	@NotBlank(message = "고객Id는 필수값 입니다.")
	private String customerId;                 // 고객 아이디
	
	@Size(min = 1, message = "1개 이상의 메뉴를 주문해주세요.")
	private List<QyByMenuReqDto> menuList;     // 주문 메뉴 아이디 목록
	
	private String orderDt;                    // 주문일시
	private String orderNo;                    // 주문번호

}
