package com.berna.parkinglotInfo.service;

import com.berna.cache.service.CacheService;
import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.domain.parkinglot.service.ParkingLotSearch;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ParkingLotSortTest {

    private static ParkingLotInfoListResponse API_DATA;
    private static String REFRESH_DATE;


    @InjectMocks
    ParkingLotSearch parkingLotSearch;

    @Mock
    CacheService cacheService;

    @Before
    public void Mock_API_CALL() throws JSONException {
        this.REFRESH_DATE = LocalDateTime.of(2020,2,22,10,00,00).toString();
        List<ParkingLotInfo> testList = new ArrayList<>();
        testList.add(new ParkingLotInfo().builder().queStatus("3").payYn("Y").parkingCode("9").rates(1000).timeRate(5).lat(37.2525320).lng(123.1333).build());//ratePerMinutes:200
        testList.add(new ParkingLotInfo().builder().queStatus("0").payYn("N").parkingCode("8").rates(0).timeRate(0).lat(37.02525).lng(123.3333).build());//ratePerMinutes:0
        testList.add(new ParkingLotInfo().builder().queStatus("2").payYn("Y").parkingCode("7").rates(100).timeRate(5).lat(37.25250).lng(123.3333).build());//ratePerMinutes:20  -> 연계된 데이터중 가장 저렴
        testList.add(new ParkingLotInfo().builder().queStatus("2").payYn("Y").parkingCode("6").rates(1000).timeRate(30).lat(37.25420).lng(126.99574629).build());//ratePerMinutes:33.3
        this.API_DATA = new ParkingLotInfoListResponse(150,testList,REFRESH_DATE);



    }


    @Test
    public void 요금이_가장_저렴한_순서대로_정렬(){


        //given
        when(cacheService.getParkingLotInfoOpenAPI(REFRESH_DATE)).thenReturn(API_DATA);
        ParkingLotRequestParam testParkingLotParam = ParkingLotRequestParam.builder().myLat(0.0).myLng(0.0).start(0).end(3).build(); //기본 comcparator


        //when
        ParkingLotInfoListResponse finalParkingLotInfoListResponse  = parkingLotSearch.searchCacheDataByApi(testParkingLotParam);


        //then : 첫번째 row의 주차장 코드를 비교 , 첫번째 row의 유무료구분이 무료인지 비교
        assertEquals("7",finalParkingLotInfoListResponse.getParkingLotInfoList().get(0).getParkingCode());

    }


    @Test
    public void 내_위치에서_가까운_순서대로_정렬(){

        //given
        when(cacheService.getParkingLotInfoOpenAPI(REFRESH_DATE)).thenReturn(API_DATA);
        ParkingLotRequestParam testParkingLotParam = ParkingLotRequestParam.builder().myLat(37.0).myLng(123.444).start(0).end(3).build();

        //when
        ParkingLotInfoListResponse finalParkingLotInfoListResponse  = parkingLotSearch.searchCacheDataByApi(testParkingLotParam);

        //then :
       assertEquals("6",finalParkingLotInfoListResponse.getParkingLotInfoList().get(0).getParkingCode());

    }

}
