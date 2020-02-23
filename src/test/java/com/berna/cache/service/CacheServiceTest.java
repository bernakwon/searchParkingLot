package com.berna.cache.service;


import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.cache.domain.ApiResult;
import com.berna.cache.domain.CodeMessageInfo;
import com.berna.cache.domain.GetParkInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CacheServiceTest {

    private static String api_name = "API_ALL_DATA";
    private static ApiResult API_DATA;
    List<ParkingLotInfo> testList;
    private static final String SUCCESS_API_RESULT_CODE = "INFO-000";

    @Mock
    private CacheService cacheService;

    @Before
    public void webClient_call_data(){

        this.testList = new ArrayList<>();
        testList.add(new ParkingLotInfo().builder().addr("서초구 1").tel("").parkingName("1주차장").queStatus("3").payYn("Y").parkingCode("9").rates(1000).timeRate(5).lat(37.2525320).lng(123.3333).build());//200
        testList.add(new ParkingLotInfo().builder().addr("압구정로 2-3").tel("02-3333-3333").parkingName("공영주차장").queStatus("0").payYn("N").parkingCode("8").rates(0).timeRate(0).lat(37.02525).lng(123.3333).build());//0
        testList.add(new ParkingLotInfo().builder().addr("성북구 11-1").tel("02-3653-3543").parkingName("3주차장").queStatus("2").payYn("Y").parkingCode("7").rates(100).timeRate(5).lat(37.25250).lng(123.3333).build());//20
        testList.add(new ParkingLotInfo().builder().addr("성동구 43-1").tel("02-456-7777").parkingName("4주차장").queStatus("2").payYn("Y").parkingCode("6").rates(1000).timeRate(30).lat(37.25420).lng(126.99574629).build());//33.3
        testList.add(new ParkingLotInfo().builder().addr("노원구 222-22").tel("").parkingName("공영주차장2").queStatus("3").payYn("Y").parkingCode("5").rates(500).timeRate(5).lat(37.45222).lng(133.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("동작구 22-22").tel("").parkingName("강남주차장").queStatus("3").payYn("Y").parkingCode("4").rates(5000).timeRate(5).lat(37.334520).lng(200.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("은평구 1-1").tel("").parkingName("구로주차장").queStatus("2").payYn("N").parkingCode("1").rates(0).timeRate(0).lat(37.43440).lng(13.3333).build());
        testList.add(new ParkingLotInfo().builder().addr("압구정동 11-1").tel("").parkingName("5주차장").queStatus("0").payYn("N").parkingCode("2").rates(0).timeRate(0).lat(37.54979909).lng(127.07326955).build());
        testList.add(new ParkingLotInfo().builder().addr("구로구 11-1").tel("").parkingName("노상주차장").queStatus("3").payYn("N").parkingCode("3").rates(0).timeRate(0).lat(38.0).lng(126.96232545).build());


        CodeMessageInfo codeMessageInfo = new CodeMessageInfo(SUCCESS_API_RESULT_CODE,"정상적으로 리턴되었습니다.");

        GetParkInfo resultAllGetParkInfo = new GetParkInfo(150,codeMessageInfo,testList);

        this.API_DATA = new ApiResult(resultAllGetParkInfo);
/*

        when(cacheService.callWebClient(1,1)).thenReturn(API_DATA);
        when(cacheService.callWebClient(1,1000)).thenReturn(API_DATA);*/

    }


    @Test
    public void 캐싱_성공() {
        String date1 = LocalDateTime.of(2020,2,22,10,00,00).toString();
        String date2 = LocalDateTime.of(2020,2,22,10,00,10).toString();
        ParkingLotRequestParam firstRefreshParam = ParkingLotRequestParam.builder().refreshCache(true).refreshDate(date1).build();

        ParkingLotRequestParam cacheRefreshParam = ParkingLotRequestParam.builder().refreshCache(true).refreshDate(date2).build();
        ParkingLotInfoListResponse get1 = new ParkingLotInfoListResponse(150,testList,date1);
        ParkingLotInfoListResponse get2 = new ParkingLotInfoListResponse(120,testList,date2);

        when(cacheService.getParkingLotInfoOpenAPI(date1)).thenReturn(get1, get2);

        // First invocation returns object returned by the method
        ParkingLotInfoListResponse first = cacheService.getParkingLotInfoOpenAPI(firstRefreshParam.getRefreshDate());
        ParkingLotRequestParam cacheRefreshNotParam = ParkingLotRequestParam.builder().refreshCache(false).refreshDate(first.getRefreshDate()).build();


        ParkingLotInfoListResponse result = cacheService.getParkingLotInfoOpenAPI(cacheRefreshNotParam.getRefreshDate());

        assertThat(result.getTotalCount(), is(get1.getTotalCount()));

        // Second invocation should return cached value, *not* second (as set up above)
        result = cacheService.getParkingLotInfoOpenAPI(cacheRefreshNotParam.getRefreshDate());
        assertThat(result.getTotalCount(), is(get1.getTotalCount()));


        // Second invocation should return cached value, *not* second (as set up above)
        result = cacheService.getParkingLotInfoOpenAPI(cacheRefreshParam.getRefreshDate());
        assertThat(result.getTotalCount(), is(get2.getTotalCount()));

        // Verify repository method was invoked once
        verify(result, Mockito.times(1)).getParkingLotInfoList();

    }


 }