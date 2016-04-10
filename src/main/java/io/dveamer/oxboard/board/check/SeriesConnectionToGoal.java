package io.dveamer.oxboard.board.check;

import io.dveamer.oxboard.Stone;

import java.util.LinkedList;

/**
 * Created by mret on 16. 4. 10.
 */
public class SeriesConnectionToGoal implements CheckType {
    @Override
    public int test(int start, int end, int goalCnt, LinkedList<Stone> stoneList, CheckLambda checkLambda) {
        int count = 0;
        int totalCnt = 0;
        int result = 0;
        Stone stone = stoneList.peekLast();
        for(int i=start; i<end; i++){
            if(checkLambda.test(i, stone.getColor())){
                count++;
            }else{
                count=0;
            }

            if(count==goalCnt){
                result = 1;
            }else if(count>goalCnt){
                return 0;
            }

            totalCnt++;
            if(totalCnt == stoneList.size()){
                break;
            }
        }
        return result;
    }
}
