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
public class ChargePointResDto {
	
	private BigDecimal beforeChargePoint;      // 충전 전 포인트
	private BigDecimal chargeAmt;              // 충전 잔액
	private BigDecimal afterChargePoint;       // 충전 후 포인트
}
