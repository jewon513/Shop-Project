## Spring MVC Security Project

## 2020-04-08
- Spring MVC 기반 Security 구현
- Security 기능을 포함한 WebSocket 구현
- React 기반 Front-End 기능 Conding
- JDBC, Mybatis 연동 
- MySQL DB를 사용
- 회원가입 E-mail 인증
- 비밀번호 분실 E-mail 인증 후 재 설정

#### Spring security dependency
- spring-security-core
- spring-security-web
- spring-security-config
- spring-security-taglibs

- jasypt    
- jasypt-spring31		

#### DB dependency(MySQL, Mybatis 연동)

- mysql-connector-java    
MySQL과 Java를 연결해주는데 사용하는 DB Driver

- commons-dbcp2    
JDBC와 Driver 사이에서 DB Connection Pool을 만들고, Connection, DisConnection 을 수행해주는 보조 Class

- spring-jdbc    
Java와 DB Driver 사이에서 명령어, 데이터를 변환 시켜주는 보조 Class

- mybatis    
마이바티스는 개발자가 지정한 SQL, 저장프로시저 그리고 몇가지 고급 매핑을 지원하는 퍼시스턴스 프레임워크이다. 마이바티스는 JDBC로 처리하는 상당부분의 코드와 파라미터 설정및 결과 매핑을 대신해준다.

- mybatis-spring    
마이바티스 스프링 연동모듈은 마이바티스와 스프링을 편하고 간단하게 연동한다. 이 모듈은 마이바티스로 하여금 스프링 트랜잭션에 쉽게 연동되도록 처리한다. 게다가 마이바티스 매퍼와 SqlSession을 다루고 다른 빈에 주입시켜준다. 마이바티스 예외를 스프링의 DataAccessException로 변환하기도 하고 마이바티스, 스프링 또는 마이바티스 스프링 연동모듈에 의존성을 없애기도 한다.

#### Security와 관련된 용어

##### 접근주체(principal)
* 보호된 대상에 접근하는 유저

##### 인증(Authenticate)
* 접근하는 유저가 누구인가 확인(로그인 절차)

##### 인가(Authorize)
* 접근한 유저가 어떤 서비스, 어떤 페이지에 접근할 수 있는 권한이 있는가 하는 것을 검사하는 것

##### 권한(Role)
* 인증(Authenticate)된 주체(User)가 어떤 페이지, 기능, 서비스에 접근할 수 있는 권한이 있다는 것을 보증, 증명

##### 무결성
* 인가된 사용자에 의해 손상될 수 있는 것들

##### 보안
* 인가되지 않은 사용자에 의해 손상될 수 있는 것들

#### Spring security
##### 개념
* Filter를 사용하여 접근하는 사용자의 인증절차와 인가를 통해 권한이 있는가를 파악하고, 적절한 조치를 취할 수 있도록 비교적 적은 코드 양으로 처리할 수 있도록 만든 framework

##### 과정
1. Authentication Filter에서 부터 users table까지 접근하여 사용자 정보를 인증하는 절차를 거친다.
2. 인증이 되면 user table, user detail table에서 사용자의 정보를 fetch(select) 하여 session에 저장 
3. 일반적인 HttpSession은 서버의 활동 영역 메모리에 session을 저장하는데 비해 Spring security는 "SecurityContextHolder"라는 메모리에 저장 한다.
4. view로 유저 정보가 담긴 session을 session Id와 함께 응답으로 전달
5. JSESSION 이라는 쿠키에 session ID를 담아서 보내고
6. 이후 유저가 접근을 하면 JSESSION에서 쿠기를 추출하여 사용자 인증을 시도한다.
7. JSESSION에서 추출한 SESSION ID가 유효하면 접근 request에게 Authentication을 전달 한다. 








