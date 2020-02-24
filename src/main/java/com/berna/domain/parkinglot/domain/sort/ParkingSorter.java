package com.berna.domain.parkinglot.domain.sort;

import java.util.List;

public interface ParkingSorter<T,S> {
    void sort(List<T> arr, S param);
}
