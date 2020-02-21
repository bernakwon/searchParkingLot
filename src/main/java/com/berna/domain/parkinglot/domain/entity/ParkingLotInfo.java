package com.berna.domain.parkinglot.domain.entity;

import com.berna.global.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@ToString
public class ParkingLotInfo implements Comparable<ParkingLotInfo> {

    // 주차장코드
    @JsonProperty("PARKING_CODE")
    private String parkingCode;

    // 주차장명
    @JsonProperty("PARKING_NAME")
    private String parkingName;

    // 주차장 종류
    @JsonProperty("PARKING_TYPE")
    private String parkingType;

    // 운영구분명
    @JsonProperty("OPERATION_RULE_NM")
    private String operationRuleNm;

    // 버스 추가 단위 요금
    @JsonProperty("BUS_ADD_RATES")
    private String busAddRates;

    // 주차장 종류명
    @JsonProperty("PARKING_TYPE_NM")
    private String parkingTypeNm;

    // 주차현황 정보 제공여부명
    @JsonProperty("QUE_STATUS_NM")
    private String queStatusNm;

    // 주차 면(주차 가능 차량 수)
    @JsonProperty("CAPACITY")
    private int capacity;

    // 주말 운영 종료시각(HHMM)
    @JsonProperty("WEEKEND_END_TIME")
    private String weekendEndTime;

    // 현재 주차 차량 업데이트 시간
    @JsonProperty("CUR_PARKING_TIME")
    private String curParkingTime;

    // 유무료구분명
    @JsonProperty("PAY_NM")
    private String payNm;

    // 평일 운영 종료시각(HHMM)
    @JsonProperty("WEEKDAY_END_TIME")
    private String weekdayEndTime;

    // 배정코드명
    @JsonProperty("ASSIGN_CODE_NM")
    private String assignCodeNm;

    // 주차현황 정보 제공여부
    @JsonProperty("QUE_STATUS")
    private String queStatus;

    // 배정코드
    @JsonProperty("ASSIGN_CODE")
    private String assignCode;

    // 추가 단위 요금
    @JsonProperty("ADD_RATES")
    private String addRates;

    // 추가 단위 시간(분 단위)
    @JsonProperty("ADD_TIME_RATE")
    private String addTimeRate;

    // 전화번호
    @JsonProperty("TEL")
    private String tel;

    // 유무료구분
    @JsonProperty("PAY_YN")
    private String payYn;

    // 토요일 유무료 구분
    @JsonProperty("SATURDAY_PAY_YN")
    private String saturdayPayYn;

    // 주소
    @JsonProperty("ADDR")
    private String addr;

    // 토요일 유무료 구분명
    @JsonProperty("saturdayPayNm")
    private String saturdayPayNm;

    // 일 최대 요금
    @JsonProperty("DAY_MAXIMUM")
    private String dayMaximum;

    // 공휴일 운영 종료시각(HHMM)
    @JsonProperty("HOLIDAY_END_TIME")
    private String holidayEndTime;

    // 공휴일 유무료 구분명
    @JsonProperty("HOLIDAY_PAY_NM")
    private String holidayPayNm;

    // 주말 운영 시작시각(HHMM)
    @JsonProperty("WEEKEND_BEGIN_TIME")
    private String weekendBeginTime;
    @Id
    // 주차장 위치 좌표 경도
    @JsonProperty("LNG")
    private double lng;

    // 월 정기권 금액
    @JsonProperty("FULLTIME_MONTHLY")
    private String fulltimeMonthly;

    // 현재 주차중인 대수
    @JsonProperty("CUR_PARKING")
    private int curParking;

    // 주차장 위치 좌표 위도
    @JsonProperty("LAT")
    private double lat;

    // 공휴일 운영 시작시각(HHMM)
    @JsonProperty("HOLIDAY_BEGIN_TIME")
    private String holidayBeginTime;

    // 운영구분
    @JsonProperty("OPERATION_RULE")
    private String operationRule;

    // 기본 주차 시간(분 단위)
    @JsonProperty("TIME_RATE")
    private float timeRate;

    // 평일 운영 시작시각(HHMM)
    @JsonProperty("WEEKDAY_BEGIN_TIME")
    private String weekdayBeginTime;

    // 기본 주차 요금
    @JsonProperty("RATES")
    private float rates;

