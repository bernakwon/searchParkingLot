package com.berna.domain.parkinglot.domain.dto;

import com.berna.global.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * @author hrkwon
 * @className ParkingLotInfoListResponse
 *
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingLotInfo implements Comparable<ParkingLotInfo>{


    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장 코드",
            hidden = false
    )
    @JsonProperty("PARKING_CODE")
    private String parkingCode;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장명",
            hidden = false
    )
    @JsonProperty("PARKING_NAME")
    private String parkingName;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장 종류",
            hidden = false
    )
    @JsonProperty("PARKING_TYPE")
    private String parkingType;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "운영구분명",
            hidden = false
    )
    @JsonProperty("OPERATION_RULE_NM")
    private String operationRuleNm;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "버스 추가 단위 요금",
            hidden = false
    )
    @JsonProperty("BUS_ADD_RATES")
    private String busAddRates;



    @ApiModelProperty(
            example = "",
            required = true,
            value = " 주차장 종류명",
            hidden = false
    )
    @JsonProperty("PARKING_TYPE_NM")
    private String parkingTypeNm;

    @ApiModelProperty(
            example = "",
            required = true,
            value = " 주차현황 정보 제공여부명",
            hidden = false
    )
    @JsonProperty("QUE_STATUS_NM")
    private String queStatusNm;


    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차 면(주차 가능 차량 수)",
            hidden = false
    )
    @JsonProperty("CAPACITY")
    private int capacity;


    @ApiModelProperty(
            example = "",
            required = true,
            value = "주말 운영 종료시각(HHMM)",
            hidden = false
    )
    @JsonProperty("WEEKEND_END_TIME")
    private String weekendEndTime;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "현재 주차 차량 업데이트 시간",
            hidden = false
    )
    @JsonProperty("CUR_PARKING_TIME")
    private String curParkingTime;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "유무료 구분명",
            hidden = false
    )
    @JsonProperty("PAY_NM")
    private String payNm;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "평일 운영 종료시각(HHMM)",
            hidden = false
    )
    @JsonProperty("WEEKDAY_END_TIME")
    private String weekdayEndTime;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "배정코드명",
            hidden = false
    )
    @JsonProperty("ASSIGN_CODE_NM")
    private String assignCodeNm;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차현황 정보 제공여부",
            hidden = false
    )
    @JsonProperty("QUE_STATUS")
    private String queStatus;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "배정코드",
            hidden = false
    )
    @JsonProperty("ASSIGN_CODE")
    private String assignCode;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "추가 단위 요금",
            hidden = false
    )
    @JsonProperty("ADD_RATES")
    private String addRates;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "추가 단위 시간(분 단위)",
            hidden = false
    )
    @JsonProperty("ADD_TIME_RATE")
    private String addTimeRate;


    @ApiModelProperty(
            example = "",
            required = true,
            value = "전화번호",
            hidden = false
    )
    @JsonProperty("TEL")
    private String tel;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "유무료구분",
            hidden = false
    )
    @JsonProperty("PAY_YN")
    private String payYn;


    @ApiModelProperty(
            example = "",
            required = true,
            value = "토요일 유무료 구분",
            hidden = false
    )
    @JsonProperty("SATURDAY_PAY_YN")
    private String saturdayPayYn;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주소",
            hidden = false
    )
    @JsonProperty("ADDR")
    private String addr;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "토요일 유무료 구분명",
            hidden = false
    )
    @JsonProperty("saturdayPayNm")
    private String saturdayPayNm;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "일 최대 요금",
            hidden = false
    )
    @JsonProperty("DAY_MAXIMUM")
    private String dayMaximum;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "공휴일 운영 종료시각(HHMM)",
            hidden = false
    )
    @JsonProperty("HOLIDAY_END_TIME")
    private String holidayEndTime;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "공휴일 유무료 구분명",
            hidden = false
    )
    @JsonProperty("HOLIDAY_PAY_NM")
    private String holidayPayNm;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주말 운영 시작시각(HHMM)",
            hidden = false
    )
    @JsonProperty("WEEKEND_BEGIN_TIME")
    private String weekendBeginTime;


    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장 위치 좌표 경도",
            hidden = false
    )
    @JsonProperty("LNG")
    private double lng;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "월 정기권 금액",
            hidden = false
    )
    @JsonProperty("FULLTIME_MONTHLY")
    private String fulltimeMonthly;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "현재 주차중인 대수",
            hidden = false
    )
    @JsonProperty("CUR_PARKING")
    private int curParking;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장 위치 좌표 위도",
            hidden = false
    )
    @JsonProperty("LAT")
    private double lat;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "공휴일 운영 시작시각(HHMM)",
            hidden = false
    )
    @JsonProperty("HOLIDAY_BEGIN_TIME")
    private String holidayBeginTime;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "운영구분",
            hidden = false
    )
    @JsonProperty("OPERATION_RULE")
    private String operationRule;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "기본 주차 시간(분 단위)",
            hidden = false
    )
    @JsonProperty("TIME_RATE")
    private double timeRate;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "평일 운영 시작시각(HHMM)",
            hidden = false
    )
    @JsonProperty("WEEKDAY_BEGIN_TIME")
    private String weekdayBeginTime;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "기본 주차 요금",
            hidden = false
    )
    @JsonProperty("RATES")
    private double rates;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "야간무료개방여부명",
            hidden = false
    )
    @JsonProperty("NIGHT_FREE_OPEN_NM")
    private String nightFreeOpenNm;


    @ApiModelProperty(
            example = "",
            required = true,
            value =  "버스 기본 주차 요금",
            hidden = false
    )
    @JsonProperty("BUS_RATES")
    private String busRates;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "공휴일 유무료 구분",
            hidden = false
    )
    @JsonProperty("HOLIDAY_PAY_YN")
    private String holidayPayYn;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "야간무료개방여부",
            hidden = false
    )
    @JsonProperty("NIGHT_FREE_OPEN")
    private String nightFreeOpen;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "버스 기본 주차 시간(분 단위)",
            hidden = false
    )
    @JsonProperty("BUS_TIME_RATE")
    private String busTimeRate;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "노상 주차장 관리그룹번호",
            hidden = false
    )
    @JsonProperty("GRP_PARKNM")
    private String grpParknm;

    @ApiModelProperty(
            example = "",
            required = true,
            value =  "노상 주차장 관리그룹번호",
            hidden = false
    )
    // 버스 추가 단위 시간(분 단위)
    @JsonProperty("BUS_ADD_TIME_RATE")
    private String busAddTimeRate;

    private double ratePerMinutes;

    private String queStatusOrder;

    private String payYnOrder;

   //1분당 기본요금
   public double getRatePerMinutes(){
       if(this.getRates()==0){ //0을 나누면 NAN 발생
           return 0;
       }else {
           return this.getRates() / this.getTimeRate();
       }
    }

    //제공동의여부 순서 customize
    public String getQueStatusOrder(){
       String unLinkedData = "0";
        if(this.queStatus.equals(unLinkedData)){ // 미연계 데이터는 순서를 마지막으로 변경
            return "4";
        }else {
            return this.queStatus;
        }
    }

    public int getPayYnOrder(){
        String free = "N";
        if(this.payYn.equals(free)){
            return 1;
        }else {
            return 2;
        }
    }

/*기본 정렬은 연계된 데이터 >  무료 > 1분당 요금 낮은 순서*/
    @Override
    public int compareTo(ParkingLotInfo o) {

        return Comparator.comparing(ParkingLotInfo::getQueStatusOrder)
                .thenComparing(ParkingLotInfo::getPayYnOrder)
               .thenComparingDouble(ParkingLotInfo::getRatePerMinutes)
                .compare(this, o);


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
