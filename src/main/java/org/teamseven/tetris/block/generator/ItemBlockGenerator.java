package org.teamseven.tetris.block.generator;

import org.teamseven.tetris.block.*;
import org.teamseven.tetris.block.item.*;

import java.util.Random;

public class ItemBlockGenerator extends RouletteWheelGenerator {

    @Override
    public Block generate() {
        Block block = super.generate();
        Random random = new Random();

        int index = random.nextInt(5);

        switch (index) {
            case 0:
                return new InnerItemBlock(block.getShape(), new BoomBlock());
            case 1:
                return new WeightBlock();
            case 2:
                return new ClearBlock();
            case 3:
                return new InnerItemBlock(block.getShape(), new LineRemoveBlock());
            case 4:
                return new SBlock();
        }
        return null;
    }
}
