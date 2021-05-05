package com.michal.battleship.generic;

import com.michal.battleship.domain.type.Field;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.processor.DefaultBoard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoard extends Board {

    private static final Integer BOARD_SIZE = 10;
    private static final String BOARD_CHARACTERS = "A,B,C,D,E,F,G,H,I,J";
    public static final String REGEX_SPLITTER = ",";
    private static final String EMPTY_STRING = "";
    public static final int INDEX = 0;

    @Override
    public Map<String, Field> drawBattleBoard(PlayerType type) {
        List<String> allPositions = getAllPossibleFieldsOnBoard();
        Map<String, Field> board = DefaultBoard.findByType(type);
        addSea(board, allPositions);
        return board;
    }

    private List<String> getAllPossibleFieldsOnBoard() {
        List<String> allAvailableFieldsOnMap = new ArrayList<>();
        generateAllAvailablePositionsWithPermutation(getBoardHeadings(), allAvailableFieldsOnMap, INDEX, EMPTY_STRING);
        return allAvailableFieldsOnMap;
    }

    private List<List<String>> getBoardHeadings() {
        return Arrays.asList(getColumnHeadings(), getRowHeading());
    }

    /*
    All positions where player didn't put any ship, will be marked as 'miss'
     */
    private void addSea(Map<String, Field> playerShips, List<String> allGamePositions) {
        allGamePositions.forEach(x -> playerShips.putIfAbsent(x, Field.MISS_NO_SHIP));
    }

    /*
    Generate all possible positions. Useful for draw game board, hit (miss) flow and gameplay.
    "A1","A2"..."J1".."J10"
     */
    private void generateAllAvailablePositionsWithPermutation(List<List<String>> lists, List<String> result, int depth, String actual) {
        if (depth == lists.size()) {
            result.add(actual);
            return;
        }
        for (var i = 0; i < lists.get(depth).size(); i++) {
            generateAllAvailablePositionsWithPermutation(lists, result, depth + 1, actual + lists.get(depth).get(i));
        }
    }

    private List<String> getColumnHeadings() {
        return Stream.of(BOARD_CHARACTERS.split(REGEX_SPLITTER, -1)).collect(Collectors.toList());
    }

    private List<String> getRowHeading() {
        return Stream.iterate(1, n -> n + 1).limit(BOARD_SIZE).map(Object::toString).collect(Collectors.toList());
    }
}
