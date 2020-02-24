package com.berna.domain.parkinglot.service;

import com.berna.cache.service.CacheService;
import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.domain.parkinglot.domain.sort.comparators.DistanceComparator;
import com.berna.domain.parkinglot.domain.sort.sort.MergeSorter;
import com.berna.global.common.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Api(value = "주차장 정보 로직")
@Service
public class ParkingLotSearch {

    @Autowired
    CacheService cacheService;

    @ApiOperation(value = "주차장 정보 list 조회 Service", notes = "검색조건과 페이징 index를 가지고 캐시된 목록을 조회한다.")
    public ParkingLotInfoListResponse searchCacheDataByApi(ParkingLotRequestParam parkingLotRequestParam) {
        String refreshKey = parkingLotRequestParam.getRefreshDate();
        if(parkingLotRequestParam.isRefreshCache()) refreshKey = LocalDateTime.now().toString();

        ParkingLotInfoListResponse allParkingLotDataMap = cacheService.getParkingLotInfoOpenAPI(refreshKey);

        List<ParkingLotInfo> allParkingLotDataList = allParkingLotDataMap.getParkingLotInfoList();


        List<ParkingLotInfo> filterList =filterListBySearchParam(allParkingLotDataList,parkingLotRequestParam);
        long totalCount = filterList.size();

        if (parkingLotRequestParam.isSearchNearCheck()) {
            double myLat = parkingLotRequestParam.getMyLat();
            double myLng = parkingLotRequestParam.getMyLng();
            MergeSorter.sort(filterList,new DistanceComparator(myLat,myLng));
           }else Collections.sort(filterList);

        //페이징
        int startIndex = parkingLotRequestParam.getStart();
        int endIndex = parkingLotRequestParam.getEnd();

        List<ParkingLotInfo> resultParkingLotDataList = settingPagingList(filterList, startIndex, endIndex);

        return new ParkingLotInfoListResponse(totalCount, resultParkingLotDataList, refreshKey);
    }



    private List<ParkingLotInfo> settingPagingList(List<ParkingLotInfo> targetList, int startIndex, int endIndex) {
        if (targetList.size() == 0) startIndex = targetList.size();
        if (targetList.size() <= endIndex) endIndex = targetList.size();

        return targetList.subList(startIndex, endIndex);
    }

    private List<ParkingLotInfo> filterListBySearchParam(List<ParkingLotInfo> allParkingLotDataList, ParkingLotRequestParam searchParam) {
        return allParkingLotDataList.stream().filter(parkingLotInfo -> {
            boolean searchAddr = true;
            boolean searchTel = true;
            boolean searchParkingName = true;
            if (!("").equals(searchParam.getAddr())) {
                searchAddr = parkingLotInfo.getAddr().contains(searchParam.getAddr());
            }
            if (!("").equals(searchParam.getTel())) {
                searchTel = parkingLotInfo.getTel().contains(searchParam.getTel());
            }
            if (!("").equals(searchParam.getAddr())) {
                searchParkingName = parkingLotInfo.getParkingName().contains(searchParam.getParkingName());
            }
            return searchAddr && searchTel && searchParkingName;
        }).collect(Collectors.toList());
    }

}
