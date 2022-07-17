package com.hyunjina.coffeeorder.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : ProductEntity.java
 * @Description : 상품 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Entity
@Table(name = "PRODUCT")		// 상품 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class ProductEntity extends AbstractEntity {
	@Id
	@Comment("상품 아이디")
	private String productId; 
	
	@Comment("상품 명")
	private String productNm; 
	
	@Comment("가격")
	private BigDecimal price; 
	
	@OneToMany(mappedBy = "productId")
	private List<MenuProductEntity> menuProductEntity = new ArrayList<>();
}
