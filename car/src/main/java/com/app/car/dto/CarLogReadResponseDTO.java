package com.app.car.dto;

import java.time.LocalDateTime;

import com.app.car.entity.CarLog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarLogReadResponseDTO {
	private Integer carLogId;
	private String comment;
	private Integer page;
	private LocalDateTime insertDateTime;
	private String displayComment;
	
	public CarLogReadResponseDTO fromCarLog(CarLog carLog) {
		this.carLogId = carLog.getCarLogId();
		this.comment = carLog.getComment();
		this.page = carLog.getPage();
		this.insertDateTime = carLog.getInsertDateTime();
this.displayComment = (this.page == null ? "":"(p." + String.valueOf(this.page)+".)") + this.comment;
//page에 값이 있는 경우 "p.[페이지번호]."형태로 앞에 붙여 가독성을 높임
		return this;
	}
	
	public static CarLogReadResponseDTO CarLogFactory(CarLog carLog) {//쉽게 DTO를 생성가능하게
		CarLogReadResponseDTO carLogReadResponseDTO  = new CarLogReadResponseDTO();
		carLogReadResponseDTO.fromCarLog(carLog);
		return carLogReadResponseDTO;
	}
	
	
	
	
	
	
	
	

}
