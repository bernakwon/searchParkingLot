package com.berna.domain.parkinglot.domain.response;


import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;




/**
 * @author hrkwon
 * @className ParkingLotInfoListResponse
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotInfoListResponse {
    @ApiModelProperty(
            example = "",
            required = true,
            value = "전체 리턴 개수",
            hidden = false
    )
    long totalCount;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장정보 Data",
            hidden = false
    )
    List<ParkingLotInfo> parkingLotInfoList;
}
