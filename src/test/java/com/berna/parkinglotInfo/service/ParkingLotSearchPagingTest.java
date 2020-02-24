package com.berna.parkinglotInfo.service;

import com.berna.cache.service.CacheService;
import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.domain.parkinglot.service.ParkingLotSearch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.Silent.class)
public class ParkingLotSearchPagingTest {
    private static ParkingLotInfoListResponse API_DATA = new ParkingLotInfoListResponse();

    private static String REFRESH_DATE;

    private static int LIST_TOTAL_COUNT;

    @InjectMocks
    ParkingLotSearch parkingLotSearch;

    @Mock
    CacheService cacheService;

    @Before
    public void Mock_API_CALL() throws IOException {
        this.REFRESH_DATE = LocalDateTime.of(2020,2,22,10,00,00).toString();
        List<ParkingLotInfo> testList = new ArrayList<>();
        testList.add(new ParkingLotInfo().builder().addr("서초구 1").tel("").parkingName("1주차장").queStatus("3").payYn("Y").parkingCode("9").rates(1000).timeRate(5).lat(37.2525320).lng(123.3333).build());//200
        testList.add(new ParkingLotInfo().builder().addr("압구정로 2-3").tel("02-3333-3333").parkingName("공영주차장").queStatus("0").payYn("N").parkingCode("8").rates(0).timeRate(0).lat(37.02525).lng(123.3333).build());//0
        testList.add(new ParkingLotInfo().builder().addr("성북구 11-1").tel("02-3653-3543").parkingName("3주차장").queStatus("2").payYn("Y").parkingCode("7").rates(100).timeRate(5).lat(37.25250).lng(123.3333).build());//20
        testList.add(new ParkingLotInfo().builder().addr("성동구 43-1").tel("02-456-7777").parkingName("4주차장").queStatus("2").payYn("Y").parkingCode("6").rates(1000).timeRate(30).lat(37.25420).lng(126.99574629).build());//33.3
        testList.add(new ParkingLotInfo().builder().addr("노원구 222-22").tel("").parkingName("공영주차장2").queStatus("3").payYn("Y").parkingCode("5").rates(500).timeRate(5).lat(37.45222).lng(133.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("동작구 22-22").tel("").parkingName("강남주차장").queStatus("3").payYn("Y").parkingCode("4").rates(5000).timeRate(5).lat(37.334520).lng(200.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("은평구 1-1").tel("").parkingName("구로주차장").queStatus("2").payYn("N").parkingCode("1").rates(0).timeRate(0).lat(37.43440).lng(13.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("압구정동 11-1").tel("").parkingName("5주차장").queStatus("0").payYn("N").parkingCode("2").rates(0).timeRate(0).lat(37.54979909).lng(127.07326955).build());
        testList.add(new ParkingLotInfo().builder().addr("구로구 11-1").tel("").parkingName("노상주차장").queStatus("3").payYn("N").parkingCode("3").rates(0).timeRate(0).lat(38.0).lng(126.96232545).build());
        this.LIST_TOTAL_COUNT = 150;
        this.API_DATA = new ParkingLotInfoListResponse(150,testList,REFRESH_DATE);
    }


    @Test
    public void 일반_페이징_테스트(){
        //given
        when(cacheService.getParkingLotInfoOpenAPI(REFRESH_DATE)).thenReturn(API_DATA);
        ParkingLotRequestParam testParkingLotParam = ParkingLotRequestParam.builder().sortDescription("default").start(0).end(5).refreshCache(false).refreshDate(REFRESH_DATE).build();
        List<ParkingLotInfo> resultListParkingLotInfoList = API_DATA.getParkingLotInfoList().subList(0,5);
        Collections.sort(resultListParkingLotInfoList);
        ParkingLotInfoListResponse resultParkingLotInfoResponse = new ParkingLotInfoListResponse(LIST_TOTAL_COUNT,resultListParkingLotInfoList,REFRESH_DATE);

        //when
        ParkingLotInfoListResponse finalParkingLotInfoListResponse  = parkingLotSearch.searchCacheDataByApi(testParkingLotParam);

        //then
        assertEquals(resultParkingLotInfoResponse.getParkingLotInfoList().size(),finalParkingLotInfoListResponse.getParkingLotInfoList().size());


    }

    @Test
    public void 조건검색_테스트(){

       //given
        when(cacheService.getParkingLotInfoOpenAPI(REFRESH_DATE)).thenReturn(API_DATA);
        ParkingLotRequestParam testParkingLotParam =  ParkingLotRequestParam.builder().addr("압구정").tel("3").parkingName("공영").start(0).end(5).refreshCache(false).refreshDate(REFRESH_DATE).build();
        List<ParkingLotInfo> returnData = API_DATA.getParkingLotInfoList();
        List<ParkingLotInfo> resultListParkingLotInfoList = Collections.singletonList(returnData.get(1));
        ParkingLotInfoListResponse resultParkingLotInfoResponse = new ParkingLotInfoListResponse(LIST_TOTAL_COUNT,resultListParkingLotInfoList,REFRESH_DATE);

        //when
        ParkingLotInfoListResponse finalParkingLotInfoListResponse  = parkingLotSearch.searchCacheDataByApi(testParkingLotParam);

        //then
        assertEquals(resultParkingLotInfoResponse.getParkingLotInfoList().get(0).getParkingCode(),finalParkingLotInfoListResponse.getParkingLotInfoList().get(0).getParkingCode());

    }


}
