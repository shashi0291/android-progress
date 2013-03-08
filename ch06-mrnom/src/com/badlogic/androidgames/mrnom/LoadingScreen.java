package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Screen;

public class LoadingScreen extends Screen {
  public LoadingScreen(Game game) {
    super(game);
  }

  public void update(float deltaTime) {
    Graphics graphics = game.getGraphics();
    Audio audio = game.getAudio();
    Assets.background = graphics.newPixmap("background.png", PixmapFormat.RGB565);
    Assets.logo = graphics.newPixmap("logo.png", PixmapFormat.ARGB4444);
    Assets.mainMenu = graphics.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
    Assets.buttons = graphics.newPixmap("buttons.png", PixmapFormat.ARGB4444);
    Assets.help1 = graphics.newPixmap("help1.png", PixmapFormat.ARGB4444);
    Assets.help2 = graphics.newPixmap("help2.png", PixmapFormat.ARGB4444);
    Assets.help3 = graphics.newPixmap("help3.png", PixmapFormat.ARGB4444);
    Assets.numbers = graphics.newPixmap("numbers.png", PixmapFormat.ARGB4444);
    Assets.ready = graphics.newPixmap("ready.png", PixmapFormat.ARGB4444);
    Assets.pause = graphics.newPixmap("pause.png", PixmapFormat.ARGB4444);
    Assets.gameOver = graphics.newPixmap("gameover.png", PixmapFormat.ARGB4444);
    Assets.headUp = graphics.newPixmap("headup.png", PixmapFormat.ARGB4444);
    Assets.headLeft = graphics.newPixmap("headleft.png", PixmapFormat.ARGB4444);
    Assets.headDown = graphics.newPixmap("headdown.png", PixmapFormat.ARGB4444);
    Assets.headRight = graphics.newPixmap("headright.png", PixmapFormat.ARGB4444);
    Assets.tail = graphics.newPixmap("tail.png", PixmapFormat.ARGB4444);
    Assets.stain1 = graphics.newPixmap("stain1.png", PixmapFormat.ARGB4444);
    Assets.stain2 = graphics.newPixmap("stain2.png", PixmapFormat.ARGB4444);
    Assets.stain3 = graphics.newPixmap("stain3.png", PixmapFormat.ARGB4444);

    Assets.click = audio.newSound("click.ogg");
    Assets.eat = audio.newSound("eat.ogg");
    Assets.bitten = audio.newSound("bitten.ogg");

    Settings.load(game.getFileIO());
    game.setScreen(new MainMenuScreen(game));
  }

  public void present(float deltaTime) {
  }

  public void pause() {
  }

  public void resume() {
  }

  public void dispose() {
  }
}
