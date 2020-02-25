Search Parking Lot System in Seoul
programed By hrkwon 
==================================
## Spec
- Springboot
- Spring Cache
- lombok
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

#### Front build 설정

node.js 가 설치된 경우 build.gradle의 node 설정을 true로 변경하거나 아래의 url에서 다운로드 설치
https://nodejs.org/dist/v12.16.1/node-v12.16.1-x64.msi 


#### Run
    - Run->Run As ->Spring Boot App
    - 포트 설정을 7070으로 해두었기때문에 localhost:7070으로 접속해야한다.
    - FrontEnd를 따로 실행하고 싶을때는 ./frontend 위치에서 npm run serve 명령어를 실행
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

  #### DTO
  
      서울시 공영주차장 정보(parking_lot_info)
     
  #### Open API

     서울열린데이터 광장의 ‘서울시 공영주차장 안내 정보'
     
     ⊙ http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-13122&srvType=S&serviceKind=1&currentPageNo=1
     
  #### 개발 전략
     Backend
     1. WebClient를 사용하여 API를 Call
     1. Spring cache를 이용하여 Open API Json 전체데이터를 호출 및 수집하여  `서울시 공영주차장 정보(parking_lot_info)` 목록으로 캐싱한다.
     2. POJO 사용 (Open API 명세가 정해져 있으므로 MAP 사용하지 않는다)
     3. List.subList 를 이용하여 페이징
     4. Comparator 를 이용한 정렬
     
    Frontend
     1. Vue.js 
     2. vue-ads-pagination 를 이용한 페이징
     3. navigator.geolocation를 이용한 경/위도 추출
     
     ## Build
     Gradle Build 시 npm build 함께 동작하도록 플러그인 설정
     
---------------------------------------------------

   #### Solve

  
  	1. 요구사항1 :  검색조건 3가지 충족 ( 구/동, 전화번호, 주차장명)

  	2. 요구사항2 :  캐시를 통해 수집한 데이터로 자유로운 페이징( 기존 API는 1000개 이상 페이징 불가 )
  	               Sorting 가능 ( 기존 API Sorting 불가 )
  	               현재 주차여부 표시

  	3. 요구사항3 :  하단 테스트 전략 참조

  	4. 요구사항4 :  Swagger로 api document 자동화
  	
  	5. 우대사항1 :  현재 위치에서 가까운 정보 조회 
  	
  	6. 우대사항2 :  Frontend를 vue-cli 로 구성, Gradle Build 시 함께 빌드, 단일 페이지로 구성
  	
  	7. 우대사항3 :  containerize - Frontend Dev Server와 Backend를 별도로 실행해도 동작가능하도록 구성 (vue.config.js의 devProxy 참조)
  	
  	8. 기타      :  에러처리 - @ExcepionHandler와 @ControllerAdvice를 이용하여 에러정보를 리턴
   
---------------------------------------------  

   #### 테스트 전략
   
    1. 단위테스트 : 검색조건과 페이징 조건에 따라 목록 리턴의 성공여부, 정렬조건에 따른 정렬 성공 여부
  	
  	2. Cache테스트 : API 호출 데이터 수집 로직 검증
  	
  	3. API 호출테스트 : API 호출 성공여부와 원하는 데이터로의 매핑이 성공적인지 여부
  	
 

  

