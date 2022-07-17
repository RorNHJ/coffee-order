package com.hyunjina.coffeeorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : MenuProductEntity.java
 * @Description : 메뉴-상품 관계 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Entity
@Table(name = "MENU_PRODUCT")		// 메뉴 - 상품 관계테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@IdClass(MenuProductId.class)
public class MenuProductEntity extends AbstractEntity {
	@Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
	@Comment("상품ID")
	private ProductEntity productId;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MENU_ID")
	@Comment("메뉴ID")
	private MenuEntity menuId; 
}
