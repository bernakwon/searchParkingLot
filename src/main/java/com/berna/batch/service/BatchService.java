package com.berna.batch.service;


import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.berna.batch.domain.ApiResult;
import com.berna.domain.parkinglot.repository.ParkingLotInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

/**
 * @author hrkwon
 * @className BatchService
 * 
 */
@Slf4j
@RestController
public class BatchService {

	private final WebClient webClient;

	@Value("${open-api.base-url}")
	private String baseUrl;

	@Value("${open-api.key}")
	private String key;

	@Value("${open-api.type}")
	private String type;

	@Value("${open-api.service-name}")
	private String serviceName;

	@Autowired
	ParkingLotInfoRepository parkingLotInfoRepository;

	public BatchService(WebClient.Builder webClient) {
		this.webClient = webClient.baseUrl(baseUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.build();
	}


	/**
	 * @author hrkwon
	 * @return List<ParkingLotInfo>
	 * @Description
	 * 
	 */
	@RequestMapping("/batch")
	public long apiDataSaveDataBase() {

		List<Set<ParkingLotInfo>> resultAllApiData = getParkingLotInfoOpenAPI();

		resultAllApiData.forEach(resultApiData-> parkingLotInfoRepository.saveAll(resultApiData));

		return parkingLotInfoRepository.count();
	}


	private List<Set<ParkingLotInfo>> getParkingLotInfoOpenAPI(){
		int listTotCnt = 0;
        List<Set<ParkingLotInfo>> resultParkingLotInfo = new ArrayList<>();
		 ApiResult apiResult =  webClient.get().uri(baseUrl+key+type+serviceName+"/1/2")
				      .accept(MediaType.APPLICATION_JSON_UTF8)
				.retrieve().bodyToMono(ApiResult.class).block();

		 listTotCnt =  apiResult.getGetParkInfo().getListTotalCount();

		 for(int idx=0;idx<Math.round(listTotCnt/1000);idx++){
			 int startIndex = 1000*idx+1;
			 int endIndex = 1000*idx+1000;

			 String apiUrl = baseUrl+key+type+serviceName+"/"+startIndex+"/"+endIndex;
			 ApiResult result =  webClient.get().uri(apiUrl)
					 .accept(MediaType.APPLICATION_JSON_UTF8)
					 .retrieve().bodyToMono(ApiResult.class).block();
			 resultParkingLotInfo.add(result.getGetParkInfo().getRow());

		 }

		return resultParkingLotInfo;
	}

}
