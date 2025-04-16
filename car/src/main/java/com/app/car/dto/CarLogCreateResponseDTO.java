package com.app.car.dto;

import com.app.car.entity.CarLog;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CarLogCreateResponseDTO {

	private Integer carLogId;//필드선언
	private Integer carId;
	private String comment;
	private Integer page;
	
	// car로그 객체를 받아서 현재객체(this)에 값들을 설정
	//이메서드는 인스턴스 메서드라 객체가 먼저 생성되어야 호출할수 있다
	public CarLogCreateResponseDTO fromCarLog(CarLog carLog) {
		this.carLogId = carLog.getCarLogId();
		this.carId = carLog.getCar().getCarId();
		this.comment = carLog.getComment();
		this.page = carLog.getPage();
		return this;
	}
	
	//객체생성과 필드설정을 한번에 해주는 정적 매서드 (Static Factory Method)
	public static CarLogCreateResponseDTO CarLogFactory(CarLog carLog) {
CarLogCreateResponseDTO carLogCreateResponseDTO = new CarLogCreateResponseDTO();
carLogCreateResponseDTO.fromCarLog(carLog);
return carLogCreateResponseDTO;
	}
	
	
	
	
	
}
