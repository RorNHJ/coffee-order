package com.hyunjina.coffeeorder.controller;

import java.util.List;

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

import com.hyunjina.coffeeorder.dto.ChargePointReqDto;
import com.hyunjina.coffeeorder.dto.ChargePointResDto;
import com.hyunjina.coffeeorder.dto.PointInfoDto;
import com.hyunjina.coffeeorder.service.PointService;


/**
 * @class Name : PointController.java
 * @Description : 포인트 조회, 충전, 차감, 소멸 컨트롤러
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@RestController
@RequestMapping("/point")
public class PointController {
	private static final Logger logger = LoggerFactory.getLogger(PointController.class);
	
	@Autowired
	private PointService pointService;
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 10.
	 * @description 고객id로 포인트 상세 내역 조회
	 */
	@GetMapping("/{customerId}")
	public ResponseEntity<List<PointInfoDto>> searchPoint(@PathVariable("customerId") String customerId) throws Exception{
		return 	ResponseEntity
		        .status(HttpStatus.OK)
		        .body(pointService.getPoint(customerId));
	}
	
	
	/**
	 * @author 나현지
	 * @return 
	 * @date 2022. 7. 10.
	 * @description 포인트 충전
	 */
	@PostMapping
	public ResponseEntity<ChargePointResDto> chargePoint(@RequestBody @Valid ChargePointReqDto chargePointReqDto) throws Exception{
		return 	ResponseEntity
		        .status(HttpStatus.OK)
		        .body(pointService.chargePoint(chargePointReqDto));
	}
}
