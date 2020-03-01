# Spring Security Project

## 개요
* Spring security의 연습을 위한 프로젝트


## 환경
* spring 5.1.11
* mybatis 3.5.4
* oracle 11xe

## 기능
* AuthenticationSuccessHandler를 implements한 CustomLoginSuccessHandler class를 이용하여 로그인시 updateDate가 갱신되도록 구현
* UserDetailsService를 implements한 CustomUserDetailService class를 이용하여 디테일한 회원정보를 조회할 수 있도록 함
* User class를 상속받은 CustomUser class를 통해서 로그인 성공 시 디테일한 회원정보를 가져오고 UserDetail class로 형 변환 하도록 구현
* bCryptPasswordEncoder를 이용하여 DB에 평문의 PW가 아닌 암호화된 PW를 등록하도록 구현

## 보완
* 로그인 실패시 AccessDeniedHandler 를 구현하여 다양한 방법으로 오류를 Handling 하기
* 회원가입 시 server단에서의 유효성 검사를 추가할 필요가 있음
