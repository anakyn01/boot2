package com.app.car.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*흔히 말하는 댓글에 대한 DB
데이터베이스의 1:N 흔히들 One To Many라고 부르는 관계(relation)는
두개의 데이터베이스 테이블 데이터가 연결되는 방식
A테이블의 데이터행(row)하나에 B테이블의 데이터행이 여러개 존재할수있는 경우를
One To Many 일대다 관계라 합니다
=> 하나의 글에 댓글이 여러개 달리는 것
*/

@Entity
@Data
@Builder
@NoArgsConstructor//기본생성자(파라미터가 없는 생성자)
@AllArgsConstructor//모든 필드를 파라미터로 받는 생성자 서로 다른 상황에서 객체를 생성할수 있도록 하기 위해서
public class CarLog {//자식 테이블임을 명시
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carLogId;
	//관계형 데이터베이스에서 거의 대부분 테이블은 주 키(primary key)가 필요합니다
	
	@ManyToOne(fetch = FetchType.LAZY)//Many{자식테이블이}ToOne{부모테이블}
	@JoinColumn(name="car_id")//외래키 관계를 관리하기위해 @JoinColumn어노테이션을 사용합니다
	private Car car;
	
	@Column(columnDefinition ="TEXT")//데이터베이스에서 지정한 타입
	private String comment;
	
	private Integer page;
	
	@CreationTimestamp
	private LocalDateTime insertDateTime;
	
	
	/*fetch = FetchType.LAZY{지연로딩}  fetch = FetchType.EARLY{빠른 로딩}
	지연로딩 => 데이터를 사용할때 쿼리를 실행시키는 방식
	빠른로딩 => 일단 데이터가 호출되면 무조건 관련 테이블의 데이터도 모두 불러옵니다
	실무에서는 테이블의 관계가 우리가 학습하고 있는 예제처럼 단순하지 않다
	빠른 로딩을 하게 될경우 어플리케이션에서 사용하지 않는 수십개의 테이블 데이터를 불러오는 
	일이 생기기 때문에 주의해야 합니다
	
	RDBMS에서는 테이블간의 관계를 FK를 이용해서 관리합니다
	물론 왜래키를 이용하지 않더라도 테이블간 연결은 가능하지만 
	속도하고 명시적 관계가 보인다는 점에서 FK를 사용하는 것이 이득이다
	예를 들어 car 부모테이블의 PK car_id값을 car_log자식테이블의 컬럼으로 설정하는 방법
	
	JPA는 ORM이기 때문에 RDBMS와 사상을 공유하지 않는다
	- RDBMS는 테이블간의 관계를 왜래키로 나타내지만
	- ORM에서는 객체를 통해 나타냅니다 => private Car car
- RDBMS 자바객체를 표현하려면 private Integer carId; 실제로 마이바티스 같은 쿼리 빌더에서는 VO를 작성합니다

- RDBMS 문자열을 나타내는 방법이 여러개가 있다
char, varchar, 데이터베이스 벤더에 따라서는  nvarchar, nvarchar2, text, longtext, blob, clob
	
	
	
	
	
	
	
	
	*/
	
	
	
	
	
	
	

}
