package com.hyunjina.coffeeorder.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : PointEntity.java
 * @Description : 포인트 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Entity
@Table(name = "POINT")		// 포인트 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class PointEntity extends AbstractEntity {
	@Id
	@Comment("포인트ID")
	private String pointId; 
	
	@Comment("고객 아이디")
	private String customerId;	 
	
	@Comment("포인트")
	private BigDecimal point;   
	
	@OneToMany(mappedBy = "pointId", fetch = FetchType.LAZY)	// get 시점에 조회
	private List<PointDetailEntity> pointDetailEntity = new ArrayList<>();


}
