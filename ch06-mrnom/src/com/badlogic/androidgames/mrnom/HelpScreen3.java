package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class HelpScreen3 extends Screen {
  public HelpScreen3(Game game) {
    super(game);
  }

  public void update(float deltaTime) {
    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    game.getInput().getKeyEvents();

    int len = touchEvents.size();
    for (int i = 0; i < len; i++) {
      TouchEvent event = touchEvents.get(i);
      if (event.type == TouchEvent.TOUCH_UP) {
        if (event.x > 256 && event.y > 416) {
          game.setScreen(new MainMenuScreen(game));
          playClick();
        }
        return;
      }
    }
  }

  private void playClick() {
    if (Settings.soundEnabled) {
      Assets.click.play(1);
    }
  }

  public void present(float deltaTime) {
    Graphics graphics = game.getGraphics();
    graphics.drawPixmap(Assets.background, 0, 0);
    graphics.drawPixmap(Assets.help3, 64, 100);
    graphics.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
  }

  public void pause() {
  }

  public void resume() {
  }

  public void dispose() {
  }

}
