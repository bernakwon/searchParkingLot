package com.berna.domain.parkinglot.domain.request;


import com.berna.global.common.object.PagingParam;
import lombok.Getter;

@Getter
public class ParkingLotRequestParam extends PagingParam {

    String addr;
    String tel;
    String parkingName;
    double myLat;
    double myLng;

}
