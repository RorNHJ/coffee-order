package com.hyunjina.coffeeorder.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : AbstractEntity.java
 * @Description : 엔티티 공통 필드
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */


@Getter
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity  {
	
	@Comment("최초 생성자 아이디")
	@Column(name = "FRST_CRE_USR_ID", nullable = false, updatable = false ,length = 50)
	private String frstCreUsrId;
	
	@Comment("최종 수정자 아이디")
	@Column(name = "LAST_UPD_USR_ID", nullable = false, updatable = false ,length = 50)
	private String lastUpdUsrId;
	
	@CreatedDate
	@Column(name = "FRST_CRE_DT", nullable = false, updatable = false)
	@Comment("최초 생성 일자")
	private LocalDateTime  frstCreDt;
	
	@Column(name = "LAST_UPD_DT", nullable = false )
	@LastModifiedDate
	@Comment("최종 수정 일자")
	private LocalDateTime  lastUpdDt;
}