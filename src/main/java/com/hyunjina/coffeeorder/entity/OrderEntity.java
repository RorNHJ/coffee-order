package com.hyunjina.coffeeorder.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import com.hyunjina.coffeeorder.code.OrderState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @class Name : OrderEntity.java
 * @Description : 주문 엔티티
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 11.		나현지			최초 생성
 */

@Entity
@Table(name = "COFFEE_ORDER")		// 주문 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class OrderEntity extends AbstractEntity {
	
	@Id
	@Comment("주문번호")
	private String orderNo;  
	
	@Comment("고객 아이디")
	private String customerId;
	
	@Comment("주문 가격")
	private BigDecimal orderPrice;
	
	@Comment("주문 일시")
	private LocalDateTime  ordertDt;
	
	@Comment("결제 가격")
	private BigDecimal payPrice;   	
	
	@Comment("사용 포인트")
	private BigDecimal usePoint; // ( 결제 성공시, 결제금액이랑 같은 거고, 결제 실패 시, 사용한 포인트은 0P)
	
	
	@Comment("주문 상태")
	@Enumerated(EnumType.STRING)
	private OrderState orderState;
	
	@OneToMany(mappedBy = "orderNo", fetch = FetchType.LAZY)	// get 시점에 조회
	private List<OrderMenuEntity> orderMenu = new ArrayList<>();
	

}
