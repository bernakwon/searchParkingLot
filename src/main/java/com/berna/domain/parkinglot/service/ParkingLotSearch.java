package com.berna.domain.parkinglot.service;

import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.repository.ParkingLotInfoRepository;
import com.querydsl.core.BooleanBuilder;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import static java.util.Objects.nonNull;

@Service
public class ParkingLotSearch {

    @Autowired
    ParkingLotInfoRepository parkingLotInfoRepository;
/**
 *
 *  구 or 동 //addr
 * 　　⊙ 전화번호 //tel
 *
 * 　　⊙ 주차장명//parkingName
 * */
    public Page<ParkingLotInfo> searchAllDataByPredicate(ParkingLotRequestParam parkingLotRequestParam){
        Pageable pageable = parkingLotRequestParam.getPageRequest();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

      /*  if(nonNull(parkingLotRequestParam.getAddr()){
            booleanBuilder.and()
        }*/
        return parkingLotInfoRepository.findAll(booleanBuilder,pageable);
    }
}
