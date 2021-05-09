package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "position"
})
@Data
public class HitRequestDTO {

    @JsonProperty("position")
    @Pattern(regexp = "^(([A-z][0-9]+)|([0-9]+))$", message = "Position must match of format: one letter, then numbers")
    @Size(min = 2, max = 3, message = "Please match position format as minimum 2 and maximum 3 characters")
    private String position;

    @JsonIgnore
    private long id;
}