package com.hyunjina.coffeeorder.entity;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import com.hyunjina.coffeeorder.listener.OrderMenuEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : OrderMenuEntity.java
 * @Description : 주문-메뉴 관계 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@EntityListeners(OrderMenuEntityListener.class)
@Entity
@Table(name = "ORDER_MENU")		// 주문 - 메뉴 관계테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@IdClass(OrderMenuId.class)

public class OrderMenuEntity extends AbstractEntity {
	
	@Id
    @ManyToOne
    @JoinColumn(name = "ORDER_NO")
	@Comment("주문번호")
	private OrderEntity orderNo; 
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MENU_ID")
	@Comment("메뉴ID")
	private MenuEntity menuId; 
	
	@Comment("수량")
	private BigDecimal qy; 
	
}
