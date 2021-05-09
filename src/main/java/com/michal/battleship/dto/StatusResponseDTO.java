package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.michal.battleship.domain.type.GameStatusDisplay;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gameStatus",
        "yourScore",
        "opponentScore"
})
@AllArgsConstructor
@Data
public class StatusResponseDTO {

    @JsonProperty("gameStatus")
    private GameStatusDisplay gameStatus;
    @JsonProperty("yourScore")
    private Integer yourScore;
    @JsonProperty("opponentScore")
    private Integer opponentScore;
}