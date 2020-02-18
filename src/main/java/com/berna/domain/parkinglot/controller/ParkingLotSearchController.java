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

@RestController
public class ParkingLotSearchController {
    @Autowired
    ParkingLotSearch parkingLotSearch;

    @PostMapping("/seoul/parking/search")
    public Page<ParkingLotInfo> searchAllData(@RequestBody ParkingLotRequestParam parkingLotRequestParam){
        return parkingLotSearch.searchAllDataByPredicate(parkingLotRequestParam);
    }
}
