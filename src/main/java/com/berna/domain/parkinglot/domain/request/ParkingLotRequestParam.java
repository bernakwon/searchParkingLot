package com.berna.domain.parkinglot.domain.request;


import lombok.Getter;
import org.springframework.data.domain.PageRequest;

@Getter
public class ParkingLotRequestParam {

    PageRequest pageRequest;

    String addr;
    String tel;
    String parkingName;


}
