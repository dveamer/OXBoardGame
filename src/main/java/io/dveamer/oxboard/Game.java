package io.dveamer.oxboard;

import io.dveamer.oxboard.board.Board;
import io.dveamer.oxboard.board.FiveMokBoard;
import io.dveamer.oxboard.board.TicTacToeBoard;
import io.dveamer.oxboard.enums.GameState;
import io.dveamer.oxboard.enums.StoneColor;
import io.dveamer.oxboard.player.Human;
import io.dveamer.oxboard.player.Player;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dveamer on 16. 4. 10
 */
public class Game {
    final private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    private StoneColor currentColor = StoneColor.WHITE;
    private GameState state = GameState.PLAYING;

    public Game(Board board, Player white, Player black){
        this.board = board;
        this.whitePlayer = white;
        this.blackPlayer = black;
        initGame();
    }

    public void start() {
        board.print();
        while( state == GameState.PLAYING ) {
            currentColor = currentColor == StoneColor.BLACK? StoneColor.WHITE: StoneColor.BLACK;

            blackPlayer.play(currentColor, board);
            whitePlayer.play(currentColor, board);
            board.print();

            state = board.state();
        }

        switch (state){
            case DRAW:
                System.out.println("It's Draw! Bye!"); break;
            default : System.out.format("'%s' won! Bye!", currentColor.name());
        }

    }

    public void initGame() {
        board.init();
    }

    public static void main(String[] args) throws Exception {

        int choice = choiceGame();

        switch (choice){
            case 1:
                new Game(new TicTacToeBoard(3,3,3), new Human(StoneColor.BLACK), new Human(StoneColor.WHITE))
                        .start();
                break;
            case 2:
                new Game(new FiveMokBoard(15,15,5), new Human(StoneColor.BLACK), new Human(StoneColor.WHITE))
                        .start();
                break;
            case 3:
                new Game(new FiveMokBoard(30,30,5), new Human(StoneColor.BLACK), new Human(StoneColor.WHITE))
                        .start();
                break;
            default:
                System.out.print("Sorry. We had an error. Bye!");
        }
    }

    private static int choiceGame(){

        System.out.println(" 1. TicTacToe(3X3)");
        System.out.println(" 2. FiveMok(15X15)");
        System.out.println(" 3. FiveMok(30X30)");
        System.out.print("Choice Game [1-3]:");

        int choice = 0;
        String choiceStr = null;
        try(Scanner in = new Scanner(new FilterInputStream(System.in) {
            @Override
            public void close() throws IOException {
                //don't close System.in!
            }
        })){
            while (true) {
                try{
                    choiceStr = in.next();
                    choice = Integer.parseInt(choiceStr);
                    if(choice>0 && choice<4){
                        return choice;
                    }
                }catch(Exception ex){
                    // do nothing.
                }
                System.out.format("This choice '%s' is not valid. Try again... \n", choiceStr);
            }
        }
    }

}
