package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@JsonPropertyOrder({
        "result",
        "shipType",
        "sunken"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HitResponseDTO {

    @JsonProperty("result")
    private String result;
    @JsonProperty("sunken")
    private Boolean sunken;
    @JsonProperty("shipType")
    private String shipType;
}
