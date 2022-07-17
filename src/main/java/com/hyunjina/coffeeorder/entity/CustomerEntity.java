package com.hyunjina.coffeeorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : CustomerEntity.java
 * @Description : 고객 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */

@Entity
@Table(name = "CUSTOMER")		// 고객 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class CustomerEntity extends AbstractEntity {
	@Id
	@Comment("고객 아이디")
	private String customerId;
}
