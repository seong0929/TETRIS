package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;

import static org.teamseven.tetris.Const.*;

public class MainFrame extends BaseFrame {

    public MainFrame() {
        this.setFrame(APPLICATION_TITLE);
    }

    @Override
    public void setComp() {
        getContentPane().add(new GameMenuPane());
    }

    @Override
    public void setDesign() {
        setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
    }

    @Override
    public void setAction() {
    }

}

