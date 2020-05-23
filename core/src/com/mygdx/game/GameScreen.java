package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base_classes.BaseActor;
import com.mygdx.game.base_classes.BaseGame;
import com.mygdx.game.base_classes.BaseScreen;

public class GameScreen extends BaseScreen {

    private Label messageLabel;

    @Override
    public void initialize() {

        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadTexture("background.jpg");

        int numberRows = 3;
        int numberCols = 3;

        Texture texture = new Texture(Gdx.files.internal("sun.jpg"), true);
        int imageWidth = texture.getWidth();
        int imageHeight = texture.getHeight();
        int pieceWidth = imageWidth / numberCols;
        int pieceHeight = imageHeight / numberRows;

        TextureRegion[][] temp = TextureRegion.split(texture, pieceWidth, pieceHeight);

        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberCols; j++) {

                // create pieces and randomize order
                int pieceX = MathUtils.random(0, 400 - pieceWidth);
                int pieceY = MathUtils.random(0, 600 - pieceHeight);
                PuzzlePiece pp = new PuzzlePiece(pieceX, pieceY, mainStage);

                // dragging causes animation (piece gets zoomed in a bit)
                Animation<TextureRegion> anim = new Animation<TextureRegion>(1, temp[i][j]);
                pp.setAnimation(anim);
                pp.setRow(i);
                pp.setCol(j);

                int marginX = (400 - imageWidth) / 2;
                int marginY = (600 - imageHeight) / 2;
                // board setup
                int areaX = (400 + marginX) + pieceWidth * j;
                int areaY = (600 - marginY - pieceHeight) - pieceHeight * i;

                PuzzleArea pa = new PuzzleArea(areaX, areaY, mainStage);
                pa.setSize(pieceWidth, pieceHeight);
                pa.setBoundaryRectangle();
                pa.setRow(i);
                pa.setCol(j);
            }
        }

        messageLabel = new Label("...", BaseGame.labelStyle);
        messageLabel.setColor(Color.CYAN);
        uiTable.add(messageLabel).expandX().expandY().bottom().pad(50);
        messageLabel.setVisible(false);
    }

    @Override
    public void update(float dt) {

        // check win conditions
        boolean solved = true;
        for (BaseActor actor: BaseActor.getList(mainStage, PuzzlePiece.class.getCanonicalName())) {

            PuzzlePiece pp = (PuzzlePiece) actor;
            if (!pp.isCorrectlyPlaced())
                solved = false;

            if (solved) {
                messageLabel.setText("You solved the puzzle!");
                messageLabel.setVisible(true);
            }

            else {
                messageLabel.setText("...");
                messageLabel.setVisible(false);
            }
        }

    }
}
