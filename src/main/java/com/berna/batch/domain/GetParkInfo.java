package com.berna.batch.domain;

import com.berna.domain.parkinglot.domain.entity.ParkingLotInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class GetParkInfo {

    @JsonProperty("list_total_count")
    private int listTotalCount;
    @JsonProperty("RESULT")
    private CodeMessageInfo result;
    @JsonProperty("row")
    private Set<ParkingLotInfo> row;
}
