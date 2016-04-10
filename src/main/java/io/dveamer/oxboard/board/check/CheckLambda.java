package io.dveamer.oxboard.board.check;

import io.dveamer.oxboard.enums.StoneColor;

/**
 * Created by mret on 16. 4. 10.
 */
public interface CheckLambda {
    public boolean test(int index, final StoneColor color);
}
