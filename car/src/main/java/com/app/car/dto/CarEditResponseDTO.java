package com.app.car.dto;

import java.time.LocalDateTime;

import com.app.car.entity.Car;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor//파라미터가 없는 기본 생성자를 LOMBOK이 생성
@Getter//모든 필드에 대해 getter메서드를 자동생성
public class CarEditResponseDTO {//Car객체에 데이터를 응답 형식으로 패킹하는 DTO
	
	private Integer carId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	public CarEditResponseDTO fromCar(Car car) {
//주어진 Car객체로 부터 현재 CarEditResponseDTO객체의 필드를 채움 fromCar현재 객체의 필드를 세팅하고 this를 리턴
		this.carId = car.getCarId();
		this.title = car.getTitle();
		this.price = car.getPrice();
		this.insertDateTime = car.getInsertDateTime();
		return this;
	}

	public static CarEditResponseDTO CarFactory(Car car) {
		CarEditResponseDTO carEditResponseDTO = new CarEditResponseDTO();
		carEditResponseDTO.fromCar(car);
		return carEditResponseDTO;
	}//car객체를 받아서 CarEditResponseDTO객체를 만들어 줍니다 fromCar로 값을 세팅하고 리턴시킴
		
	
	/*
	car엔티티 기반으로 일부 필드만 포함한 응답 객체를 만들어주는 역활을 하고
	Lombok을 통해 코드 간결성을 유지합니다 
	*/
	
	
	
	
	

}
