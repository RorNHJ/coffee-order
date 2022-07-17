package com.hyunjina.coffeeorder.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hyunjina.coffeeorder.code.ErrorCode;
import com.hyunjina.coffeeorder.code.OrderState;
import com.hyunjina.coffeeorder.config.BizException;
import com.hyunjina.coffeeorder.dto.MenuInfoDto;
import com.hyunjina.coffeeorder.dto.OrderInfoDto;
import com.hyunjina.coffeeorder.dto.OrderReqDto;
import com.hyunjina.coffeeorder.dto.OrderResDto;
import com.hyunjina.coffeeorder.dto.QyByMenuReqDto;
import com.hyunjina.coffeeorder.entity.CustomerEntity;
import com.hyunjina.coffeeorder.entity.MenuEntity;
import com.hyunjina.coffeeorder.entity.OrderEntity;
import com.hyunjina.coffeeorder.entity.OrderMenuEntity;
import com.hyunjina.coffeeorder.repository.CustomerRepository;
import com.hyunjina.coffeeorder.repository.MenuRepository;
import com.hyunjina.coffeeorder.repository.OrderMenuRepository;
import com.hyunjina.coffeeorder.repository.OrderRepository;
/**
 * @class Name : OrderService.java
 * @Description : 주문 CRUD 서비스
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Service
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private OrderMenuRepository orderMenuRepository;

	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PointService pointService;
	
	@Autowired
	private MockCollectOrderPlatformService mcopService;
	
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 10.
	 * @description 주문 정보 조회
	 * 1. 주문 정보 조회
	 */
	public OrderInfoDto  getOrderInfo(String orderNo) {
		
		OrderInfoDto rtnDto = null;
		List<MenuInfoDto> menuInfoDtoList = new ArrayList<>();
		
		
		/** 주문 정보 조회 */
		Optional<OrderEntity> orderOpt = orderRepository.findById(orderNo);	
		orderOpt.orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND_DATA, "주문 정보"));
		OrderEntity orderEntity = orderOpt.get();
			
			
		orderEntity.getOrderMenu().forEach(e -> {
		    MenuEntity menuEntity = e.getMenuId();
			menuInfoDtoList.add(MenuInfoDto
            			        .builder()
            			        .menuId(menuEntity.getMenuId())
            			        .menuNm(menuEntity.getMenuNm())
            			        .menuPrice(menuEntity.getMenuPrice())
            			        .build()
            			        );
		});
			
		
		rtnDto = OrderInfoDto
		        .builder()
		        .orderNo(orderEntity.getOrderNo())
		        .customerId(orderEntity.getCustomerId())
		        .orderPrice(orderEntity.getOrderPrice())
		        .payPrice(orderEntity.getPayPrice())
		        .usePoint(orderEntity.getUsePoint())
				.orderStateCode(orderEntity.getOrderState().getCode())
				.orderStateName(orderEntity.getOrderState().getName())
				.orderMenu(menuInfoDtoList)
				.build();
		
		return rtnDto;
	}
	
	/**
	 * @author 나현지
	 * @throws JsonProcessingException 
	 * @date 2022. 7. 10.
	 * @description 주문 등록
	 * 1. 고객 조회
	 * 2. 주문금액,결제금액 계산
	 * 3. 포인트 결제
	 * 4. 엔티티 생성 ( 주문,  주문-메뉴  )
	 * 5. 저장 ( 주문,  주문-메뉴  )
	 * 6. 주문 내역을 데이터 수집 플랫폼으로 실시간 전송
	 * 7. 주문번호, 결제금액, 차감된 포인트를 응답.
	 */
	@Transactional
	public OrderResDto  createOrder(OrderReqDto orderReqDto) throws JsonProcessingException {
		
		/** 고객 조회 */
	    Optional<CustomerEntity> customerOpt = customerRepository.findById(orderReqDto.getCustomerId());
	    customerOpt.orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND_DATA, "고객 정보"));
	    
		/** 주문 금액, 결제해야할 금액 계산 */
	  	List<QyByMenuReqDto> qyByMenuReqList = orderReqDto.getMenuList(); 
	  	Map<String, BigDecimal> menuQyMap = qyByMenuReqList
                            	  	        .stream()
                            	  	        .collect(Collectors.toMap(
                            	  	                i1 -> i1.getMenuId(),
                            	  	                i2 -> i2.getQy())
                            	  	                );
	 	List<MenuEntity> menuEntityList = menuRepository.findByMenuIdIn(qyByMenuReqList
                                                        	 	        .stream()
                                                        	 	        .map(e -> e.getMenuId())
                                                        	 	        .collect(Collectors.toList())
                                                        	 	        ); 						  	
	    Optional<BigDecimal> orderPrice = menuEntityList.stream()
                        	            .map( e -> e.getMenuPrice().multiply(menuQyMap.get(e.getMenuId())))
                        	            .reduce(BigDecimal::add);
		

	    /** 포인트 결제 */
	    pointService.payByPoint(orderReqDto.getCustomerId(), orderPrice.orElse(BigDecimal.ZERO));
	    
	    
		/** 엔티티 생성 ( 주문,  주문-메뉴  ) */
	    // 주문 객체 생성 전, 주문번호 시퀀스 생성
	    String orderNo = sequenceService.getSequence("ORD");	 
	    // 엔티티 생성 (주문) 
	    OrderEntity orderEntity = OrderEntity.builder()
                            	            .orderNo(orderNo)
                            	            .customerId(orderReqDto.getCustomerId())
                            	            .orderPrice(orderPrice.orElse(BigDecimal.ZERO))
                            	            .payPrice(orderPrice.orElse(BigDecimal.ZERO))
                            	            .usePoint(orderPrice.orElse(BigDecimal.ZERO))
                            	            .orderState(OrderState.COMPLETE)
                            	            .ordertDt(LocalDateTime.now())
                            	            .frstCreUsrId(orderReqDto.getCustomerId())
                            	            .lastUpdUsrId(orderReqDto.getCustomerId())
                            	            .build();
		
	    
	    
	    
	    // 엔티티 생성 (주문-메뉴) 
	    List<OrderMenuEntity> orderMenuEntityList = new ArrayList<OrderMenuEntity>();
	    menuEntityList.forEach(menuEntity ->  orderMenuEntityList.add(  OrderMenuEntity.builder()
                                                                        	            .orderNo(orderEntity)
                                                                        	            .menuId(menuEntity)
                                                                        	            .qy(menuQyMap.get(menuEntity.getMenuId()))
                                                                        	            .frstCreUsrId(orderReqDto.getCustomerId())
                                                                        	            .lastUpdUsrId(orderReqDto.getCustomerId())
                                                                        	            .build()
                                                                        	            )
	            );
	    
	    
	    
	    /** 저장 ( 주문,  주문-메뉴  ) */
	    orderRepository.save(orderEntity);                    // 저장 (주문) 
	 	orderMenuRepository.saveAll(orderMenuEntityList);     // 저장 (주문-메뉴)
	    
		/** 주문 내역을 데이터 수집 플랫폼으로 실시간 전송 */
	 	boolean mockApiSuccessYn = mcopService.sendOrderData(orderReqDto.toBuilder()
                                                        	 	        .orderNo(orderEntity.getOrderNo())
                                                        	 	        .orderDt(orderEntity.getOrdertDt().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                                                        	 	        .build()
                                                        	 	        );
	 	
	 	return OrderResDto.builder()
        	 	        .orderNo(orderEntity.getOrderNo())
        	 	        .orderPrice(orderEntity.getOrderPrice())
        	 	        .usePoint(orderEntity.getUsePoint())
        	 	        .mockApiSuccessYn(mockApiSuccessYn)
        	 	        .build();

	}
}
