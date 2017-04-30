package tests;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdimitris.boxcrush.Box;
import com.gdimitris.boxcrush.BoxFactory;
import com.gdimitris.boxcrush.BoxGrid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoxGridTests {

    private BoxGrid boxGrid;
    private BoxFactory boxFactory;

    @Before
    public void setup(){
        boxFactory = Mockito.mock(BoxFactory.class);
        when(boxFactory.createBox(anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(Mockito.mock(Box.class));
        boxGrid = new BoxGrid(boxFactory,12);
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
        verify(mockBox).setPosition(140.0f,140.0f);
        boxGrid.addBoxAtPosition(mockBox2,1,3);
        verify(mockBox2).setPosition(210.0f,70.0f);
        boxGrid.shiftBoxesByOneRow();

        assertEquals(mockBox,boxGrid.getBoxAt(3,2));
        assertNull(boxGrid.getBoxAt(2,2));
        assertEquals(mockBox2,boxGrid.getBoxAt(2,3));
        assertNull(boxGrid.getBoxAt(1,3));
        verify(mockBox).setPosition(140.0f,210.0f);
        verify(mockBox2).setPosition(210.0f,140.0f);
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
    public void testShouldDelegateDrawCallsToBoxes(){
        Box mockBox = mock(Box.class);
        Box mockBox2 = mock(Box.class);
        SpriteBatch mockSpriteBatch = mock(SpriteBatch.class);

        boxGrid.addBoxAtPosition(mockBox,1,1);
        boxGrid.addBoxAtPosition(mockBox2,2,2);
        boxGrid.draw(mockSpriteBatch);

        verify(mockBox).draw(mockSpriteBatch);
        verify(mockBox2).draw(mockSpriteBatch);
    }

    @Test
    public void testShouldCreateAFullRowOnRow0(){
        boxGrid.createNewRowOfBoxes();

        for(int i=0;i<7;i++){
            assertNotNull(boxGrid.getBoxAt(0,i));
        }
    }
}
