package com.app.car.dto;

import java.time.LocalDateTime;

import com.app.car.entity.Car;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본 생성자를 자동으로 생성 이노테이션으로 인해 생성자가 명시적으로 
//작성되지 않아도 기본 생성자가 제공됩니다
@Getter//아래에 모든필드에 대해 getter 매서드를 자동으로 생성합니다
public class CarReadResponseDTO {
	private Integer carId;//필드
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	public CarReadResponseDTO fromCar(Car car) {
		//Car 객체를 받아서 CarReadResponseDTO객체의 필드를 채우는 역활을 합니다
		//Car 객체의 데이터를 CarReadResponseDTO객체로 변환하는 매서드 입니다
		this.carId = car.getCarId();
		this.title = car.getTitle();
		this.price = car.getPrice();
		this.insertDateTime = car.getInsertDateTime();
		//데이터를 추출하고 이를 CarReadResponseDTO필드에 설정합니다 
		return this;//현재 객체를 반환하여 매서드 체이닝을 가능하게 합니다
	}
	
	public static CarReadResponseDTO CarFactory(Car car) {
		CarReadResponseDTO carReadResponseDTO = new CarReadResponseDTO();
		carReadResponseDTO.fromCar(car);
		return carReadResponseDTO;
	}
	/*카 개체를 인수로 받아서 CarReadResponseDTO객체를 생성하는 팩토리 매서드 
	객체를 생성하고 fromCar(car)를 호출하여 car객체의 데이터를 CarReadResponseDTO객체로 변환
	변환된 객체를 리턴
	*/
	
	
	//car객체를 CarReadResponseDTO로 변환하는 역활을 하고  Car는 데이터를 API응답형식으로 변환하여 전달하는데 사용하며
	//fromcar매서드와 carFactory매서드를 통해 car객체를 DTO로 변환하는 두가지 방법을 제공
	
	

}
