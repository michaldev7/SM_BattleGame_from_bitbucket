package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.michal.battleship.domain.type.UserGameStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gameStatus",
        "yourScore",
        "opponentScore"
})
public class StatusDTO {

    @JsonProperty("gameStatus")
    private UserGameStatus gameStatus;
    @JsonProperty("yourScore")
    private Integer yourScore;
    @JsonProperty("opponentScore")
    private Integer opponentScore;

    public StatusDTO(int yourScore, int opponentScore, UserGameStatus gameStatus) {
        this.opponentScore = opponentScore;
        this.yourScore = yourScore;
        this.gameStatus = gameStatus;
    }

    @JsonProperty("gameStatus")
    public UserGameStatus getGameStatus() {
        return gameStatus;
    }

    @JsonProperty("gameStatus")
    public void setGameStatus(UserGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @JsonProperty("yourScore")
    public Integer getYourScore() {
        return yourScore;
    }

    @JsonProperty("yourScore")
    public void setYourScore(Integer yourScore) {
        this.yourScore = yourScore;
    }

    @JsonProperty("opponentScore")
    public Integer getOpponentScore() {
        return opponentScore;
    }

    @JsonProperty("opponentScore")
    public void setOpponentScore(Integer opponentScore) {
        this.opponentScore = opponentScore;
    }
}