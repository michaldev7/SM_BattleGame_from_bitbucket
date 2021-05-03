package com.michal.battleship.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.michal.battleship.domain.type.GameStatusType;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gameStatus",
        "yourScore",
        "opponentScore"
})
public class StatusDTO {

    @JsonProperty("gameStatus")
    private GameStatusType gameStatus;
    @JsonProperty("yourScore")
    private Integer yourScore;
    @JsonProperty("opponentScore")
    private Integer opponentScore;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap();

    @JsonProperty("gameStatus")
    public GameStatusType getGameStatus() {
        return gameStatus;
    }

    @JsonProperty("gameStatus")
    public void setGameStatus(GameStatusType gameStatus) {
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}