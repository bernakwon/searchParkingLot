package com.berna.batch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResult {
    @JsonProperty("GetParkInfo")
    private GetParkInfo getParkInfo;
}
