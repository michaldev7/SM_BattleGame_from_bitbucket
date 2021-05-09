package com.michal.battleship.domain.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTypeTest {

    public static final String HIT = "HIT";
    public static final String MISS = "MISS";

    @Test
    void shouldReturnTrueWhenTypeIsShip() {
        assertTrue(ShipType.ONE_DECKER.isShipFound());
        assertTrue(ShipType.TWO_DECKER.isShipFound());
        assertTrue(ShipType.THREE_DECKER.isShipFound());
        assertTrue(ShipType.FOUR_DECKER.isShipFound());
    }

    @Test
    void shouldNotBeReadAsShip() {
        assertFalse(ShipType.POSITION_MISS.isShipFound());
        assertFalse(ShipType.POSITION_DESTROYED.isShipFound());
    }

    @Test
    void shouldReturnMissWhenShipNotFound() {
        assertEquals(MISS, ShipType.POSITION_DESTROYED.getResultMessageForPlayer());
        assertEquals(MISS, ShipType.POSITION_MISS.getResultMessageForPlayer());
    }

    @Test
    void shouldReturnHitWhenShipFound() {
        assertEquals(HIT, ShipType.ONE_DECKER.getResultMessageForPlayer());
        assertEquals(HIT, ShipType.FOUR_DECKER.getResultMessageForPlayer());
    }
}