    // 야간무료개방여부명
    @JsonProperty("NIGHT_FREE_OPEN_NM")
    private String nightFreeOpenNm;

    // 버스 기본 주차 요금
    @JsonProperty("BUS_RATES")
    private String busRates;

    // 공휴일 유무료 구분
    @JsonProperty("HOLIDAY_PAY_YN")
    private String holidayPayYn;

    // 야간무료개방여부
    @JsonProperty("NIGHT_FREE_OPEN")
    private String nightFreeOpen;

    // 버스 기본 주차 시간(분 단위)
    @JsonProperty("BUS_TIME_RATE")
    private String busTimeRate;

    // 노상 주차장 관리그룹번호
    @JsonProperty("GRP_PARKNM")
    private String grpParknm;

    // 버스 추가 단위 시간(분 단위)
    @JsonProperty("BUS_ADD_TIME_RATE")
    private String busAddTimeRate;

    // 최종데이터 동기화 시간
    @JsonProperty("SYNC_TIME")
    private String syncTime;

    /*기본 정렬은 분 단위 당 에상요금 최소*/
    //TODO 추가 요금에 대한 것을 해결하고 가야함
    @Override
    public int compareTo(ParkingLotInfo o) {
        /*if (this.getQueStatus().equals(o.getQueStatus())) return 0;
        int queStatusCompare = o.getQueStatus().compareTo(this.getQueStatus());
        if (queStatusCompare != 0) return queStatusCompare;
*/
        int payYnCompare = 0;
        if (!this.getPayYn().equals(o.getPayYn()))
        { payYnCompare = this.getPayYn().compareTo(o.getPayYn());}

        if (payYnCompare != 0){ return payYnCompare;}
        else {
            return (int) (this.getRates() / this.getTimeRate() - o.getRates() / o.getTimeRate());
        }

    }

    /*현재 주차가능 여부 체크 */
    public boolean isCurrentParkingCheck() {
        //현재시간
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        int yyyy = currentDate.getYear();

        // 주중/주말/공휴일 운영시간 체크
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        boolean weekendCheck = dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
        boolean holidayCheck = DateUtil.isHolidayCheck(currentDate);
        boolean timeCheck = false;


        if (!weekendCheck && !holidayCheck) {
            LocalTime weekdayBeginTime = LocalTime.parse(this.weekdayBeginTime.substring(0, 2) + ":" + this.weekdayBeginTime.substring(2, 4));
            if (!this.weekdayEndTime.equals("2400")) {
                LocalTime weekdayEndTime = LocalTime.parse(this.weekdayEndTime.substring(0, 2) + ":" + this.weekdayEndTime.substring(2, 4));
                timeCheck = currentTime.isAfter(weekdayBeginTime) && currentTime.isBefore(weekdayEndTime);
            } else {
                timeCheck = currentTime.isAfter(weekdayBeginTime);
            }
        } else if (weekendCheck && !holidayCheck) {
            LocalTime weekendBeginTime = LocalTime.parse(this.weekendBeginTime.substring(0, 2) + ":" + this.weekendBeginTime.substring(2, 4));
            if (!this.weekendEndTime.equals("2400")) {
                LocalTime weekendEndTime = LocalTime.parse(this.weekendEndTime.substring(0, 2) + ":" + this.weekendEndTime.substring(2, 4));
                timeCheck = currentTime.isAfter(weekendBeginTime) && currentTime.isBefore(weekendEndTime);
            } else {
                timeCheck = currentTime.isAfter(weekendBeginTime);
            }
        } else {

            LocalTime holidayBeginTime = LocalTime.parse(this.holidayBeginTime.substring(0, 2) + ":" + this.holidayBeginTime.substring(2, 4));
            if (!this.holidayEndTime.equals("2400")) {
                LocalTime holidayEndTime = LocalTime.parse(this.holidayEndTime.substring(0, 2) + ":" + this.holidayEndTime.substring(2, 4));
                timeCheck = currentTime.isAfter(holidayBeginTime) && currentTime.isBefore(holidayEndTime);
            } else {
                timeCheck = currentTime.isAfter(holidayBeginTime);
            }
        }

        //미연계중이 아니고 주차가능 대수가 0 이상(현재주차중인 대수는 고려하지 않음), 현재 운영시간인지 여부
        return !this.queStatus.equals("0") && this.capacity > 0 && timeCheck;
    }
}
