package com.michal.battleship.domain;

import com.michal.battleship.domain.type.ShipType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/*
 * To hide implementation details I decided to write here details of the map with basic functions
 */
@Data
@AllArgsConstructor
public class BattleBoard {

    private Map<String, ShipType> gameBoard;

    public ShipType getContentOfPositionAt(String position) {
        return gameBoard.get(position);
    }

    /*
     * Returns true when ship is sunken.
     * Because if inside game map there is no more ships with given type/size, then this mean that
     * ship is destroyed at all / sunken.
     *
     * @return the true when ship is sunken
     * @return false when ship is not sunken
     */
    public boolean isShipSunken(ShipType shipType){
        return !gameBoard.containsValue(shipType);
    }

    public void makePositionShot(String position) {
        gameBoard.put(position, ShipType.POSITION_DESTROYED);
    }

    /*
     * Returns false if position is out of game board. Image object that can then be painted on the screen.
     *      Position must be inside game limits.
     * @param  position - the location of field at the map / or location of the hit request
     * @return  the true when position is inside map,
     * @return false when position is out of the map
     */
    public boolean isPositionInsideGameBoard(String position) {
        return gameBoard.containsKey(position);
    }
}
