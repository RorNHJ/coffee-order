package com.hyunjina.coffeeorder.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * @class Name : PointInfoDto.java
 * @Description : 포인트 정보 return dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Builder
@Getter
public class PointInfoDto {
	private String customerId;                 // 고객 아이디
	private BigDecimal point;                  // 포인트(현재 총 포인트)
	private List<PointDetailDto> pointDetail;  // 포인트 상세 내역
}
