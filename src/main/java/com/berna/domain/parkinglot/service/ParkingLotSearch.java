package com.berna.domain.parkinglot.service;

import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;

import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.domain.parkinglot.repository.ParkingLotInfoRepository;
import com.berna.global.common.util.CommonUtil;
import com.berna.scheduler.service.CacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class ParkingLotSearch {

    @Autowired
    ParkingLotInfoRepository parkingLotInfoRepository;

    @Autowired
    CacheService cacheService;

/*    public Page<ParkingLotInfo> searchDbDataByPredicate(ParkingLotRequestParam parkingLotRequestParam) {
        Pageable pageable = CommonUtil.toSpringPageable(parkingLotRequestParam);
     //   QParkingLotInfo parkingLotInfoForQuery = QParkingLotInfo.parkingLotInfo;
//        BooleanBuilder predicate = new BooleanBuilder();

        if (nonNull(parkingLotRequestParam.getAddr())) {
     //       predicate.and(parkingLotInfoForQuery.addr.like("%" + parkingLotRequestParam.getAddr() + "%"));
        }
        if (nonNull(parkingLotRequestParam.getTel())) {
     //       predicate.and(parkingLotInfoForQuery.tel.like("%" + parkingLotRequestParam.getTel() + "%"));
        }
        if (nonNull(parkingLotRequestParam.getParkingName())) {
      //      predicate.and(parkingLotInfoForQuery.parkingName.like("%" + parkingLotRequestParam.getParkingName() + "%"));
        }

        return parkingLotInfoRepository.findAll(predicate, pageable);
    }*/

    public ParkingLotInfoListResponse searchCacheDataByPredicate(ParkingLotRequestParam parkingLotRequestParam) {
        Pageable pageable = CommonUtil.toSpringPageable(parkingLotRequestParam);
        ParkingLotInfoListResponse aallParkingLotDataMap = cacheService.getParkingLotInfoOpenAPI();
        List<ParkingLotInfo> allParkingLotDataList = aallParkingLotDataMap.getParkingLotInfoList();
        long totalCount = aallParkingLotDataMap.getTotalCount();


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

        if (nonNull(parkingLotRequestParam)) {
            double myLat = parkingLotRequestParam.getMyLat();
            double myLng = parkingLotRequestParam.getMyLng();
//Todo sorting은 따로 test function 만들기
            if (nonNull(myLat) && nonNull(myLng)) {

                Comparator<ParkingLotInfo> distanceComparator = new Comparator<ParkingLotInfo>() {
                    @Override
                    public int compare(ParkingLotInfo o1, ParkingLotInfo o2) {
                        double o1Distance = CommonUtil.distance(myLat, o1.getLat(), myLng, o1.getLng());
                        double o2Distance = CommonUtil.distance(myLat, o2.getLat(), myLng, o2.getLng());
                        return Double.compare(o2Distance, o1Distance);
                    }

                };
                if (filterList.size()!=0){
                    filterList.sort(distanceComparator);
                }
            }
        }
        //페이징
        List<ParkingLotInfo> resultParkingLotDataList = new ArrayList<>();
        if (filterList.size()!=0){
            resultParkingLotDataList = filterList.subList(pageable.getPageNumber(), pageable.getPageSize());
        }


        return new ParkingLotInfoListResponse(totalCount,resultParkingLotDataList);
    }
}
