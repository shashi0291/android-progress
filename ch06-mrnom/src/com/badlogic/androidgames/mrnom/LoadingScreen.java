package com.badlogic.androidgames.mrnom;

import badlogic.androidgames.framework.Game;
import badlogic.androidgames.framework.Graphics;
import badlogic.androidgames.framework.Screen;
import badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
  public LoadingScreen(Game game) {
    super(game);
  }

  @Override
  public void update(float deltaTime) {
    Graphics g = game.getGraphics();
    Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
    Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB444);
    Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
    Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
    Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
    Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
    Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
    Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
    Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
    Assets.pause = g.newPixmap("pause.png", PixmapFormat.ARGB4444);
    Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
    Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
    Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
    Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
    Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
    Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
    Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
    Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
    Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
    Assets.click = g.newPixmap("click.png", PixmapFormat.ARGB4444);
    Assets.eat = g.newPixmap("eat.png", PixmapFormat.ARGB4444);
    Assets.bitten = g.newPixmap("bitten.png", PixmapFormat.ARGB4444);
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