package com.linden2015.Minesweeper.Domain;

import com.linden2015.Minesweeper.Domain.Location;
import com.linden2015.Minesweeper.Domain.MsLocation;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void isMine() {
        Location location = new MsLocation(1, 1, true);
        assertTrue(
            location.isMine()
        );
    }

    @Test
    public void isNotAMine() {
        Location location = new MsLocation(1, 1, false);
        assertFalse(
            location.isMine()
        );
    }

    @Test
    public void equals() {
        Location location1 = new MsLocation(1, 1, true);
        Location location2 = new MsLocation(1, 1, false);
        assertTrue(location1.equals(location2));
    }

    @Test
    public void unEquals() {
        Location location1 = new MsLocation(1, 2, false);
        Location location2 = new MsLocation(1, 1, false);
        assertFalse(location1.equals(location2));
    }

    @Test
    public void adjacent_specific() {
        // Same
        assertFalse(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(1000, 1000, false))
        );

        // To the right of
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(1001, 1000, false))
        );

        // To the left of
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(999, 1000, false))
        );

        // Above
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(1000, 999, false))
        );

        // Below
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(1000, 1001, false))
        );

        // Above and left
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(999, 999, false))
        );

        // Above and right
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(1001, 999, false))
        );

        // Below and left
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(999, 1001, false))
        );

        // Below and right
        assertTrue(
            new MsLocation(1000, 1000, false).isAdjacentTo(
                new MsLocation(1001, 1001, false))
        );
    }

    @Test
    public void adjacent_count() {
        Location location = new MsLocation(1, 1, false);
        int adjacentCount = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (location.isAdjacentTo(new MsLocation(i, j, false))) {
                    adjacentCount++;
                }
            }
        }
        assertEquals(8, adjacentCount);
    }
}
