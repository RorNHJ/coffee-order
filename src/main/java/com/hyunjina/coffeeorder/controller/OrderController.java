package com.hyunjina.coffeeorder.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyunjina.coffeeorder.dto.OrderInfoDto;
import com.hyunjina.coffeeorder.dto.OrderReqDto;
import com.hyunjina.coffeeorder.dto.OrderResDto;
import com.hyunjina.coffeeorder.service.OrderService;


/**
 * @class Name : OrderController.java
 * @Description : 주문 컨트롤러
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 10.
	 * @description 주문 번호로 주문 조회
	 */
	@GetMapping("/{orderNo}")
	public ResponseEntity<OrderInfoDto> searchOrderInfo(@PathVariable("orderNo") String orderNo) throws Exception{
		return 	ResponseEntity
		        .status(HttpStatus.OK)
		        .body(orderService.getOrderInfo(orderNo));
	}
	
	
	/**
	 * @author 나현지
	 * @return 
	 * @date 2022. 7. 10.
	 * @description 주문 생성
	 */
	@PostMapping
	public ResponseEntity<OrderResDto> order(@RequestBody @Valid OrderReqDto orderReqDto) throws Exception{
		;
		return 	ResponseEntity
		        .status(HttpStatus.OK)
		        .body(orderService.createOrder(orderReqDto));
	}
}
