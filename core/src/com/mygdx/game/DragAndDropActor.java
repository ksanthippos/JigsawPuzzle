package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base_classes.BaseActor;

public class DragAndDropActor extends BaseActor {

    private DragAndDropActor self;
    private float grabOffsetX;
    private float grabOffsetY;

    public DragAndDropActor(float x, float y, Stage s) {
        super(x, y, s);
        self = this;

        addListener(new InputListener() {

           public boolean touchDown(InputEvent event, float offsetX, float offsetY, int pointer, int button) {
               self.grabOffsetX = offsetX;
               self.grabOffsetY = offsetY;
               self.toFront();
               return true;
           }

           public void touchDragged(InputEvent event, float offsetX, float offsetY, int pointer) {
               float deltaX = offsetX - self.grabOffsetX;
               float deltaY = offsetY - self.grabOffsetY;
               self.moveBy(deltaX, deltaY);
           }

           public void touchUp(InputEvent event, float offsetX, float offsetY, int pointer, int button) {
               // later
           }

        });
    }

    @Override
    public void act(float dt) {
        super.act(dt);
    }

}
