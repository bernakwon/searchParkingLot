package com.berna.domain.parkinglot.domain.sort;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.request.ParkingLotRequestParam;
import com.berna.global.common.util.CommonUtil;
import org.springframework.util.comparator.BooleanComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;


/**
 * @author hrkwon
 * @Description 정렬기준에 따른 Comparator를 정의한 enum class
 */

public enum ParkingSorterType {

    DEFAULT("default", (arr, param) ->{
        arr.sort(null);
    }),
    DISTANCE("distance", (arr, param) -> {
        double myLat = param.getMyLat();
        double myLng = param.getMyLng();
        arr.sort((p1, p2) -> {
            double o1Distance = CommonUtil.distance(myLat, p1.getLat(), myLng, p1.getLng());
            double o2Distance = CommonUtil.distance(myLat, p2.getLat(), myLng, p2.getLng());
            return Double.compare(o1Distance, o2Distance);
        });
    }),
    CURRENT_PARKING_CHECK("currentParkingCheck", (arr, param) -> {
        arr.sort((p1, p2) -> Boolean.compare(p2.isCurrentParkingCheck(), p1.isCurrentParkingCheck()));
    });


    private final String description;
    private final ParkingSorter<ParkingLotInfo, ParkingLotRequestParam> sortFunc;

    ParkingSorterType(String description, ParkingSorter<ParkingLotInfo, ParkingLotRequestParam> sortFunc) {
        this.description = description;
        this.sortFunc = sortFunc;
    }

    public void sort(List<ParkingLotInfo> arr, ParkingLotRequestParam param) {
        sortFunc.sort(arr, param);
    }
    public static ParkingSorterType findSorter(String description)
    {
        return Arrays.stream(ParkingSorterType.values())
                .filter(type -> type.description != null)
                .filter(type -> description.contains(type.description))
                .findFirst()
                .orElse(ParkingSorterType.DEFAULT);
    }
}
