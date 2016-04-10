package io.dveamer.oxboard.board;

import io.dveamer.oxboard.Stone;
import io.dveamer.oxboard.board.check.Checker;
import io.dveamer.oxboard.board.check.ThreeConnectionExists;
import io.dveamer.oxboard.board.check.SeriesConnectionToGoal;
import io.dveamer.oxboard.enums.GameState;
import io.dveamer.oxboard.enums.StoneColor;

import java.util.LinkedList;

/**
 * Created by dveamer on 16. 4. 10
 */
public class FiveMokBoard implements Board {

    final private int ROWS;
    final private int COLS;

    final private Stone[][] stones;
    final private LinkedList<Stone> stoneList = new LinkedList<>();

    final private Checker winChecker;
    final private Checker duplicatedThreeConnectionChecker;

    public FiveMokBoard(int rows, int cols, int goalCnt) throws Exception {
        ROWS = rows;
        COLS = cols;
        stones = new Stone[ROWS][COLS];

        if(goalCnt > ROWS && goalCnt >COLS){
            throw new Exception("goalCnt is bigger than the size of board.");
        }
        init();

        winChecker = new Checker(new SeriesConnectionToGoal(), ROWS, COLS, goalCnt, stones, stoneList);
        duplicatedThreeConnectionChecker = new Checker(new ThreeConnectionExists(), ROWS, COLS, goalCnt, stones, stoneList);
    }

    public void init() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                stones[row][col] = new Stone(row,col);  // clear the cell color
            }
        }

        stoneList.clear();
    }

    public boolean put(Stone stone){

        if(stone.getRow() < 0){
            return false;
        }

        if(stone.getRow() >= ROWS) {
            return false;
        }

        if(stone.getCol() < 0){
            return false;
        }

        if(stone.getCol() >= COLS){
            return false;
        }

        switch(stones[stone.getRow()][stone.getCol()].getColor()) {
            case BLACK :
            case WHITE : return false;
        }

        stones[stone.getRow()][stone.getCol()] = stone;
        stoneList.add(stone);

        if(!isValidStone(stone)){
            takeLastStoneBack();
            return false;
        }

        return true;
    }

    private boolean isValidStone(Stone stone){

        int inRow = duplicatedThreeConnectionChecker.inRow(stone);
        if(inRow<0){
            return true;
        }

        int inCol = duplicatedThreeConnectionChecker.inCol(stone);
        if(inCol<0){
            return true;
        }

        int inDiagonal = duplicatedThreeConnectionChecker.inDiagonal(stone);
        if(inDiagonal<0){
            return true;
        }

        int inOppositeDiagonal = duplicatedThreeConnectionChecker.inOppositeDiagonal(stone);
        if(inOppositeDiagonal<0){
            return true;
        }

        return 2 > inRow + inCol + inDiagonal + inOppositeDiagonal;
    }

    private boolean isDraw() {
        if(stoneList.size() >= COLS*ROWS) {
            return true;
        }
        return false;
    }

    private boolean isFinalStone(Stone stone) {
        return 0 < winChecker.inRow(stone) +
                winChecker.inCol(stone) +
                winChecker.inDiagonal(stone) +
                winChecker.inOppositeDiagonal(stone);
    }

    public GameState state() {
        Stone stone = stoneList.peekLast();

        if(isFinalStone(stone)){
            switch(stone.getColor()){
                case BLACK : return GameState.BLACK_WON;
                case WHITE : return GameState.WHITE_WON;
            }
        }

        if(isDraw()){
            return GameState.DRAW;
        }

        return GameState.PLAYING;
    }

    public void print() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<COLS+1; i++){
            sb.append("----");
            System.out.format(" %d |", i%10);
        }
        String enter = sb.toString();
        System.out.println("\n"+enter);

        for (int row = 0; row < ROWS; row++) {
            System.out.format(" %d |", (row + 1)%10);
            for (int col = 0; col < COLS; col++) {
                stones[row][col].paint();
                System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println(enter);
            }
        }
    }

    public int sizeOfRow(){
        return ROWS;
    }

    public int sizeOfCol(){
        return COLS;
    }

    public void takeLastStoneBack(){
        Stone stone = stoneList.removeLast();
        stone.setColor(StoneColor.EMPTY);
    }
}
