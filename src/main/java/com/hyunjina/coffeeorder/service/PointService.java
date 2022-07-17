package com.hyunjina.coffeeorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyunjina.coffeeorder.code.ErrorCode;
import com.hyunjina.coffeeorder.code.PointType;
import com.hyunjina.coffeeorder.config.BizException;
import com.hyunjina.coffeeorder.dto.ChargePointReqDto;
import com.hyunjina.coffeeorder.dto.ChargePointResDto;
import com.hyunjina.coffeeorder.dto.PointDetailDto;
import com.hyunjina.coffeeorder.dto.PointInfoDto;
import com.hyunjina.coffeeorder.entity.PointDetailEntity;
import com.hyunjina.coffeeorder.entity.PointEntity;
import com.hyunjina.coffeeorder.repository.PointDetailRepository;
import com.hyunjina.coffeeorder.repository.PointRepository;
/**
 * @class Name : PointService.java
 * @Description : 포인트 CRUD 서비스
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Service
public class PointService {
	private static final Logger logger = LoggerFactory.getLogger(PointService.class);

	@Autowired
	private PointRepository pointRepository;
	@Autowired
	private PointDetailRepository pointDetailRepository;
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 10.
	 * @description 포인트 정보 조회
	 * 1. 고객의 포인트 조회
	 * 2. 엔티티로부터 조회용 Dto 생성
	 */
	public List<PointInfoDto>  getPoint(String customerId) {
		
		List<PointInfoDto> rtnList = new ArrayList<>();
		
		/** 고객의 포인트 조회 */
		Optional<PointEntity> pointOpt = pointRepository.findOneByCustomerId(customerId);	
		pointOpt.orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND_DATA, "고객 정보"));
		PointEntity pointEntity = pointOpt.get();
		
		
		
		/** 엔티티로부터 조회용 Dto 생성 */
		List<PointDetailEntity> pointDetailEntityList = pointEntity.getPointDetailEntity();
		List<PointDetailDto> PointDetailDtoList = new ArrayList<>();
			
		pointDetailEntityList.forEach(e -> {
		    PointDetailDtoList.add(PointDetailDto.builder()
                            		            .point(e.getPointType().calculate(e.getPoint()))
                            		            .pointTypeCode(e.getPointType().getCode())
                            		            .pointTypeName(e.getPointType().getName())
                            		            .expDate(e.getExpDate())
                            		            .remark(e.getRemark())
                            		            .build()
                            		            );
		    });
			
		rtnList.add(PointInfoDto.builder()
                		        .customerId(pointEntity.getCustomerId())
                		        .point(pointEntity.getPoint())
                		        .pointDetail(PointDetailDtoList)
                		        .build()
                		        );
		
		return rtnList;
	}
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 10.
	 * @description 포인트 충전
	 * 1. 고객의 포인트 조회
	 * 2. 포인트 충전
	 * 3. 엔티티 수정, 생성 (포인트, 포인트 상세)
	 * 4. 저장 (포인트, 포인트 상세)
	 * 5. 충전 전 포인트, 충전 금액 , 충전 후 포인트를 응답
	 */
	@Transactional
	public ChargePointResDto  chargePoint(ChargePointReqDto chargePointReqDto) {
		
		/** 고객의 포인트 조회 */
		Optional<PointEntity> pointOpt = pointRepository.findOneByCustomerId(chargePointReqDto.getCustomerId());
		pointOpt.orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND_DATA, "고객 정보"));
		PointEntity pointEntity = pointOpt.get();
		
		/** 포인트 충전 */
		BigDecimal originPoint = pointEntity.getPoint();                              // 기존 포인트
		BigDecimal addedPoint = originPoint.add(chargePointReqDto.getChargeAmt());    // 기존 포인트 + 충전금액
		
		/** 엔티티 수정,생성 (포인트, 포인트 상세) */
	    // 엔티티 수정 (포인트) 
		pointEntity = pointEntity.toBuilder()
                		        .point(addedPoint)
                		        .lastUpdUsrId(chargePointReqDto.getCustomerId())
                		        .build();
		
		// 엔티티 생성 (포인트 상세) 
		PointDetailEntity pointDetailEntity = PointDetailEntity.builder()
                                                		        .pointId(pointEntity)
                                                		        .pointType(PointType.CHARGE)
                                                		        .point(chargePointReqDto.getChargeAmt())
                                                		        .expDate(null)
                                                		        .remark("고객 충전")
                                                		        .frstCreUsrId(chargePointReqDto.getCustomerId())
                                                		        .lastUpdUsrId(chargePointReqDto.getCustomerId())
                                                		        .build();
		
		
		
		/** 저장 (포인트, 포인트 상세) */
		pointRepository.saveAndFlush(pointEntity);                // 저장 (포인트)
		pointDetailRepository.saveAndFlush(pointDetailEntity);    // 저장 (포인트 상세)
		
		return ChargePointResDto.builder()
                		        .beforeChargePoint(originPoint)
                		        .chargeAmt(chargePointReqDto.getChargeAmt())
                		        .afterChargePoint(addedPoint)
                		        .build();
	}	
	
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 10.
	 * @description 포인트 결제
	 * 1. 고객의 포인트 조회
	 * 2. 포인트 차감
	 * 3. 엔티티 수정, 생성 (포인트, 포인트 상세)
	 * 4. 저장 (포인트, 포인트 상세)
	 */
	@Transactional
	public void  payByPoint(String customerId , BigDecimal payPrice) {
		
		/** 고객의 포인트 조회 */
		Optional<PointEntity> pointOpt = pointRepository.findOneByCustomerId(customerId);	
		pointOpt.orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND_DATA, "고객 정보"));
		PointEntity pointEntity = pointOpt.get();
		
		
		
		/** 포인트 차감 */
		BigDecimal originPoint = pointEntity.getPoint();                  // 기존 포인트
		BigDecimal substractedPoint =originPoint.subtract(payPrice);      // 기존 포인트 - 결제 포인트
		
		//차감 후의 포인트가 결제금액보다 부족한지 확인
		if(substractedPoint.compareTo(BigDecimal.ZERO) < 0)
			throw new BizException(ErrorCode.NO_POINT,substractedPoint.abs().toBigInteger().toString());
		
		
		/** 엔티티 수정,생성 (포인트, 포인트 상세) */
	    // 엔티티 수정 (포인트) 
		pointEntity = pointEntity.toBuilder()
                		        .point(substractedPoint)
                		        .lastUpdUsrId(customerId)
                		        .build();
		// 엔티티 생성 (포인트 상세) 
		PointDetailEntity pointDetailEntity = PointDetailEntity.builder()
                                                		        .pointId(pointEntity)
                                                		        .pointType(PointType.USE)
                                                		        .point(payPrice)
                                                		        .expDate(null)
                                                		        .remark("포인트 결제")
                                                		        .frstCreUsrId(customerId)
                                                		        .lastUpdUsrId(customerId)
                                                		        .build();
		
		/** 저장 (포인트, 포인트 상세) */
		pointRepository.saveAndFlush(pointEntity);                    // 저장 (포인트)
		pointDetailRepository.saveAndFlush(pointDetailEntity);        // 저장 (포인트 상세)
	}
}
