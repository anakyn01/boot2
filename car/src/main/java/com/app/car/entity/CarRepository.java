package com.app.car.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
/*
JPA에서는 데이터베이스와 연동하는 방법으로 리포지토리인터페이스를 주로 사용합니다
*/
