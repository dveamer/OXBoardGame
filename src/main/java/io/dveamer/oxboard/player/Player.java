package io.dveamer.oxboard.player;

import io.dveamer.oxboard.board.Board;
import io.dveamer.oxboard.enums.StoneColor;

/**
 * Created by mret on 16. 4. 10.
 */
public interface Player {

    public void play(StoneColor currentColor, Board board);
}
