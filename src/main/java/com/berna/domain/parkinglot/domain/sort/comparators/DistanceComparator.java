package com.berna.domain.parkinglot.domain.sort.comparators;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.global.common.util.CommonUtil;

import java.util.Comparator;

public class DistanceComparator implements Comparator<ParkingLotInfo>{
    double myLat;
    double myLng;

    public DistanceComparator(double myLat, double myLng) {
        this.myLat =myLat;
        this.myLng= myLng;
    }

    @Override
    public int compare(ParkingLotInfo p1, ParkingLotInfo p2) {

            double o1Distance = CommonUtil.distance(myLat, p1.getLat(), myLng, p1.getLng());
            double o2Distance = CommonUtil.distance(myLat, p2.getLat(), myLng, p2.getLng());
            return Double.compare(o1Distance, o2Distance);
    }

}
