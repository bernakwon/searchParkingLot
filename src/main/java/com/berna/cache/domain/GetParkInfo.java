package com.berna.cache.domain;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetParkInfo {

    /*전체 개수*/
    @JsonProperty("list_total_count")
    private int listTotalCount;

    /*Result Code*/
    @JsonProperty("RESULT")
    private CodeMessageInfo result;

    /*주차장 정보 Data*/
    @JsonProperty("row")
    private List<ParkingLotInfo> row;
}
