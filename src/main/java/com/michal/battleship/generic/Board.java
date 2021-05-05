package com.michal.battleship.generic;

import com.michal.battleship.domain.type.Field;
import com.michal.battleship.domain.type.PlayerType;

import java.util.Map;

public abstract class Board {

    abstract Map<String, Field> drawBattleBoard(PlayerType type);
}
