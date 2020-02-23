package com.berna.domain.parkinglot.service;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;

import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.global.common.util.CommonUtil;
import com.berna.cache.service.CacheService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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
        if(parkingLotRequestParam.isRefreshCache()){
            refreshKey = LocalDateTime.now().toString();
        }
        ParkingLotInfoListResponse allParkingLotDataMap = cacheService.getParkingLotInfoOpenAPI(refreshKey);
        List<ParkingLotInfo> allParkingLotDataList = allParkingLotDataMap.getParkingLotInfoList();
        long totalCount = allParkingLotDataMap.getTotalCount();


        List<ParkingLotInfo> filterList = new ArrayList<>();

        filterList = allParkingLotDataList.stream().filter(parkingLotInfo -> {
            boolean searchAddr = true;
            boolean searchTel = true;
            boolean searchParkingName = true;
            if (nonNull(parkingLotRequestParam.getAddr())) {
                searchAddr = parkingLotInfo.getAddr().contains(parkingLotRequestParam.getAddr());
            }
            if (nonNull(parkingLotRequestParam.getTel())) {
                searchTel = parkingLotInfo.getTel().contains(parkingLotRequestParam.getTel());
            }
            if (nonNull(parkingLotRequestParam.getParkingName())) {
                searchParkingName = parkingLotInfo.getParkingName().contains(parkingLotRequestParam.getParkingName());
            }
            return searchAddr && searchTel && searchParkingName;
        }).collect(Collectors.toList());

        if(parkingLotRequestParam.isSearchNearCheck()){
        double myLat = parkingLotRequestParam.getMyLat();
        double myLng = parkingLotRequestParam.getMyLng();

            Comparator<ParkingLotInfo> distanceComparator = (o1, o2) -> {
                double o1Distance = CommonUtil.distance(myLat, o1.getLat(), myLng, o1.getLng());
                double o2Distance = CommonUtil.distance(myLat, o2.getLat(), myLng, o2.getLng());
                return Double.compare(o1Distance, o2Distance);
            };
            Collections.sort(filterList, distanceComparator);

        } else Collections.sort(filterList);


        //페이징
        List<ParkingLotInfo> resultParkingLotDataList;
        int startindex = parkingLotRequestParam.getStart();
        int endIndex = parkingLotRequestParam.getEnd();

        if (filterList.size() == 0) {
            startindex = filterList.size();
        }
        if (filterList.size() <= endIndex) {
            endIndex = filterList.size();
        }


        resultParkingLotDataList = filterList.subList(parkingLotRequestParam.getStart(), endIndex);

        return new ParkingLotInfoListResponse(filterList.size(), resultParkingLotDataList,refreshKey);
    }



    /**
     * searchText 하나만 받아 처리 3개의 조건중 하나만 성립해도 검색됨

    @ApiOperation(value = "주차장 정보 list 조회 Service 두번째", notes = "검색조건을 분리하지 않고 3개의 조건 중 부합하는 목록을 조회한다.")
    public ParkingLotInfoListResponse searchCacheDataByApi2(ParkingLotRequestParam parkingLotRequestParam) {

        ParkingLotInfoListResponse aallParkingLotDataMap = cacheService.getParkingLotInfoOpenAPI();
        List<ParkingLotInfo> allParkingLotDataList = aallParkingLotDataMap.getParkingLotInfoList();
        long totalCount = aallParkingLotDataMap.getTotalCount();


        List<ParkingLotInfo> filterList = new ArrayList<>();
        if (nonNull(parkingLotRequestParam.getSearchText())) {
            allParkingLotDataList = allParkingLotDataList.stream().filter(parkingLotInfo -> {

                boolean searchAddr = parkingLotInfo.getAddr().contains(parkingLotRequestParam.getSearchText());

                boolean searchTel = parkingLotInfo.getTel().contains(parkingLotRequestParam.getSearchText());

                boolean searchParkingName = parkingLotInfo.getParkingName().contains(parkingLotRequestParam.getSearchText());

                return searchAddr || searchTel || searchParkingName;


            }).collect(Collectors.toList());
        }

        double myLat = parkingLotRequestParam.getMyLat();
        double myLng = parkingLotRequestParam.getMyLng();

        if (myLat != 0.0 && myLng != 0.0) {

            Comparator<ParkingLotInfo> distanceComparator = new Comparator<ParkingLotInfo>() {
                @Override
                public int compare(ParkingLotInfo o1, ParkingLotInfo o2) {
                    double o1Distance = CommonUtil.distance(myLat, o1.getLat(), myLng, o1.getLng());
                    double o2Distance = CommonUtil.distance(myLat, o2.getLat(), myLng, o2.getLng());
                    return Double.compare(o1Distance, o2Distance);
                }

            };
            Collections.sort(filterList, distanceComparator);

        } else Collections.sort(filterList);

        //페이징
        List<ParkingLotInfo> resultParkingLotDataList = new ArrayList<>();

        int endIndex = parkingLotRequestParam.getEnd();
        if (filterList.size() <= endIndex) {
            endIndex = filterList.size();
        }


        resultParkingLotDataList = filterList.subList(parkingLotRequestParam.getStart(), endIndex);

        return new ParkingLotInfoListResponse(totalCount, resultParkingLotDataList);
    }
     */
}
