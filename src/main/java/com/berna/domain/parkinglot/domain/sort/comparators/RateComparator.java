package com.berna.domain.parkinglot.domain.sort.comparators;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;

import java.util.Comparator;

public class RateComparator implements Comparator<ParkingLotInfo> {
    @Override
    public int compare(ParkingLotInfo p1, ParkingLotInfo p2) {
        if(p1.getRates()==0&&p2.getRates()==0){ //0을 나누면 NAN 발생
            return 1;
        }else {
            return Double.compare(p1.getRates() / p1.getTimeRate(),p2.getRates()/p2.getTimeRate());
        }

    }
}
