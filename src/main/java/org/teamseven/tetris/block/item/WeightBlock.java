package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;

import java.awt.*;

public class WeightBlock extends Block implements ItemBlock {

    public WeightBlock() {
        color = Color.PINK;
        shape = new UnitBlock[][] {
                {null                , new UnitBlock(color), new UnitBlock(color), null},
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)}
        };

    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {
        
    }

    @Override
    public int execute(GameBoard board, CurrBlock curr, int[] pos) {
        return 0;
    }
}
