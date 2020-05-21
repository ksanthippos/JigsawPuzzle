package com.mygdx.game.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.game.JigsawPuzzle;


public class DesktopLauncher {
	public static void main (String[] arg) {

		Game game = new JigsawPuzzle();
		new LwjglApplication(game, "Jigsaw Puzzle Game", 800, 600);

	}
}
