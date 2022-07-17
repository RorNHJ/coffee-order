package com.hyunjina.coffeeorder.kafka;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @class Name : AbstractEvent.java
 * @Description : 카프카 이벤트 객체 
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 11.		나현지			최초 생성
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEvent implements Serializable{
	public String id;
	public String id2;
	public String frstCreUsrId;            // 최초 생성자 아이디
	public String lastUpdUsrId;            // 최종 수정자 아이디
	public LocalDateTime  frstCreDt;       // 최초 생성 일자
	public LocalDateTime  lastUpdDt;       // 최종 수정 일자
}
