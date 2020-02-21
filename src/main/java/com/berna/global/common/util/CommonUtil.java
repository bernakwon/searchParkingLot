package com.berna.global.common.util;

import com.berna.global.common.object.PagingParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * <pre>
 * 메소드명	: com.berna.global.common.util
 * 작성자	: chode8703
 * 작성일	: 2020-02-19
 * 설명	:
 * </pre>
 *
 * @param
 * @return
 */
public class CommonUtil {
    public static Pageable toSpringPageable(PagingParam pagingParam) {
        PageRequest pageRequest = null;

        int pageNo   = pagingParam.getPage();
        int pageSize = pagingParam.getPageSize();



        if(pageNo >= 0 && pageSize > 0) {
            pageRequest = new PageRequest(pageNo, pageSize);

        }else {
            pageRequest = new PageRequest(0, 10);
        }

        return pageRequest;
    }

    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lng1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lng2 지점 2 경도
     * @return
     */
    public static double distance(double lat1, double lng1, double lat2, double lng2) {

        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;


        return (dist);
    }



    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
