package com.berna.domain.parkinglot.domain.request;


import com.berna.global.common.object.PagingParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author hrkwon
 * @className ParkingLotInfoListResponse
 *
 */
@Getter
public class ParkingLotRequestParam {

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주소",
            hidden = false
    )
    String addr;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "전화번호",
            hidden = false
    )
    String tel;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "주차장명",
            hidden = false
    )
    String parkingName;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "검색 text",
            hidden = false
    )
    String searchText;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "현재 주차 가능 여부",
            hidden = false
    )
    boolean searchCurrentCheck;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "나의 위도",
            hidden = false
    )
    double myLat;

    @ApiModelProperty(
            example = "",
            required = true,
            value = "나의 경도",
            hidden = false
    )
    double myLng;

    @ApiModelProperty(
            example = "0",
            required = true,
            value = "시작  index",
            hidden = false
    )
    int start;

    @ApiModelProperty(
            example = "10",
            required = true,
            value = "종료 index",
            hidden = false
    )
    int end;

}
