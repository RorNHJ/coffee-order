package com.hyunjina.coffeeorder.code;

/**
 * @class Name : OrderState.java
 * @Description : 주문상태 공통코드
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
public enum OrderState {
	COMPLETE("COMPLETE","주문완료"),
	CANCEL("CANCEL","주문취소"),
    FAIL("FAIL","주문실패") 
    ;

    private final String code;
    private final String name;
    
	private OrderState(String code, String name ) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
    
}
