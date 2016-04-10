package io.dveamer.oxboard.board.check;

import io.dveamer.oxboard.Stone;
import io.dveamer.oxboard.enums.StoneColor;

import java.util.LinkedList;

/**
 * Created by mret on 16. 4. 10.
 */
public class ThreeConnectionExists implements CheckType {

    static final private int THREE = 3;

    @Override
    public int test(int start, int end, int goalCnt, LinkedList<Stone> stoneList, CheckLambda checkLambda) {
        int blinkCnt = 0;
        int count = 0;
        int totalCnt = 0;
        int result = 0;
        Stone stone = stoneList.peekLast();
        for(int i=start; i<end; i++){
            if(checkLambda.test(i, stone.getColor())){
                count++;
                blinkCnt=0;
            }else if(checkLambda.test(i, StoneColor.EMPTY)) {
                if(blinkCnt==0){
                    blinkCnt++;
                }else{
                    count=0;
                    blinkCnt=0;
                }
            }else{
                if(count==THREE && blinkCnt==0){
                    result=0;
                }
                count=0;
                blinkCnt=0;
            }

            if(count==THREE){
                result = 1;
            }else if(count>THREE){
                return -1;
            }

            totalCnt++;
            if(totalCnt == stoneList.size()){
                break;
            }
        }
        return result;
    }
}
