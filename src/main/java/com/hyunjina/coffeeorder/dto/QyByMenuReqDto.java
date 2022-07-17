package com.hyunjina.coffeeorder.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @class Name : QyByMenuReqDto.java
 * @Description : 주문 등록 시, 메뉴별 수량 request Dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QyByMenuReqDto {
	
	@NotBlank(message = "메뉴Id는 필수값 입니다.")
	private String menuId;     // 메뉴 아이디
	
	@Size(min = 1, message = "1개 이상의 메뉴를 선택해주세요.")
	private BigDecimal qy;     // 수량
	
}
