package com.app.car.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CarCreateDTO {

	@NonNull
	private String title;
	
	@NonNull
	private Integer price;
}