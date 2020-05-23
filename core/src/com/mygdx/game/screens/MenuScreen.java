package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.JigsawPuzzleGame;
import com.mygdx.game.base_classes.BaseActor;
import com.mygdx.game.base_classes.BaseGame;
import com.mygdx.game.base_classes.BaseScreen;

public class MenuScreen extends BaseScreen {

    @Override
    public void initialize() {

        // background
        BaseActor ocean = new BaseActor(0, 0, mainStage);
        ocean.loadTexture("background.jpg");
        ocean.setSize(800, 600);
        BaseActor.setWorldBounds(ocean);
        Label title = new Label("Jigsaw Puzzle Game", BaseGame.labelStyle);
        title.moveBy(0, 100);

        // start and quit buttons
        TextButton startButton = new TextButton("Start", BaseGame.textButtonStyle);
        TextButton quitButton = new TextButton("Quit", BaseGame.textButtonStyle);
        uiStage.addActor(startButton);
        uiStage.addActor(quitButton);

        startButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(InputEvent.Type.touchDown))
                return false;
            JigsawPuzzleGame.setActiveScreen(new GameScreen());
            return false;
        });

        quitButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(InputEvent.Type.touchDown))
                return false;
            System.exit(0);
            return false;
        });

        // arrange title and buttons
        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);

    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
            JigsawPuzzleGame.setActiveScreen(new GameScreen());
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            System.exit(0);
    }
}
