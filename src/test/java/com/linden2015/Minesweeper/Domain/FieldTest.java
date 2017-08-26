package com.linden2015.Minesweeper.Domain;

import com.linden2015.Minesweeper.Domain.Field;
import com.linden2015.Minesweeper.Domain.FieldStatus;
import com.linden2015.Minesweeper.Domain.Location;
import com.linden2015.Minesweeper.Domain.MsField;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void uncleared() {
        Field field = newField();
        assertEquals(
            FieldStatus.UNCLEARED,
            field.status()
        );
    }

    @Test
    public void hit() {
        Field field = newField();
        for (Location location : field.locations()) {
            location.stepOn();
        }
        assertEquals(
            FieldStatus.HIT,
            field.status()
        );
    }

    @Test
    public void cleared() {
        Field field = newField();
        for (Location location : field.locations()) {
            if (!location.isMine()) {
                location.stepOn();
            }
        }
        assertEquals(
            FieldStatus.CLEARED,
            field.status()
        );
    }

    @Test
    public void randomization() {
        Field field1 = newField();
        Field field2 = newField();
        boolean differenceFound = false;
        for (int i = 0; i < field1.locations().size(); i++) {
            if (
                field1.locations().get(i).isMine()
                != field2.locations().get(i).isMine()
            ) {
                differenceFound = true;
            }
        }
        assertTrue(differenceFound);
    }

    private Field newField() {
        return new MsField(16, 16, 40);
    }
}
