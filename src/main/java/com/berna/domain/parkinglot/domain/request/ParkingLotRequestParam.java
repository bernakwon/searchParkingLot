package com.berna.domain.parkinglot.domain.request;



import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hrkwon
 * @className ParkingLotInfoListResponse
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ApiModelProperty(
            example = "default",
            required = true,
            value = "정렬 인자",
            hidden = false
    )
    String sortDescription;


    @ApiModelProperty(
            example = "false",
            required = true,
            value = "새로고침여부",
            hidden = false
    )
    boolean refreshCache;
    @ApiModelProperty(
            example = "",
            required = true,
            value = "새로고침 날짜",
            hidden = false
    )
    String refreshDate;

}
