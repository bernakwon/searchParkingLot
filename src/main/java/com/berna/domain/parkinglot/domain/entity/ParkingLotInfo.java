package com.berna.domain.parkinglot.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name="PARKING_LOT_INFO")
@Entity
@Data
@IdClass(ParkingLotInfoPK.class)
public class ParkingLotInfo {


    @Id
    @Column(name = "PARKING_CODE") // 주차장코드
    @JsonProperty("PARKING_CODE")
    private String parkingCode;

    @Column(name = "PARKING_NAME") // 주차장명
    @JsonProperty("PARKING_NAME")
    private String parkingName;

    @Column(name = "PARKING_TYPE") // 주차장 종류
    @JsonProperty("PARKING_TYPE")
    private String parkingType;

    @Column(name = "OPERATION_RULE_NM") // 운영구분명
    @JsonProperty("OPERATION_RULE_NM")
    private String operationRuleNm;

    @Column(name = "BUS_ADD_RATES") // 버스 추가 단위 요금
    @JsonProperty("BUS_ADD_RATES")
    private String busAddRates;

    @Column(name = "PARKING_TYPE_NM") // 주차장 종류명
    @JsonProperty("PARKING_TYPE_NM")
    private String parkingTypeNm;

    @Column(name = "QUE_STATUS_NM") // 주차현황 정보 제공여부명
    @JsonProperty("QUE_STATUS_NM")
    private String queStatusNm;

    @Column(name = "CAPACITY") // 주차 면(주차 가능 차량 수)
    @JsonProperty("CAPACITY")
    private String capacity;

    @Column(name = "WEEKEND_END_TIME") // 주말 운영 종료시각(HHMM)
    @JsonProperty("WEEKEND_END_TIME")
    private String weekendEndTime;

    @Column(name = "CUR_PARKING_TIME") // 현재 주차 차량 업데이트 시간
    @JsonProperty("CUR_PARKING_TIME")
    private String curParkingTime;

    @Column(name = "PAY_NM") // 유무료구분명
    @JsonProperty("PAY_NM")
    private String payNm;

    @Column(name = "WEEKDAY_END_TIME") // 평일 운영 종료시각(HHMM)
    @JsonProperty("WEEKDAY_END_TIME")
    private String weekdayEndTime;

    @Column(name = "ASSIGN_CODE_NM") // 배정코드명
    @JsonProperty("ASSIGN_CODE_NM")
    private String assignCodeNm;
    @Id
    @Column(name = "QUE_STATUS") // 주차현황 정보 제공여부
    @JsonProperty("QUE_STATUS")
    private String queStatus;

    @Column(name = "ASSIGN_CODE") // 배정코드
    @JsonProperty("ASSIGN_CODE")
    private String assignCode;

    @Column(name = " ADD_RATES") // 추가 단위 요금
    @JsonProperty("ADD_RATES")
    private String addRates;

    @Column(name = "ADD_TIME_RATE") // 추가 단위 시간(분 단위)
    @JsonProperty("ADD_TIME_RATE")
    private String addTimeRate;

    @Column(name = "TEL") // 전화번호
    @JsonProperty("TEL")
    private String tel;

    @Column(name = "PAY_YN") // 유무료구분
    @JsonProperty("PAY_YN")
    private String payYn;

    @Column(name = "SATURDAY_PAY_YN") // 토요일 유무료 구분
    @JsonProperty("SATURDAY_PAY_YN")
    private String saturdayPayYn;

    @Column(name = "ADDR") // 주소
    @JsonProperty("ADDR")
    private String addr;

    @Column(name = "SATURDAY_PAY_NM") // 토요일 유무료 구분명
    @JsonProperty("SATURDAY_PAY_NM")
    private String saturdayPayNm;

    @Column(name = "DAY_MAXIMUM") // 일 최대 요금
    @JsonProperty("DAY_MAXIMUM")
    private String dayMaximum;

    @Column(name = "HOLIDAY_END_TIME") // 공휴일 운영 종료시각(HHMM)
    @JsonProperty("HOLIDAY_END_TIME")
    private String holidayEndTime;

    @Column(name = "HOLIDAY_PAY_NM") // 공휴일 유무료 구분명
    @JsonProperty("HOLIDAY_PAY_NM")
    private String holidayPayNm;

    @Column(name = "WEEKEND_BEGIN_TIME") // 주말 운영 시작시각(HHMM)
    @JsonProperty("WEEKEND_BEGIN_TIME")
    private String weekendBeginTime;
    @Id
    @Column(name = "LNG") // 주차장 위치 좌표 경도
    @JsonProperty("LNG")
    private String lng;

    @Column(name = "FULLTIME_MONTHLY") // 월 정기권 금액
    @JsonProperty("FULLTIME_MONTHLY")
    private String fulltimeMonthly;

    @Column(name = "CUR_PARKING") // 현재 주차중인 대수
    @JsonProperty("CUR_PARKING")
    private String curParking;
    @Id
    @Column(name = "LAT") // 주차장 위치 좌표 위도
    @JsonProperty("LAT")
    private String lat;

    @Column(name = "HOLIDAY_BEGIN_TIME") // 공휴일 운영 시작시각(HHMM)
    @JsonProperty("HOLIDAY_BEGIN_TIME")
    private String holidayBeginTime;

    @Column(name = "OPERATION_RULE") // 운영구분
    @JsonProperty("OPERATION_RULE")
    private String operationRule;

    @Column(name = "TIME_RATE") // 기본 주차 시간(분 단위)
    @JsonProperty("TIME_RATE")
    private String timeRate;

    @Column(name = "WEEKDAY_BEGIN_TIME") // 평일 운영 시작시각(HHMM)
    @JsonProperty("WEEKDAY_BEGIN_TIME")
    private String weekdayBeginTime;

    @Column(name = "RATES") // 기본 주차 요금
    @JsonProperty("RATES")
    private String rates;

    @Column(name = "NIGHT_FREE_OPEN_NM") // 야간무료개방여부명
    @JsonProperty("NIGHT_FREE_OPEN_NM")
    private String nightFreeOpenNm;

    @Column(name = "BUS_RATES") // 버스 기본 주차 요금
    @JsonProperty("BUS_RATES")
    private String busRates;

    @Column(name = "HOLIDAY_PAY_YN") // 공휴일 유무료 구분
    @JsonProperty("HOLIDAY_PAY_YN")
    private String holidayPayYn;

    @Column(name = "NIGHT_FREE_OPEN") // 야간무료개방여부
    @JsonProperty("NIGHT_FREE_OPEN")
    private String nightFreeOpen;

    @Column(name = "BUS_TIME_RATE") // 버스 기본 주차 시간(분 단위)
    @JsonProperty("BUS_TIME_RATE")
    private String busTimeRate;

    @Column(name = "GRP_PARKNM") // 노상 주차장 관리그룹번호
    @JsonProperty("GRP_PARKNM")
    private String grpParknm;

    @Column(name = "BUS_ADD_TIME_RATE") // 버스 추가 단위 시간(분 단위)
    @JsonProperty("BUS_ADD_TIME_RATE")
    private String busAddTimeRate;

    @Column(name = "SYNC_TIME") // 최종데이터 동기화 시간
    @JsonProperty("SYNC_TIME")
    private String syncTime;

    @CreationTimestamp
    @Column(name= "SAVE_TIME")
    private LocalDateTime saveTime;
}
