package com.app.car.service;

import org.springframework.stereotype.Service;

import com.app.car.dto.CarLogCreateDTO;
import com.app.car.dto.CarLogCreateResponseDTO;
import com.app.car.entity.Car;
import com.app.car.entity.CarLog;
import com.app.car.entity.CarLogRepository;
import com.app.car.entity.CarRepository;

@Service
public class CarLogService {
	//리포지터리 2개를 선언하고 생성자를 통해 의존성을 주입받음
	private CarRepository carRepository;
	private CarLogRepository carLogRepository;
	
	public CarLogService(CarRepository carRepository, CarLogRepository carLogRepository) {
		this.carRepository = carRepository;
		this.carLogRepository = carLogRepository;
	}//댓글을 위한 리포지토리 정보를 다루는 jpa를 통해 값을 불러와야 하기 때문에 필요
	
	public CarLogCreateResponseDTO insert(CarLogCreateDTO carLogCreateDTO) {
		//댓글 객체를 생성할때 사용
		Car car = this.carRepository.findById(carLogCreateDTO.getCarId()).orElseThrow(null);
		//댓글 객체를 생성
CarLog carLog = CarLog.builder().car(car).comment(carLogCreateDTO.getComment())
.page(carLogCreateDTO.getPage()).build();

carLog = this.carLogRepository.save(carLog);
return CarLogCreateResponseDTO.CarLogFactory(carLog);
	}
	
	
	
	
	
	
	
	

}
