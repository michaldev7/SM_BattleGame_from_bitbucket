package com.michal.battleship.processor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrawProcessor {

    private static final Integer BOARD_LIMIT_Y = 10;
    private static final String BOARD_LIMIT_X = "A,B,C,D,E,F,G,H,I,J";
    public static final String REGEX_SPLITTER = ",";

    public List<String> initGameLineX() {
        return Stream.of(BOARD_LIMIT_X.split(REGEX_SPLITTER, -1)).collect(Collectors.toList());
    }

    public List<String> initGameLineY() {
        return Stream.iterate(1, n -> n + 1).limit(BOARD_LIMIT_Y).map(Object::toString).collect(Collectors.toList());
    }
}
