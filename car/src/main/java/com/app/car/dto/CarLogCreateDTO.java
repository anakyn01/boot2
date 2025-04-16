package com.app.car.dto;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CarLogCreateDTO {//DTO는 주로 요청 호은 응답
	
	@NonNull
	@Positive//양수만 허용 
	private Integer carId;
	
	@NonNull
	private String comment;
	private Integer page;
	
	
	

}
