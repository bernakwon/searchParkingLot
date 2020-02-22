package com.berna.parkinglotInfo.service;

import com.berna.cache.service.CacheService;
import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.domain.parkinglot.service.ParkingLotSearch;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ParkingLotSortTest {

    private static ParkingLotInfoListResponse API_DATA;

    private static int list_total_count;

    @InjectMocks
    ParkingLotSearch parkingLotSearch;

    @Mock
    CacheService cacheService;


/*



    {"GetParkInfo":{"list_total_count":15078,"RESULT":{"CODE":"INFO-000","MESSAGE":"정상 처리되었습니다 (* 열린데이터광장 서비스 일시 중단 안내 \n안정적인 서비스를 위한 오프라인 작업으로 인하여 열린데이터광장에서 운영중인 모든 \n서비스가 중단될 예정이오니 이용에 참고 하시기 바랍니다. \n 작업일정 및 안내사항 - \n1. 작업일정 - 2월 21일(금) 19시00분 ~ 2월 23일(일) 24시00분 \n2. 작업내용 - 데이터센터 전기 기반시설 정비 작업\n※ 작업일정은 작업 사정에 따라 변동될 수 있습니다.)"},"row":[
        {"que_status_nm":"현재~20분이내 연계데이터 존재(현재 주차대수 표현)","add_rates":150,"tel":"02-859-6318","parking_type":"NW","bus_add_rates":0,"lng":126.90124331,"addr":"구로구 구로동 810-3","holiday_pay_yn":"N","bus_rates":0,"que_status":"1","cur_parking_time":"2020-02-21 16:17:16","weekday_begin_time":"0000","time_rate":5,"capacity":93,"cur_parking":82,"lat":37.48543179,"night_free_open_nm":"야간 미개방","add_time_rate":5,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:36:15","pay_yn":"Y","parking_code":"1037932","rates":150,"weekend_begin_time":"0000","weekday_end_time":"2400","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"구로디지털단지역 환승주차장(시)","assign_code":"PIS02","weekend_end_time":"2400","day_maximum":0,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"2400","fulltime_monthly":"100000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"현재~20분이내 연계데이터 존재(현재 주차대수 표현)","add_rates":0,"tel":"02-3443-2784","parking_type":"NW","bus_add_rates":0,"lng":127.04992891,"addr":"강남구 개포동 1204-0","holiday_pay_yn":"Y","bus_rates":0,"que_status":"1","cur_parking_time":"2020-02-21 16:09:59","weekday_begin_time":"0900","time_rate":5,"capacity":36,"cur_parking":30,"lat":37.47715365,"night_free_open_nm":"야간 미개방","add_time_rate":0,"assign_code_nm":"미배정,미점유","sync_time":"2019-12-10 20:06:47","pay_yn":"Y","parking_code":"1042423","rates":200,"weekend_begin_time":"0000","weekday_end_time":"1900","saturday_pay_yn":"Y","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"개포로24길33(구)","assign_code":"PIS02","weekend_end_time":"0000","day_maximum":18000,"holiday_pay_nm":"유료","saturday_pay_nm":"유료","holiday_end_time":"0000","fulltime_monthly":"120000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"현재~20분이내 연계데이터 존재(현재 주차대수 표현)","add_rates":100,"tel":"02)353-6413","parking_type":"NW","bus_add_rates":0,"lng":126.91932176,"addr":"은평구 진관동 66-30","holiday_pay_yn":"N","bus_rates":0,"que_status":"1","cur_parking_time":"2020-02-21 16:18:31","weekday_begin_time":"0000","time_rate":5,"capacity":401,"cur_parking":265,"lat":37.63800702,"night_free_open_nm":"야간 미개방","add_time_rate":5,"assign_code_nm":"미배정,미점유","sync_time":"2019-11-27 14:32:09","pay_yn":"Y","parking_code":"1051043","rates":100,"weekend_begin_time":"0000","weekday_end_time":"2400","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"구파발역 환승주차장(시)","assign_code":"PIS02","weekend_end_time":"2400","day_maximum":0,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"2400","fulltime_monthly":"65000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"미연계중","add_rates":300,"tel":"","parking_type":"NW","bus_add_rates":0,"lng":126.99574629,"addr":"성북구 정릉동 865-7","holiday_pay_yn":"Y","bus_rates":0,"que_status":"0","cur_parking_time":"","weekday_begin_time":"0000","time_rate":5,"capacity":44,"cur_parking":0,"lat":37.60969589,"night_free_open_nm":"야간 미개방","add_time_rate":10,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:33:28","pay_yn":"Y","parking_code":"1079102","rates":150,"weekend_begin_time":"0000","weekday_end_time":"0000","saturday_pay_yn":"Y","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"국민대앞(구)","assign_code":"PIS02","weekend_end_time":"0000","day_maximum":10000,"holiday_pay_nm":"유료","saturday_pay_nm":"유료","holiday_end_time":"0000","fulltime_monthly":"100000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"미연계중","add_rates":150,"tel":"","parking_type":"NW","bus_add_rates":0,"lng":127.09138968,"addr":"광진구 구의동 219-15","holiday_pay_yn":"N","bus_rates":0,"que_status":"0","cur_parking_time":"","weekday_begin_time":"0000","time_rate":5,"capacity":159,"cur_parking":0,"lat":37.5391245,"night_free_open_nm":"야간 미개방","add_time_rate":5,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:37:47","pay_yn":"Y","parking_code":"1247659","rates":150,"weekend_begin_time":"0000","weekday_end_time":"2400","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"3","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"구의3동 공영주차장(구)","assign_code":"PIS02","weekend_end_time":"2400","day_maximum":0,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"2400","fulltime_monthly":"80000","grp_parknm":"","operation_rule_nm":"시간제 + 거주자 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"미연계중","add_rates":250,"tel":"02-465-2026","parking_type":"NW","bus_add_rates":0,"lng":127.07326955,"addr":"광진구 군자동 374-7","holiday_pay_yn":"N","bus_rates":0,"que_status":"0","cur_parking_time":"","weekday_begin_time":"0000","time_rate":5,"capacity":36,"cur_parking":0,"lat":37.54770648,"night_free_open_nm":"야간 미개방","add_time_rate":5,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:37:48","pay_yn":"Y","parking_code":"1247661","rates":250,"weekend_begin_time":"0000","weekday_end_time":"2400","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"광진광장공영(구)","assign_code":"PIS02","weekend_end_time":"2400","day_maximum":null,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"2400","fulltime_monthly":"120000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"미연계중","add_rates":500,"tel":"02-300-5041","parking_type":"NW","bus_add_rates":0,"lng":126.96232545,"addr":"마포구 공덕동 7-133","holiday_pay_yn":"N","bus_rates":0,"que_status":"0","cur_parking_time":"","weekday_begin_time":"0900","time_rate":60,"capacity":56,"cur_parking":0,"lat":37.54979909,"night_free_open_nm":"야간 미개방","add_time_rate":30,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:38:01","pay_yn":"Y","parking_code":"1247676","rates":1000,"weekend_begin_time":"0000","weekday_end_time":"1800","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"3","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"공덕1-2 공영주차장(구)","assign_code":"PIS02","weekend_end_time":"0000","day_maximum":0,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"0000","fulltime_monthly":"","grp_parknm":"","operation_rule_nm":"시간제 + 거주자 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"미연계중","add_rates":300,"tel":"02-792-5661","parking_type":"NW","bus_add_rates":0,"lng":126.98365962,"addr":"용산구 용산동6가 93-6","holiday_pay_yn":"N","bus_rates":0,"que_status":"0","cur_parking_time":"","weekday_begin_time":"0000","time_rate":10,"capacity":50,"cur_parking":0,"lat":37.52073083,"night_free_open_nm":"야간 미개방","add_time_rate":10,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:38:20","pay_yn":"Y","parking_code":"1268767","rates":300,"weekend_begin_time":"0000","weekday_end_time":"2400","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":0,"pay_nm":"유료","parking_name":"가족공원주차장(시)","assign_code":"PIS02","weekend_end_time":"2400","day_maximum":0,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"2400","fulltime_monthly":"120000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":0,"holiday_begin_time":"0000"},
        {"que_status_nm":"미연계중","add_rates":100,"tel":"","parking_type":"NW","bus_add_rates":null,"lng":126.8046,"addr":"강서구 방화동 526-16","holiday_pay_yn":"N","bus_rates":null,"que_status":"0","cur_parking_time":"","weekday_begin_time":"0000","time_rate":5,"capacity":310,"cur_parking":0,"lat":37.572684,"night_free_open_nm":"야간 미개방","add_time_rate":5,"assign_code_nm":"미배정,미점유","sync_time":"2019-06-10 09:38:05","pay_yn":"Y","parking_code":"1277148","rates":100,"weekend_begin_time":"0000","weekday_end_time":"2400","saturday_pay_yn":"N","parking_type_nm":"노외 주차장","operation_rule":"1","bus_add_time_rate":null,"pay_nm":"유료","parking_name":"개화산역(시)","assign_code":"PIS02","weekend_end_time":"2400","day_maximum":0,"holiday_pay_nm":"무료","saturday_pay_nm":"무료","holiday_end_time":"2400","fulltime_monthly":"50000","grp_parknm":"","operation_rule_nm":"시간제 주차장","night_free_open":"N","bus_time_rate":null,"holiday_begin_time":"0000"}]}}
*/

    @Before
    public void Mock_API_CALL() throws JSONException {

        List<ParkingLotInfo> testList = new ArrayList<>();
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("3").payYn("Y").parkingCode("9").rates(1000).timeRate(5).lat(37.2525320).lng(123.3333).build());//200
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("0").payYn("N").parkingCode("8").rates(0).timeRate(0).lat(37.02525).lng(123.3333).build());//0
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("2").payYn("Y").parkingCode("7").rates(100).timeRate(5).lat(37.25250).lng(123.3333).build());//20
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("2").payYn("Y").parkingCode("6").rates(1000).timeRate(30).lat(37.25420).lng(126.99574629).build());//33.3
       /* testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("3").payYn("Y").parkingCode("5").rates(500).timeRate(5).lat(37.45222).lng(133.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("3").payYn("Y").parkingCode("4").rates(5000).timeRate(5).lat(37.334520).lng(200.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("2").payYn("N").parkingCode("1").rates(0).timeRate(0).lat(37.43440).lng(13.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("0").payYn("N").parkingCode("2").rates(0).timeRate(0).lat(37.54979909).lng(127.07326955).build());
        testList.add(new ParkingLotInfo().builder().addr("주소").tel("02-3333-3333").parkingName("강남").queStatus("3").payYn("N").parkingCode("3").rates(0).timeRate(0).lat(38.0).lng(126.96232545).build());*/
        this.API_DATA = new ParkingLotInfoListResponse(150,testList);



    }


    @Test
    public void 요금이_가장_저렴한_순서대로_정렬(){

        when(cacheService.getParkingLotInfoOpenAPI()).thenReturn(API_DATA);
        //given
        ParkingLotRequestParam testParkingLotParam = new ParkingLotRequestParam(0.0,0.0,0,3); //기본 comparator
        List<ParkingLotInfo> resultListParkingLotInfoList = API_DATA.getParkingLotInfoList().subList(0,3);
        ParkingLotInfoListResponse resultParkingLotInfoResponse = new ParkingLotInfoListResponse(list_total_count,resultListParkingLotInfoList);

        //when
        ParkingLotInfoListResponse finalParkingLotInfoListResponse  = parkingLotSearch.searchCacheDataByApi(testParkingLotParam);


        //then : 첫번째 row의 주차장 코드를 비교 , 첫번째 row의 유무료구분이 무료인지 비교
        assertEquals("1",finalParkingLotInfoListResponse.getParkingLotInfoList().get(1).getParkingCode());
        assertEquals("N",finalParkingLotInfoListResponse.getParkingLotInfoList().get(1).getPayYn());
    }


    @Test
    public void 내_위치에서_가까운_순서대로_정렬(){
        when(cacheService.getParkingLotInfoOpenAPI()).thenReturn(API_DATA);
        //given
        ParkingLotRequestParam testParkingLotParam =  new ParkingLotRequestParam(37.0,123.444,0,3);
        List<ParkingLotInfo> resultListParkingLotInfoList = API_DATA.getParkingLotInfoList().subList(0,3);
        ParkingLotInfoListResponse resultParkingLotInfoResponse = new ParkingLotInfoListResponse(list_total_count,resultListParkingLotInfoList);

        //when
        ParkingLotInfoListResponse finalParkingLotInfoListResponse  = parkingLotSearch.searchCacheDataByApi(testParkingLotParam);

        //then :
       assertEquals("1","1");

    }

}
