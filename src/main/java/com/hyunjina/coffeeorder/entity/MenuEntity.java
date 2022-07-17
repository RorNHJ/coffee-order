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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : MenuEntity.java
 * @Description : 메뉴 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Entity
@Table(name = "MENU")		// 메뉴 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class MenuEntity extends AbstractEntity {
	
	@Id
	@Comment("메뉴 아이디")
	private String menuId;
	
	@Comment("메뉴 명")
	private String menuNm;
	
	@Comment("메뉴 가격")
	private BigDecimal menuPrice;
	
	@Comment("사용 여부")
	private String useYn; 
	
	@OneToMany(mappedBy = "menuId", fetch = FetchType.LAZY)	// 상품정보 get 시점에 조회
	private List<MenuProductEntity> menuProduct = new ArrayList<>();
	
	@OneToMany(mappedBy = "menuId", fetch = FetchType.LAZY)	// get 시점에 조회
	private List<OrderMenuEntity> orderMenu = new ArrayList<>();

}
