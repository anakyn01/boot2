package com.app.car.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.app.car.dto.CarCreateDTO;
import com.app.car.dto.CarEditDTO;
import com.app.car.dto.CarEditResponseDTO;
import com.app.car.dto.CarListResponseDTO;
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
		Car car = this.carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("페이지를 찾을수 없으므니다"));
		CarReadResponseDTO carReadResponseDTO = new CarReadResponseDTO();
		carReadResponseDTO.fromCar(car);
		return carReadResponseDTO;
	}
	
	//edit
	public CarEditResponseDTO edit(Integer carId) throws NoSuchElementException{
Car car = this.carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("페이지를 찾을수 없으므니다"));
		return CarEditResponseDTO.CarFactory(car);
	}
	public void update(CarEditDTO carEditDTO) throws NoSuchElementException{
//1) 데이터베이스에서 저장된 정보를 가져옵니다
Car car = this.carRepository.findById(carEditDTO.getCarId()).orElseThrow(() -> new NoSuchElementException("페이지를 찾을수 없으므니다"));		
//2) 입력 커맨드 객체에서 필요한 필드를 추려내서 데이터 베이스에서 저장할 정보를 변경합니다
car = carEditDTO.fill(car);
//3) 실제 데이터를 데이터베이스에 저장합니다
this.carRepository.save(car);
//jpa에서는 입력,수정 일때도 save메소드를 사용합니다
	}
	
	//delete
	public void delete(Integer carId)throws NoSuchElementException{
		Car car = this.carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("페이지를 찾을수 없으므니다"));
		this.carRepository.delete(car);
	}
	
	//list 목록을 페이징처리 + 검색조건에 따라 조회한후에 DTO로 반환
	public List<CarListResponseDTO> carList(String title, Integer page){
		//매개변수 title은 제목 검색을 위해서 page는 현재페이지를 나타내기위해 선언됨
		final int pageSize = 10;//한페이지에서 10개 이상의 글이 나온다는걸 변경되지 않는 값이라는걸 명시 
		List<Car> cars;
		if(page == null) {
			//page객체는 null을 허용하는 Integer타입으로 선언후에 만약 페이지 변수가 null이라면 기본값을 0으로 설정한다
			page = 0;//페이지에 시작
		}else {
			page -= 1;//일반적으로 사용자들은 1페이지부터 시작한다고 가정하기 때문에 그차이만큼 빼줍니다
		}
		
		if(title == null) {//제목에 값이 없다면 페이징 정보만 있으면 된다
			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "insertDateTime");
			cars = this.carRepository.findAll(pageable).toList();
			//페이징과 정렬을 담당 page =0, pageSize = 10
		}else {//제목이 비어있지 않다면 제목으로 검색하고 그결과에 페이징 정보를 제공 역방향 desc
			Pageable pageable = PageRequest.of(page, pageSize);
			Sort sort = Sort.by(Order.desc("insertDateTime"));			
			pageable.getSort().and(sort);//데이터를 있는 갯수만큼
			cars = this.carRepository.findByTitleContains(title, pageable);
		}
		return cars.stream().map(car -> new CarListResponseDTO(car.getCarId(), car.getTitle()))
	  .collect(Collectors.toList());
				
	}
	
	
	
	

}
