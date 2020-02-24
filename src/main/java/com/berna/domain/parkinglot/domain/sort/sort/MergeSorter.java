package com.berna.domain.parkinglot.domain.sort.sort;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.berna.domain.parkinglot.domain.sort.comparators.DistanceComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author hrkwon
 * @className MergeSorter
 * @description Merge Sort를 위한 class
 *
 */

public class MergeSorter {

    public static List<ParkingLotInfo> sort(List<ParkingLotInfo> targetList,  Comparator<? super ParkingLotInfo> comp) {
       return mergeSort(targetList, comp);
    }


    public static List<ParkingLotInfo> mergeSort(List<ParkingLotInfo> targetList, Comparator<? super ParkingLotInfo> comp) {

        if (targetList.size() > 1) {

            return mergeAll(

                    mergeSort(targetList.subList(0, targetList.size() / 2),comp),
                    mergeSort(targetList.subList(targetList.size() / 2, targetList.size()),comp)
                    , comp
            );
        } else {

            return targetList;
        }
    }


    public  static List<ParkingLotInfo> mergeAll(List<ParkingLotInfo> leftList, List<ParkingLotInfo> rightList, Comparator<? super ParkingLotInfo> comp) {

        List<ParkingLotInfo> result = new ArrayList<ParkingLotInfo>();
        int rightIdx = 0;

        // 왼쪽 배열 순환
       for (ParkingLotInfo l : leftList) {
          int r = comp.compare(l,rightList.get(rightIdx));
            // right 끝까지 순회 && comp로 비교시 right가 작다

            while (rightList.size() > rightIdx) {
                if (comp.compare(l, rightList.get(rightIdx)) > 0){
                    // 작은 값을 결과에 넣습니다.
                    result.add(rightList.get(rightIdx));
                    rightIdx++;
                  }

            }
           result.add(l);

        }
        // 오른쪽 배열 나머지 결과
        for (int i = rightIdx; i < rightList.size(); i++) {
            result.add(rightList.get(i));
        }
        return result;
    }
}
