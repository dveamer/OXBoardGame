package io.dveamer.oxboard.board.check;

import io.dveamer.oxboard.Stone;
import io.dveamer.oxboard.enums.StoneColor;

import java.util.LinkedList;

/**
 * Created by mret on 16. 4. 10.
 */
public class Checker {

    final private CheckType checkType;
    final private int ROWS;
    final private int COLS;
    final private int goalCnt;
    final private Stone[][] stones;
    final private LinkedList<Stone> stoneList;

    public Checker(CheckType checkType, int rows, int cols, int goalCnt, Stone[][] stones, LinkedList<Stone> stoneList) {
        this.checkType = checkType;
        ROWS = rows;
        COLS = cols;
        this.goalCnt = goalCnt;
        this.stones = stones;
        this.stoneList = stoneList;
    }


    public int inRow(final Stone stone) {
        final int start = Math.max(0, stone.getRow() - goalCnt);
        final int end = Math.min(ROWS, stone.getRow()+ goalCnt);

        return checkType.test(start, end, goalCnt, stoneList, new CheckLambda() {
            @Override
            public boolean test(final int index, final StoneColor color) {
                return stones[index][stone.getCol()].colorIs(color);
            }
        });
    }

    public int inCol(final Stone stone) {
        final int start = Math.max(0, stone.getCol()- goalCnt);
        final int end = Math.min(COLS, stone.getCol()+ goalCnt);

        return checkType.test(start, end, goalCnt, stoneList, new CheckLambda() {
            @Override
            public boolean test(final int index, final StoneColor color) {
                return stones[stone.getRow()][index].colorIs(color);
            }
        });
    }

    public int inDiagonal(final Stone stone) {

        final int gapStartRow = stone.getRow() - Math.max(0, stone.getRow() - goalCnt);
        final int gapEndRow = Math.min(ROWS-1, stone.getRow() + goalCnt) - stone.getRow();

        final int gapStartCol = stone.getCol() - Math.max(0, stone.getCol() - goalCnt);
        final int gapEndCol = Math.min(COLS-1, stone.getCol() + goalCnt) - stone.getCol();

        final int gapStart = Math.min(gapStartCol, gapStartRow);
        final int gapEnd = Math.min(gapEndCol, gapEndRow);

        final int startRow = stone.getRow() - gapStart;
        final int startCol = stone.getCol() - gapStart;

        return checkType.test(0, gapStart+gapEnd+1, goalCnt, stoneList, new CheckLambda() {
            @Override
            public boolean test(final int index, final StoneColor color) {
                return stones[startRow + index][startCol + index].colorIs(color);
            }
        });
    }

    public int inOppositeDiagonal(final Stone stone) {

        final int gapStartRow = stone.getRow() - Math.max(0, stone.getRow() - goalCnt);
        final int gapEndRow = Math.min(ROWS - 1, stone.getRow() + goalCnt) - stone.getRow();

        final int gapStartCol = Math.min(COLS - 1, stone.getCol() + goalCnt) - stone.getCol();
        final int gapEndCol = stone.getCol() - Math.max(0, stone.getCol() - goalCnt);

        final int gapStart = Math.min(gapStartCol, gapStartRow);
        final int gapEnd = Math.min(gapEndCol, gapEndRow);

        final int startRow = stone.getRow() - gapStart;
        final int startCol = stone.getCol() + gapStart;

        return checkType.test(0, gapStart+gapEnd+1, goalCnt, stoneList, new CheckLambda() {
            @Override
            public boolean test(final int index, final StoneColor color) {
                return stones[startRow + index][startCol - index].colorIs(color);
            }
        });
    }
}
