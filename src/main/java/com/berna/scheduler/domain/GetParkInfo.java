package com.berna.scheduler.domain;

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

    @JsonProperty("list_total_count")
    private int listTotalCount;
    @JsonProperty("RESULT")
    private CodeMessageInfo result;
    @JsonProperty("row")
    private List<ParkingLotInfo> row;
}
