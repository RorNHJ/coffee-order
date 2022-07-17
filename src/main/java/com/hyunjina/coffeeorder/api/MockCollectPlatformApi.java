package com.hyunjina.coffeeorder.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyunjina.coffeeorder.dto.MockPlatformResDto;
import com.hyunjina.coffeeorder.dto.OrderReqDto;

/**
 * @class Name : MockCollectPlatformApi.java
 * @Description :  데이터 수집 플랫폼 외부 api interface
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 13.		나현지			최초 생성
 */
@FeignClient(value="mockCollectPlatformApi" , url = "${mockCollectPlatform.url}")
public interface MockCollectPlatformApi {
	
	/**
	 * @author 나현지
	 * @date 2022. 7. 13.
	 * @description Json형식으로 보내고 Json 형태로 받아온다고 가정.
	 */
	@PostMapping(value = "/post" ,  consumes = "application/json", produces = "application/json")
	@ResponseBody MockPlatformResDto sendOrderData(@RequestBody OrderReqDto reqText);
}
