package com.hyunjina.coffeeorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @class Name : MockPlatformResDto.java
 * @Description : Mock api 응답 Dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 13.		나현지			최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MockPlatformResDto {
	private OrderReqDto data;	
}
