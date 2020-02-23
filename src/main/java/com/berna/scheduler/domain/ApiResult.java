package com.berna.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult {
    @JsonProperty("GetParkInfo")
    private GetParkInfo getParkInfo;
}
