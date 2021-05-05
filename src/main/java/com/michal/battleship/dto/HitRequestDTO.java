package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "position"
})
public class HitRequestDTO {

    @JsonProperty("position")
    @Pattern(regexp = "^(([A-z][0-9]+)|([0-9]+))$", message = "Position must match of format: one letter, then numbers")
    @Size(min = 2, max = 3, message = "Please match position format as minimum 2 and maximum 3 characters")
    private String position;

    @NotBlank
    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }
}