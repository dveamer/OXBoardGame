package io.dveamer.oxboard;

import io.dveamer.oxboard.enums.StoneColor;

/**
 * Created by dveamer on 16. 4. 10
 */
public class Stone {
    private StoneColor color;
    final private int row, col;

    public Stone(int row, int col) {
        this.row = row;
        this.col = col;
        color = StoneColor.EMPTY;
    }

    public Stone(int row, int col, StoneColor color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public void paint() {
        switch (color) {
            case WHITE:  System.out.print(" O "); break;
            case BLACK: System.out.print(" X "); break;
            case EMPTY:  System.out.print("   "); break;
        }
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setColor(StoneColor color){
        this.color = color;
    }

    public StoneColor getColor(){
        return color;
    }

    public boolean colorIs(StoneColor color){
        return this.color == color;
    }


}
