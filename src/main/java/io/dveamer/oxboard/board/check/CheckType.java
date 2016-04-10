package io.dveamer.oxboard.board.check;

import io.dveamer.oxboard.Stone;

import java.util.LinkedList;

/**
 * Created by mret on 16. 4. 10.
 */
public interface CheckType {

    public int test(int start, int end, int goalCnt, LinkedList<Stone> stoneList, CheckLambda checkLambda);

}
