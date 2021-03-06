package com.gdimitris.boxcrush;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class BoxGrid {

    private static final int ROWS = 9;
    private static final int COLUMNS = 7;
    private Box[][] boxArray;
    private int boxSize;
    private int padding;
    private BoxFactory boxFactory;

    public BoxGrid(BoxFactory boxFactory, int width){
        boxArray = new Box[ROWS][COLUMNS];
        this.boxFactory = boxFactory;
        padding = 3;
        boxSize = (width-(padding * (COLUMNS + 1)))/COLUMNS;

    }

    public void addBoxAtPosition(Box box, int row, int col) {
        if (box!=null){
            boxArray[row][col] = box;
            int x = col * (boxSize + padding) + padding;
            int y = row * (boxSize + padding) + padding;
            box.setEntityPosition(new Vector3(x, y,0.0f));
        }
    }

    public Box getBoxAt(int row, int col) {
        return boxArray[row][col];
    }

    public void shiftBoxesByOneRow() {
        for(int row = ROWS-2; row>=0;row--){
            for(int col = COLUMNS-1; col>=0;col--){
                if (boxArray[row][col] != null){
                    addBoxAtPosition(boxArray[row][col],row+1,col);
                    removeBoxAtPosition(row,col);
                }
            }
        }
    }

    public boolean rowIsEmpty(int rowIndex) {
        for(int col = 0; col< COLUMNS-1;col++){
            if (boxArray[rowIndex][col] != null) return false;
        }
        return true;
    }

    public void removeBoxAtPosition(int row, int col) {
        boxArray[row][col] = null;
    }

    public void  draw(SpriteBatch spriteBatch) {
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMNS;j++){
                if(boxArray[i][j] != null){
                    boxArray[i][j].draw(spriteBatch);
                }
            }
        }
    }

    public void createNewRowOfBoxes() {
        for(int i=0;i<COLUMNS;i++){
            addBoxAtPosition(boxFactory.createBox(0,0, boxSize,boxSize),0,i);
        }
    }
}
