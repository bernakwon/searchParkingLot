package com.berna.domain.parkinglot.controller;

import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.domain.parkinglot.domain.response.ParkingLotInfoListResponse;
import com.berna.domain.parkinglot.service.ParkingLotSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value="주차장 정보 api" ,produces = "주차장 정보를 조회하는 api Controller")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParkingLotSearchController {
    @Autowired
    ParkingLotSearch parkingLotSearch;

    /**
     * @author hrkwon
     * @className searchCacheDataByApi
     *
     */
    @ApiOperation(value = "주차장 정보 list 조회 Api")
    @PostMapping("/parking/cache/search")
    public ParkingLotInfoListResponse searchCacheDataByApi(@RequestBody ParkingLotRequestParam parkingLotRequestParam){
        return parkingLotSearch.searchCacheDataByApi(parkingLotRequestParam);
    }
}
