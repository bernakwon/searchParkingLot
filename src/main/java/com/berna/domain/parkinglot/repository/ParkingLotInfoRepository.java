package com.berna.domain.parkinglot.repository;


import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.entity.ParkingLotInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ParkingLotInfoRepository extends JpaRepository<ParkingLotInfo, ParkingLotInfoPK>, QuerydslPredicateExecutor<ParkingLotInfo> {

}
