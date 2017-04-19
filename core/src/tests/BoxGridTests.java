package tests;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdimitris.boxcrush.Box;
import com.gdimitris.boxcrush.BoxGrid;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class BoxGridTests {

    private BoxGrid boxGrid;

    @Before
    public void setup(){
        boxGrid = new BoxGrid(500,500);
    }

    @Test
    public void testShouldInsertBox(){
        Box mockBox = mock(Box.class);

        boxGrid.addBoxAtPosition(mockBox,1,1);

        assertEquals(mockBox,boxGrid.getBoxAt(1,1));
    }

    @Test
    public void testShouldRemoveBox(){
        Box mockBox = mock(Box.class);

        boxGrid.addBoxAtPosition(mockBox,1,2);
        boxGrid.removeBoxAtPosition(1,2);

        assertNull(boxGrid.getBoxAt(1,2));
    }

    @Test
    public void testShiftsBoxesDown(){
        Box mockBox = mock(Box.class);
        Box mockBox2 = mock(Box.class);

        boxGrid.addBoxAtPosition(mockBox,2,2);
        boxGrid.addBoxAtPosition(mockBox2,1,3);
        boxGrid.shiftBoxesByOneRow();

        assertEquals(mockBox,boxGrid.getBoxAt(3,2));
        assertNull(boxGrid.getBoxAt(2,2));
        assertEquals(mockBox2,boxGrid.getBoxAt(2,3));
        assertNull(boxGrid.getBoxAt(1,3));
    }

    @Test
    public void testRow1ShouldNotBeEmpty(){
        Box mockBox = mock(Box.class);

        boxGrid.addBoxAtPosition(mockBox,1,1);

        assertFalse(boxGrid.rowIsEmpty(1));
    }

    @Test
    public void testRow1ShouldBeEmptyWhenShifted(){
        Box mockBox = mock(Box.class);

        boxGrid.addBoxAtPosition(mockBox,1,1);
        boxGrid.shiftBoxesByOneRow();

        assertTrue(boxGrid.rowIsEmpty(1));
    }

    @Test
    public void testShouldDelegateDrawCallsToBoxess(){
        Box mockBox = mock(Box.class);
        Box mockBox2 = mock(Box.class);
        SpriteBatch mockSpriteBatch = mock(SpriteBatch.class);

        boxGrid.addBoxAtPosition(mockBox,1,1);
        boxGrid.addBoxAtPosition(mockBox2,2,2);
        boxGrid.draw(mockSpriteBatch);

        verify(mockBox).draw(mockSpriteBatch);
        verify(mockBox2).draw(mockSpriteBatch);
    }


}
