## 백엔드 웹 기초 지식 강의 실습

### 설명
* 백엔드 개발에 필요한 웹 기초 지식(HTTP, REST API) 학습   
* 강의에서 다루는 Spring Boot, MySQL을 활용하여 간단한 웹서비스 실습, 클론 코딩 진행

   
### 구현 기능 목록
* REST API : HTTP Method, HTTP 상태 코드, REST 정의, Builder 패턴, Camel -> Snake case
* Exception : @ControllerAdvice, @ExceptionHandler -> GlobalExceptionHandler 처리 필수
* Validation : Bean Validation, Custom Annotation -> ConstraintValidator로 검증   
  Custom Annotation : @Pattern을 이용한 휴대폰 번호 정규식 대신 추후 재사용을 위해 어노테이션 생성
* memorydb : ArrayList, Interface를 활용하여 Spring Data JPA와 비슷하게 구현
* docker : docker 환경에서 mysql 실행
* jpa : memorydb -> jpa 변환, jpql, nativeQuery 사용
* simple-board : 게시판 설계, ERD 설계, Pagination, Converter(Entity -> Dto), CRUD 구현
* filter, intepceptor : 원본 데이터 확인을 위해 filter, 인증 및 인가를 위해 interceptor 사용
* update-simple-board : 반복되는 CRUD 개선을 위해 추상화(인터페이스, 추상 클래스로 변환) 실시

### 개발 도구
* Language : Java 17
* Framework : Spring Boot 3.3.1
* DBMS : MySQL 8
* DB Library : JPA
