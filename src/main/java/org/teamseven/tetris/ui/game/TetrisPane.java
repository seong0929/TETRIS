package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.ItemModeHandler;
import org.teamseven.tetris.ui.IDesign;
import org.teamseven.tetris.ui.menu.ScoreBoardPane;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.teamseven.tetris.Const.*;

public class TetrisPane extends BaseTetrisPane implements IDesign {

    private final GameHandler gameHandler;

    private OnePlayerKeyEventDispatcher keyEventDispatcher;

    public TetrisPane(GameHandler gameHandler) {
        this.gameHandler = gameHandler;

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        main = new JPanel();
        tetrisBoard = new JTextPane();
        nextBlockBoard = new JTextPane();
        scoreBoard = new JTextPane();

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();

        //Set timer for block drops.
        Timer timer = new Timer(INIT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameHandler.playing()) {
                    drawBoard();
                } else {
                    finishGame();
                }
            }
        });

        gameHandler.setTimer(timer);

        drawBoard();
        timer.start();
    }

    @Override
    public void finishGame() {
        gameHandler.pause();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.removeKeyEventDispatcher(keyEventDispatcher);
        Pipeline.replacePane(new ScoreBoardPane(gameHandler instanceof ItemModeHandler, gameHandler.getScore()));
    }

    @Override
    public void drawBoard() {
        drawGameBoard(gameHandler, tetrisBoard);
        drawNextBlock(gameHandler, nextBlockBoard);
        drawScore(gameHandler, scoreBoard);
    }

    @Override
    public void setDesign() {
        tetrisBoard.setEditable(false);
        nextBlockBoard.setEditable(false);
        scoreBoard.setEditable(false);

        tetrisBoard.setBackground(Color.BLACK);
        nextBlockBoard.setBackground(Color.BLACK);
        scoreBoard.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, preferredResolution[1] / 60),
                BorderFactory.createLineBorder(Color.darkGray, preferredResolution[1] / 90));

        tetrisBoard.setBorder(border);
        nextBlockBoard.setBorder(border);
        scoreBoard.setBorder(border);

        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] * 3 / 8, preferredResolution[1] / 18, 0);
        make(tetrisBoard, 0, 0, 1, 2);

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] / 16, preferredResolution[1] / 180, preferredResolution[0] * 3 / 16);
        make(nextBlockBoard, 1, 0, 1, 1);

        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 16, preferredResolution[1] * 3 / 5, preferredResolution[0] * 3 / 16);
        make(scoreBoard, 1, 1, 1, 1);

        main.add(tetrisBoard);
        main.add(nextBlockBoard);
        main.add(scoreBoard);

        main.setBounds(0, 0, preferredResolution[0], preferredResolution[1]);
        this.add(main, JLayeredPane.DEFAULT_LAYER);

    }

    @Override
    public void setAction() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyEventDispatcher = new OnePlayerKeyEventDispatcher(gameHandler, new IKeyInputFeedback() {
            @Override
            public void feedback() {
                drawBoard();
            }
        });
        manager.addKeyEventDispatcher(keyEventDispatcher);
    }

}
