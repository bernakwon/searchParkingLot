package com.berna.scheduler.service;


import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.global.error.exception.APIErrorException;
import com.berna.scheduler.domain.ApiResult;
import com.berna.scheduler.domain.CodeMessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

/**
 * @author hrkwon
 * @className BatchService
 *
 */
@Slf4j
@Service
public class CacheService {

	private final WebClient webClient;

	@Value("${open-api.base-url}")
	private String baseUrl;

	@Value("${open-api.key}")
	private String key;

	@Value("${open-api.type}")
	private String type;

	@Value("${open-api.service-name}")
	private String serviceName;

	private static final String SUCCESS_API_RESULT_CODE = "INFO-000";

	public CacheService(WebClient.Builder webClient) {
		this.webClient = webClient.baseUrl(baseUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.build();
	}

	/**
	 * @author hrkwon
	 * @Description open api 를 통해 메모리 캐시에 수집하는 메서드.
	 *
	 */
	@Cacheable(value="API_ALL_DATA")
	public ParkingLotInfoListResponse getParkingLotInfoOpenAPI(){
		long listTotCnt = 0;
       List<ParkingLotInfo> resultParkingLotInfo = new ArrayList<>();
        // API Error 검사
        List<CodeMessageInfo> codeMessageInfos = new ArrayList<>();

        //get Total Count
		 ApiResult apiResult =  webClient.get().uri(baseUrl+key+type+serviceName+"/1/1")
				      .accept(MediaType.APPLICATION_JSON_UTF8)
				.retrieve().bodyToMono(ApiResult.class).block();


		if(nonNull(apiResult)) {
			codeMessageInfos.add(apiResult.getGetParkInfo().getResult()); //처리 결과 저장
			listTotCnt = apiResult.getGetParkInfo().getListTotalCount();

			IntStream.range(0, Math.round(listTotCnt / 1000)).forEach(idx -> {
				int startIndex = 1000 * idx + 1;
				int endIndex = 1000 * idx + 1000;
				String apiUrl = baseUrl + key + type + serviceName + "/" + startIndex + "/" + endIndex;
				ApiResult result = webClient.get().uri(apiUrl)
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.retrieve().bodyToMono(ApiResult.class).block();



				if (result != null) {
					codeMessageInfos.add(apiResult.getGetParkInfo().getResult());
					resultParkingLotInfo.addAll(result.getGetParkInfo().getRow());
				}
			});
		}

		// 처리 결과가 성공이 아닌 경우 APIErrorException 발생
		List<CodeMessageInfo> apiErrorList = codeMessageInfos.stream().filter(codeMessageInfo -> !codeMessageInfo.getCode().equals(SUCCESS_API_RESULT_CODE)).collect(Collectors.toList());

		if(apiErrorList.size()>0){
			String errorMessage = "";
			apiErrorList.forEach(apiError->{
				errorMessage.join(apiError.getCode());
			});
			throw new APIErrorException(errorMessage);
		}

		ParkingLotInfoListResponse parkingLotInfoListResponse = new ParkingLotInfoListResponse(listTotCnt,resultParkingLotInfo);
		return parkingLotInfoListResponse;
	}

}
