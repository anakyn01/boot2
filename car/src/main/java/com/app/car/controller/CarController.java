package com.app.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.car.dto.CarCreateDTO;
import com.app.car.service.CarService;

@Controller
public class CarController {
	
	@Autowired
	private CarService carService;

	@GetMapping("/car/create")//url
	public String create() {
		return "car/create";
	}
	@PostMapping("/car/create")
	public String insert(CarCreateDTO carCreateDTO) {
		Integer carId = this.carService.insert(carCreateDTO);
		return String.format("redirect:/car/read/%s", carId);
		//car/read/1
	}
	
	
	
	
	
	
	
	
}
