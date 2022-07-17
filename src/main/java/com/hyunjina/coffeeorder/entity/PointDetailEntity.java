package com.hyunjina.coffeeorder.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import com.hyunjina.coffeeorder.code.PointType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : PointDetailEntity.java
 * @Description : 포인트상세 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Entity
@Table(name = "POINT_DETAIL")		// 포인트 상세 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class PointDetailEntity extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Comment("포인트상세ID")
	private Long pointDetailId; 
	
	@ManyToOne
	@JoinColumn(name = "POINT_ID")
	@Comment("포인트ID")
	private PointEntity pointId; 
	
	@Enumerated(EnumType.STRING)
	@Comment("포인트 유형")
	private PointType pointType;	// 유형 ( 충전, 적립, 소멸, 사용) PointType
	
	@Comment("포인트")
	private BigDecimal point;    
	
	@Comment("만료일자-yyyyMMdd")
	private String expDate;	 
	
	@Comment("비고")
	private String remark;	 
	
}
