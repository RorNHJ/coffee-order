package com.hyunjina.coffeeorder.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @class Name : ChargePointDto.java
 * @Description : 포인트 충전 request Dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ChargePointReqDto {
	
	@NotBlank(message = "고객Id는 필수값 입니다.")
	private String customerId;                 // 고객 아이디
	
	@Min(value = 100, message = "100원 이상 충전이 가능합니다. ")
	private BigDecimal chargeAmt;              // 충전금액
}
