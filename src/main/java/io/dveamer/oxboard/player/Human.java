package io.dveamer.oxboard.player;

import io.dveamer.oxboard.Stone;
import io.dveamer.oxboard.board.Board;
import io.dveamer.oxboard.enums.StoneColor;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dveamer on 16. 4. 10
 */
public class Human implements Player {

    final private StoneColor color;

    public Human(StoneColor color){
        this.color = color;
    }

    public void play(StoneColor currentColor, Board board){
        if(this.color != currentColor){
            return;
        }

        String row = null;
        String col = null;
        boolean result= false;
        try(Scanner in = new Scanner(new FilterInputStream(System.in) {
            @Override
            public void close() throws IOException {
                //don't close System.in!
            }
        })){

            while(!result){
                System.out.format("Human '%s', enter your move (row[1-%d] column[1-%d]): ", color.name(), board.sizeOfRow(), board.sizeOfCol());

                try{
                    row = in.next();
                    col = in.next();

                    final Stone stone = new Stone(Integer.parseInt(row)-1, Integer.parseInt(col)-1, color);
                    result = board.put(stone);
                    if(!result){
                        throw new Exception("Invalid Movement");
                    }

                }catch(Exception ex){
//                    ex.printStackTrace();
                    System.out.format("This move at ( '%s', '%s' ) is not valid. Try again... \n", row, col);
                }
            }
        }
    }

}
