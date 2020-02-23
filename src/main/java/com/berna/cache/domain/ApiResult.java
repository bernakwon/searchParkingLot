package com.berna.cache.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult {
    /*서비스 이름*/
    @JsonProperty("GetParkInfo")
    private GetParkInfo getParkInfo;
}
