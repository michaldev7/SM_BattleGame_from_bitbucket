package com.michal.battleship.domain;

import com.michal.battleship.domain.type.Field;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class BattleBoard {
    private Map<String, Field> gameBoard;
}
