package com.berna.domain.parkinglot.domain.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class ParkingLotInfoPK implements Serializable {


    private static final long serialVersionUID = 7716436416925452401L;

    private String parkingCode;
    private String parkingName;
    private String operationRule;
    private String operationRuleNm;
    private String addr;
    private String queStatus;
    private String lng;
    private String lat;
    private String assignCode;
    private String assignCodeNm;
}
