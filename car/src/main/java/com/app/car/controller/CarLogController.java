package com.app.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.car.dto.CarLogCreateDTO;
import com.app.car.dto.CarLogCreateResponseDTO;
import com.app.car.service.CarLogService;


/* 콘트롤러 + 리스판스바디(리턴값을 JSON,XML등 응답본문을 바로 변환해서 클라이언트에게 전달
주로 REST API를 만들때 필요 Representational State Transfer API
메소드가 반환한 값을 컨버터를 거쳐서 바로 클라이언트에게 응답한다
Restful API
*/
@RestController 
@RequestMapping("/car-log")
public class CarLogController {
	
	private CarLogService carLogService;
	
	@Autowired //이메서드를 통해 CarLogService 객체 주입
	public void setCarLogService(CarLogService carLogService) {
		this.carLogService = carLogService;
	}
	
@PostMapping("/create")
public ResponseEntity<CarLogCreateResponseDTO> insert(@RequestBody CarLogCreateDTO carLogCreateDTO){
//클라이언트로부터 전송된 json데이터를 	CarLogCreateDTO 변환해 매핑
CarLogCreateResponseDTO carLogCreateResponseDTO = this.carLogService.insert(carLogCreateDTO);//서비스 계층을 호출
return ResponseEntity.ok(carLogCreateResponseDTO);//HTTP 200 ok응답
}
	
	
	
	
	
	
	

}
