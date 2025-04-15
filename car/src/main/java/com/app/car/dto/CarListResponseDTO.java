package com.app.car.dto;

import lombok.Getter;

@Getter
public class CarListResponseDTO {
	
	private Integer carId;
	private String title;
	
	/* 생성자를 통해 객체를 생성하는 방법은 변하지 않는 객체를 생성할때 
	자주 사용하는 패턴입니다 이유가 맴버변수 private 로 선언되어 있고
	setter도 없기 때문에 객체를 생성할때 외에는 값을 바꿀수 없습니다
	*/
	public CarListResponseDTO(Integer carId, String title) {
		this.carId = carId;
		this.title = title;
	}
	
	
	
	
	
	
	
	

}
