Search Parking Lot System in Seoul
programed By hrkwon 
==================================
## Spec
- Springboot
- Spring data Jpa
- lombok
- H2-database
- thymeleaf(View리턴에만 사용함)
- Vue.js
- webflux (WebClient로 OpenAPI 호출)
- JuitTest
--------------------------------------------
## build Method

unzip SearchParkingLot.zip 
IDE Open -> File -> Open -> SearchParkingLot

>자바만 설치되어 있다고 가정한다. JAVA 8 은 로컬에 설치되어 있어야 한다. eclipse 기준으로 설명한다. InteliJ에서는 별도로 lombok플러그인을 제공한다. 

#### gradle, lombok 설치

	1. gradle 
    - Help->Eclipse Markerplace에서 buildShip 검색 후 설치
    - 재시작 후 Help->Eclipse Markerplace 에서 Gradle IDE Pack 3.8.x+1.0.x+2.2.x 검색 후 설치
    - 재시작 후 소스빌드가 되지 않을 시, 소스 오른쪽 마우스를 클릭하여 configure->convert Gradle(STS) project 를 실행한다.
	2. lombok : 
    - gradle 빌드 후 lombok1.16.22.jar가 다운로드된 위치를 찾아서 더블클릭한다.이클립스실행파일에 설치해준다.
    - 이클립스를 재시작한다.
    
#### Encoding
    - project->properties->resource에서 text file encoding을 UTF-8로 변경한다.

#### Run
    - Run->Run As ->Spring Boot App
    - 포트 설정을 7070으로 해두었기때문에 localhost:7070으로 접속해야한다.
--------------------------------------------
## 요구사항과 개발전략

 ### 요구사항 - 주차 가능한 주차장 찾기
    1. 다음 타입의 검색어로 검색이 되어야 함
         ⊙ 구 or 동  / 전화번호  / 주차장명  
         
    2. 조회결과
    　　 ⊙ 모든 조회는 paging이 가능해야 함  
    　　 ⊙ 예상 요금이 최소인 주차장 순으로 조회  
    　　 ⊙ 현재 주차가능 여부 
    
    3. 테스트 코드 작성  
    
    4. api document 작성
　　 
-------------------------------------------

  #### DB
  
      서울시 공영주차장 정보(parking_lot_info)
     
  #### Open API

     서울열린데이터 광장의 ‘서울시 공영주차장 안내 정보'
     
     ⊙ http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-13122&srvType=S&serviceKind=1&currentPageNo=1
     
  #### 개발 전략
     Backend
     1. Spring Schduler를 이용하여 Open API Json 전체데이터를 호출 및 수집하여  `서울시 공영주차장 정보(parking_lot_info)` 에 Insert (Open API 전략과 동일하게 5분단위)
     2. POJO 사용 (Open API 명세가 정해져 있으므로 MAP 사용하지 않는다)
     3. 기본 JPARepository 가 제공하는 Paging 활용 
     
    Frontend
     1. Vue.js 
     2. Kakao Map 이용한 Map Component로 경위도 표시   
     ## Build
     Gradle Build 시 npm build 함께 동작하도록 플러그인 설정
     
---------------------------------------------------

   #### Solve

  
  	1. 요구사항1 :  검색조건 3가지 충족 ( 구/동, 전화번호, 주차장명)

  	2. 요구사항2 :  배치를 통해 수집한 데이터로 자유로운 페이징( 기존 API는 1000개 이상 페이징 불가 )
  	               Sorting 가능 ( 기존 API Sorting 불가 )
  	               현재 주차여부 표시

  	3. 요구사항3 :  하단 테스트 전략 참조

  	4. 요구사항4 :  Swagger로 api document 자동화
  	
  	5. 우대사항1 :  Kakao Map 을 사용하여 현재 위치에서 가까운 정보 조회 
  	
  	6. 우대사항2 :  Frontend를 vue-cli 로 구성, Gradle Build 시 함께 빌드, 단일 페이지로 구성
  	
  	7. 우대사항3 :  containerize - Frontend Dev Server와 Backend를 별도로 실행해도 동작가능하도록 구성
  	
  	8. 기타      :  에러처리 - @ExcepionHandler와 @ControllerAdvice를 이용하여 에러정보를 리턴
   
---------------------------------------------  

   #### 테스트 전략
    Backend
   
    1. 단위테스트 : serviceImpl단의 주요로직 검증
  	
  	2. 배치테스트 : API 호출 데이터 수집 로직 검증
  	
  	Frontend
       
     vue test
  

