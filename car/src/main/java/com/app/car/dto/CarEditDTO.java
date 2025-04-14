package com.app.car.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
//jdk버전이 1.2 1998년 이고 javaEE -> jakarta EE jdk11이후

import com.app.car.entity.Car;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CarEditDTO {
	
	@NonNull
	@Positive
	private Integer carId;
	//값을 양수로 제한합니다 => 1이상이어야 한다 RDBMS에서 1미만이면 데이터가 없는걸 의미

	@NonNull
	@NotBlank
	private String title;
	
	@NonNull
	@Min(1000)//1000이라는 숫자 이상이어야함
	private Integer price;
	
	public Car fill(Car car) {//클라이언트가 요청한 값으로 엔티티를 채우는 메소드 fill작성
//fill같은 메소드를 사용하면 서비스레이어에서 값을 채우는 논리가 커멘드객체로 이동되므로 
		//코드가 더 분산되는 효과가 있습니다		
		car.setTitle(this.title);
		car.setPrice(this.price);
		return car;
	}
	
	
	
	
	
	
	
	
	
}
