package com.michal.battleship.dto.internal;

import com.michal.battleship.domain.type.GameStatusDisplay;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusDTO {

    private GameStatusDisplay gameStatusDisplay;
    private int yourScore;
    private int opponentScore;
    private boolean statusFound;

    public StatusDTO(GameStatusDisplay gameStatusDisplay, int yourScore, int opponentScore) {
        this.statusFound = true;
        this.gameStatusDisplay = gameStatusDisplay;
        this.yourScore = yourScore;
        this.opponentScore = opponentScore;
    }

    public StatusDTO() {
    }
}
