package com.app.car.controller;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;//
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;//
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;//

import com.app.car.dto.CarCreateDTO;
import com.app.car.dto.CarEditDTO;
import com.app.car.dto.CarEditResponseDTO;
import com.app.car.dto.CarReadResponseDTO;
import com.app.car.service.CarService;

@Controller
public class CarController {
	
	@Autowired
	private CarService carService;

	//쓰기
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
	
	//읽기
	@GetMapping("/car/read/{carId}")/*정보를 가져와서 주소로 들어오면 실행시키고 존재하지 않으면 에러페이지 422로 이동*/
	public ModelAndView read(@PathVariable Integer carId) {//주소로 요청이 들어올경우 read/숫자 스트링으로 실행
		//경로변수 (PathVariable) url에 있는 숫자값을 받아서 매핑 
		ModelAndView mav = new ModelAndView(); //스프링에서 데이터와 화면을 함께 담을수 있는 객체
		//데이터를 넣고 어떤 템플릿을 보여줄지 설정
		try {
			CarReadResponseDTO carReadResponseDTO = this.carService.read(carId);
			//호출해서 read정보를 가져옴
			mav.addObject("carReadResponseDTO",carReadResponseDTO);//결과를 DTO형태로 받음 model에 전달할 데이터 설정 /1, 2
			mav.setViewName("car/read");//url
		}catch(NoSuchElementException ex) {//에러가 생겼을때 이동하는 url이나 메세지
			//에러 메시지와 이동할 위치 설정
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("message","정보가 없습니다");
			mav.addObject("location", "/car");
			mav.setViewName("common/error/422");
			//http 200(ok), 400(bad request), 404(주소 경로틀림), 405(메소드 안만들고 실행)
		}
		return mav;	
	}
	
	//예외 핸들러 추가
	@ExceptionHandler(NoSuchElementException.class)
	//NoSuchElementException 는 Optional.get()에서 값이 없을때 발생
	//이걸 처리하지 않았을때는 서버에서 500 에러가 발생할수 있는데 사용자에게 친절한 메세지를 보여주기위해 이핸들러를 추가함
	public ModelAndView noSuchElementExceptionHandeler(NoSuchElementException ex) {
		ModelAndView mav = new ModelAndView(); //스프링에서 데이터와 화면을 함께 담을수 있는 객체
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message","정보가 없습니다");//에러 페이지에 사용할 메세지 전달
		mav.addObject("location","/car/list");//에러 페이지에서 이동할수 있는 위치를 전달
		mav.setViewName("common/error/422");//사용할 뷰 템플릿 경로지정
		return mav;
		//사용자의 요청자체는 유효하지만 서버에서 해당 리소스를 차지 못한 상황일때 사용
	}
	private ModelAndView error422(String message, String location) {
		ModelAndView mav = new ModelAndView(); //스프링에서 데이터와 화면을 함께 담을수 있는 객체
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message",message);//에러 페이지에 사용할 메세지 전달
		mav.addObject("location",location);//에러 페이지에서 이동할수 있는 위치를 전달
		mav.setViewName("common/error/422");//사용할 뷰 템플릿 경로지정
		return mav;
	}
	
	//수정 edit
	@GetMapping("/car/edit/{carId}") //url 경로에서 수정하는 순번에 대한 숫자 3
	public ModelAndView edit(@PathVariable Integer carId) throws NoSuchElementException{//해당값이 없을때 예외를 던짐
	ModelAndView mav = new ModelAndView();
	CarEditResponseDTO carEditResponseDTO = this.carService.edit(carId);
	//뷰에 전달할 데이터 설정
	mav.addObject("carEditResponseDTO",carEditResponseDTO);
	//렌더링할 뷰 이름을 지정
	mav.setViewName("car/edit");
	return mav;
	}
	
	@PostMapping("/car/edit/{carId}")
	public ModelAndView update(@Validated CarEditDTO carEditDTO, Errors errors) {
		//Errors errors 유호성 검사에서 발생한 에러정보를 담고 있는 객체
		//메소드 유효성을 검사하기위해 검사할 DTO객체에 @Validated어노테이션을 붙이고 오른쪽에 에러객체를 선언함
		if(errors.hasErrors()) {//오류가 있는지 확인하려면
			String errorMessage = errors
			.getFieldErrors()//오류가 난 항목의 목록을 가져옵니다
			.stream()//스트림으로 바꾸고
			.map(x -> x.getField()+" : "+x.getDefaultMessage())//필드명 오류 메세지 형태로 각항목을 적용하고
			.collect(Collectors.joining("\n"));//줄바꿈 문자로 합쳐줍니다
			
			return this.error422(
					errorMessage, String.format("/car/edit/%s", carEditDTO.getCarId()));
			
		}
		this.carService.update(carEditDTO); //정보를 수정하고 보기 페이지로 이동합니다
		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/car/read/%s", carEditDTO.getCarId()));
		return mav;
	}

	 
	
}
	
	
	
	
	
	
