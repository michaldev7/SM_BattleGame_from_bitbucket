package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result",
        "shipType",
        "sunken"
})
public class HitDTO {

    @JsonProperty("result")
    private String result;
    @JsonProperty("sunken")
    private Boolean sunken;
    @JsonProperty("shipType")
    private String shipType;

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("sunken")
    public Boolean getSunken() {
        return sunken;
    }

    @JsonProperty("sunken")
    public void setSunken(Boolean sunken) {
        this.sunken = sunken;
    }

    @JsonProperty("shipType")
    public String getShipType() {
        return shipType;
    }

    @JsonProperty("shipType")
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
}
