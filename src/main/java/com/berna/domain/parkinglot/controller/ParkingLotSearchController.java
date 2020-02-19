package com.berna.domain.parkinglot.controller;

import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.service.ParkingLotSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingLotSearchController {
    @Autowired
    ParkingLotSearch parkingLotSearch;

    @PostMapping("/parking/database/search")
    public Page<ParkingLotInfo> searchDbDataByPredicate(@RequestBody ParkingLotRequestParam parkingLotRequestParam){
        return parkingLotSearch.searchDbDataByPredicate(parkingLotRequestParam);
    }

    @PostMapping("/parking/cache/search")
    public List<ParkingLotInfo> searchCacheDataByPredicate(@RequestBody ParkingLotRequestParam parkingLotRequestParam){
        return parkingLotSearch.searchCacheDataByPredicate(parkingLotRequestParam);
    }
}
