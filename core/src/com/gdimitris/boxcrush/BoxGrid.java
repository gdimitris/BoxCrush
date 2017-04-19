package com.gdimitris.boxcrush;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoxGrid {

    private static final int ROWS = 9;
    private static final int COLUMNS = 7;
    private Box[][] boxArray;


    public BoxGrid(int width, int height){
        boxArray = new Box[ROWS][COLUMNS];
    }

    public void addBoxAtPosition(Box box, int row, int col) {
        boxArray[row][col] = box;
    }

    public Box getBoxAt(int row, int col) {
        return boxArray[row][col];
    }

    public void shiftBoxesByOneRow() {
        for(int row = ROWS-2; row>0;row--){
            for(int col = COLUMNS-1; col> 0;col--){
               boxArray[row+1][col] = boxArray[row][col];
               removeBoxAtPosition(row,col);
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

    public void draw(SpriteBatch spriteBatch) {
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMNS;j++){
                if(boxArray[i][j] != null){
                    boxArray[i][j].draw(spriteBatch);
                }
            }
        }
    }
}
