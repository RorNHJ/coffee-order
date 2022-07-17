package com.hyunjina.coffeeorder.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

/**
 * @class Name : PointDetailDto.java
 * @Description : 포인트 상세 내역 return dto
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Builder
@Getter
public class PointDetailDto {
	private BigDecimal point;          // 포인트
	private String pointTypeCode;      // Point 유형 Code
	private String pointTypeName;      // Point 유형 name
	private String expDate;            // 포인트 만료일자 yyyyMMdd
	private String remark;             // 비고
}
