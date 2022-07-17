package com.hyunjina.coffeeorder.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
/**
 * @class Name : SequenceEntity.java
 * @Description : 시퀀스 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Entity
@Table(name = "CUSTOM_SEQUENCE")		// 커스텀 시퀀스 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class SequenceEntity extends AbstractEntity {

	@Id
	@Comment("시퀀스명")
	private String seqNm;
	
	@Comment("현재 값")
	private BigDecimal nowVal;
	
	@Comment("최대 값")
	private BigDecimal maxVal;
	
}
