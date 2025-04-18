package com.app.car.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//해당 클래스가 JPA엔티티임을 명시 => 데이터베이스테이블과 매핑되어 
//객체관계 매핑(ORM)을 통해 데이터를 저장하거나 조회할수 있습니다
@Data//클래스에 대해 Getter, Setter, toString, equals, hashCode를 자동으로 생성
@Builder//빌더패턴을 사용하여 객체를 생성합니다
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	@Id//기본 키 임을 나타냅니다
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//데이터베이스가 기본 키값을 자동으로 증가시키는 방식(auto_increment)
	private Integer carId;
	
	@Column(length = 200)//최대 200자로 제한이 됩니다
	private String title;
	
	private Integer price;
	
	@CreationTimestamp//자동으로 현재시간을 설정합니다
	private LocalDateTime insertDateTime;
	
	
	
	
	
	
	
	
	

}
