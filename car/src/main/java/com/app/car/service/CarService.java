package com.app.car.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.app.car.dto.CarCreateDTO;
import com.app.car.dto.CarReadResponseDTO;
import com.app.car.entity.Car;
import com.app.car.entity.CarRepository;

@Service //서비스 계층을 나타냄 => 비즈니스 로직을 처리하는 역활을 한다는 것을 의미
public class CarService {
	
	private CarRepository carRepository;//정보를 데이터베이스에 저장하거나 조회하는 등의 작업을 담당
	
	public CarService(CarRepository carRepository) {
		//생성자 주입 (DI) 서비스가 생성될때 CarRepository 의존성 주입을 받는 구조입니다
		this.carRepository = carRepository;
		//서비스 클래스는 carRepository객체가 필요하고 그 객체는 외부에서 주입됩니다
		//스프링은 의존성 주입을 하여 객체간의 결합도를 낮추고 유연성을 높여줍니다
		
	}
	//가비지 컬렉터에 대한 낭비와 코드량을 줄이기 위해 빌더패턴 사용
	public Integer insert(CarCreateDTO carCreateDTO) {//데이터 베이스를 저장하는 역활을 합니다
		Car car = Car.builder().title(carCreateDTO.getTitle()).price(carCreateDTO.getPrice()).build();
		
		this.carRepository.save(car);
		return car.getCarId();//고유 식별자
	}
	
	//read
	public CarReadResponseDTO read(Integer carId) throws NoSuchElementException{
		Car car = this.carRepository.findById(carId).orElseThrow();
		CarReadResponseDTO carReadResponseDTO = new CarReadResponseDTO();
		carReadResponseDTO.fromCar(car);
		return carReadResponseDTO;
	}
	
	
	
	
	
	

}
