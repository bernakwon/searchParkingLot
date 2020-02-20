package com.berna.domain.parkinglot.domain.response;


import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotInfoListResponse {
    long totalCount;
    List<ParkingLotInfo> parkingLotInfoList;
}
