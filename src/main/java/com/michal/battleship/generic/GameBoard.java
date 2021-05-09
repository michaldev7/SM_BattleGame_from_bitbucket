package com.michal.battleship.generic;

import com.michal.battleship.domain.type.ShipType;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.processor.DefaultBoard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoard extends Board {

    private static final String AVAILABLE_GAME_POSITIONS = "A,B,C,D,E,F,G,H,I,J";
    public static final String REGEX_SPLITTER = ",";
    private static final String EMPTY_STRING = "";
    public static final int INDEX = 0;

    @Override
    public Map<String, ShipType> drawBattleBoard(PlayerType type) {
        List<String> allPositions = getAllPositionsOfGame();
        Map<String, ShipType> board = DefaultBoard.findByType(type);
        addSea(board, allPositions);
        return board;
    }

    private List<String> getAllPositionsOfGame() {
        List<String> allAvailablePositions = new ArrayList<>();
        generateAllPositionInGame(getBoardHeadings(), allAvailablePositions, INDEX, EMPTY_STRING);
        return allAvailablePositions;
    }

    private List<List<String>> getBoardHeadings() {
        return Arrays.asList(getColumnHeadings(), getBoardRows());
    }

    /*
    All positions where player didn't put any ship, will be marked as 'miss'
     */
    private void addSea(Map<String, ShipType> playerShips, List<String> allGamePositions) {
        allGamePositions.forEach(x -> playerShips.putIfAbsent(x, ShipType.POSITION_MISS));
    }

    /*
    Permutation generator to calculate all available positions on map.
    Position stands for:
    "A1","A2", .. "J10"
     */
    private void generateAllPositionInGame(List<List<String>> headings, List<String> result, int depth, String actual) {
        if (depth == headings.size()) {
            result.add(actual);
            return;
        }
        for (var i = 0; i < headings.get(depth).size(); i++) {
            generateAllPositionInGame(headings, result, depth + 1, actual + headings.get(depth).get(i));
        }
    }

    private List<String> getColumnHeadings() {
        return Stream.of(AVAILABLE_GAME_POSITIONS.split(REGEX_SPLITTER, -1)).collect(Collectors.toList());
    }

    private List<String> getBoardRows() {
        return Stream.iterate(1, n -> n + 1).limit(GameConfig.GAME_SIZE).map(Object::toString).collect(Collectors.toList());
    }
}
