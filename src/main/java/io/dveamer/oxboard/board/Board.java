package io.dveamer.oxboard.board;

import io.dveamer.oxboard.Stone;
import io.dveamer.oxboard.enums.GameState;

/**
 * Created by mret on 16. 4. 10.
 */
public interface Board {


    public void init();

    public boolean put(Stone stone);

    public GameState state();

    public void print();

    public int sizeOfRow();

    public int sizeOfCol();

    public void takeLastStoneBack();

}
