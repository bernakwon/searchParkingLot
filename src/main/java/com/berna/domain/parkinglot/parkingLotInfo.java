package com.berna.domain.parkinglot;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class parkingLotInfo {


    @Id
    @Column(name = "PARKING_CODE") // 주차장코드
    private String parkingCode;

    @Column(name = "PARKING_NAME") // 주차장명
    private String parkingName;

    @Column(name = "PARKING_TYPE") // 주차장 종류
    private String parkingType;

    @Column(name = "OPERATION_RULE_NM") // 운영구분명
    private String operationRuleNm;

    @Column(name = "BUS_ADD_RATES") // 버스 추가 단위 요금
    private String busAddRates;

    @Column(name = "PARKING_TYPE_NM") // 주차장 종류명
    private String parkingTypeNm;

    @Column(name = "QUE_STATUS_NM") // 주차현황 정보 제공여부명
    private String queStatusNm;

    @Column(name = "CAPACITY") // 주차 면(주차 가능 차량 수)
    private String capacity;

    @Column(name = "WEEKEND_END_TIME") // 주말 운영 종료시각(HHMM)
    private String weekendEndTime;

    @Column(name = "CUR_PARKING_TIME") // 현재 주차 차량 업데이트 시간
    private String curParkingTime;

    @Column(name = "PAY_NM") // 유무료구분명
    private String payNm;

    @Column(name = "WEEKDAY_END_TIME") // 평일 운영 종료시각(HHMM)
    private String weekdayEndTime;

    @Column(name = "ASSIGN_CODE_NM") // 배정코드명
    private String assignCodeNm;

    @Column(name = "QUE_STATUS") // 주차현황 정보 제공여부
    private String queStatus;

    @Column(name = "ASSIGN_CODE") // 배정코드
    private String assignCode;

    @Column(name = " ADD_RATES") // 추가 단위 요금
    private String addRates;

    @Column(name = "ADD_TIME_RATE") // 추가 단위 시간(분 단위)
    private String addTimeRate;

    @Column(name = "TEL") // 전화번호
    private String tel;

    @Column(name = "PAY_YN") // 유무료구분
    private String payYn;

    @Column(name = "SATURDAY_PAY_YN") // 토요일 유무료 구분
    private String saturdayPayYn;

    @Column(name = "ADDR") // 주소
    private String addr;

    @Column(name = "SATURDAY_PAY_NM") // 토요일 유무료 구분명
    private String saturdayPayNm;

    @Column(name = "DAY_MAXIMUM") // 일 최대 요금
    private String dayMaximum;

    @Column(name = "HOLIDAY_END_TIME") // 공휴일 운영 종료시각(HHMM)
    private String holidayEndTime;

    @Column(name = "HOLIDAY_PAY_NM") // 공휴일 유무료 구분명
    private String holidayPayNm;

    @Column(name = "WEEKEND_BEGIN_TIME") // 주말 운영 시작시각(HHMM)
    private String weekendBeginTime;

    @Column(name = "LNG") // 주차장 위치 좌표 경도
    private String lng;

    @Column(name = "FULLTIME_MONTHLY") // 월 정기권 금액
    private String fulltimeMonthly;

    @Column(name = "CUR_PARKING") // 현재 주차중인 대수
    private String curParking;

    @Column(name = "LAT") // 주차장 위치 좌표 위도
    private String lat;

    @Column(name = "HOLIDAY_BEGIN_TIME") // 공휴일 운영 시작시각(HHMM)
    private String holidayBeginTime;

    @Column(name = "OPERATION_RULE") // 운영구분
    private String operationRule;

    @Column(name = "TIME_RATE") // 기본 주차 시간(분 단위)
    private String timeRate;

    @Column(name = "WEEKDAY_BEGIN_TIME") // 평일 운영 시작시각(HHMM)
    private String weekdayBeginTime;

    @Column(name = "RATES") // 기본 주차 요금
    private String rates;

    @Column(name = "NIGHT_FREE_OPEN_NM") // 야간무료개방여부명
    private String nightFreeOpenNm;

    @Column(name = "BUS_RATES") // 버스 기본 주차 요금
    private String busRates;

    @Column(name = "HOLIDAY_PAY_YN") // 공휴일 유무료 구분
    private String holidayPayYn;

    @Column(name = "NIGHT_FREE_OPEN") // 야간무료개방여부
    private String nightFreeOpen;

    @Column(name = "BUS_TIME_RATE") // 버스 기본 주차 시간(분 단위)
    private String busTimeRate;

    @Column(name = "GRP_PARKNM") // 노상 주차장 관리그룹번호
    private String grpParknm;

    @Column(name = "BUS_ADD_TIME_RATE") // 버스 추가 단위 시간(분 단위)
    private String busAddTimeRate;

    @Column(name = "SYNC_TIME") // 최종데이터 동기화 시간
    private String syncTime;
}
