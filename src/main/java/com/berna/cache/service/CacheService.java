package com.berna.cache.service;


import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.global.error.exception.APIErrorException;
import com.berna.cache.domain.ApiResult;
import com.berna.cache.domain.CodeMessageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

/**
 * @author hrkwon
 * @className CacheService
 */
@Slf4j
@Service
public class CacheService {

    private WebClient webClient;

    @Value("${open-api.base-url}")
    private String baseUrl;

    @Value("${open-api.key}")
    private String key;

    @Value("${open-api.type}")
    private String type;

    @Value("${open-api.service-name}")
    private String serviceName;

    private static final String SUCCESS_API_RESULT_CODE = "INFO-000";
	private static final int MAX_END_INDEX = 1000;

    public CacheService(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .build();
    }

    public CacheService() {

    }

    /**
     * @author hrkwon
     * @Description open api 를 통해 메모리 캐시에 수집.
     */

    @ApiOperation(value = "open api 를 통해 메모리 캐시에 수집하는 메서드")
    @Cacheable(cacheNames = "API_ALL_DATA" , key="#refreshDate")
    public ParkingLotInfoListResponse getParkingLotInfoOpenAPI(String refreshDate) {

        List<ParkingLotInfo> resultParkingLotInfo = new ArrayList<>();
        List<CodeMessageInfo> codeMessageInfos = new ArrayList<>();
        //get Total Count
        int listTotCnt = getTotalCount();

        IntStream.range(0,  Math.round(listTotCnt / MAX_END_INDEX)+1).forEach(idx -> {
            int startIndex = MAX_END_INDEX * idx + 1;
            int endIndex = MAX_END_INDEX * idx + MAX_END_INDEX;

            ApiResult result = apiCallByWebClient(startIndex, endIndex);

            if (nonNull(result)) {
                resultParkingLotInfo.addAll(result.getGetParkInfo().getRow());
            }
        });


        return new ParkingLotInfoListResponse(listTotCnt, resultParkingLotInfo ,refreshDate);
    }

    /**
     * @author hrkwon
     * @Description 시작 index와 종료 index에 따라 api를 호출하는 보조 method
     */
    private ApiResult apiCallByWebClient(int startIndex, int endIndex) {
        String apiUrl = baseUrl + key + type + serviceName + "/" + startIndex + "/" + endIndex;
        ApiResult result =  webClient.get().uri(apiUrl)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve().bodyToMono(ApiResult.class).block();

        // Error 처리
        if (nonNull(result)) {
            if(nonNull(result.getGetParkInfo())) {
                CodeMessageInfo errorCheckMessage = result.getGetParkInfo().getResult();
                if (!errorCheckMessage.getCode().equals(SUCCESS_API_RESULT_CODE)) {//처리 결과 성공이 아니면 APIErrorException 호출
                    log.debug(errorCheckMessage.getMessage());
                    throw new APIErrorException();
                }
            } else {
                throw new APIErrorException();
            }
        }else{
            throw new APIErrorException();
        }

        return result;
    }

    /**
     * @author hrkwon
     * @Description 전체 목록 수
     */
    private int getTotalCount() {
        ApiResult resultTotal = apiCallByWebClient(1, 1);

        return resultTotal.getGetParkInfo().getListTotalCount();
    }

}
