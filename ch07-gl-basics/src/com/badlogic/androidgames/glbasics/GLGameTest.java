package com.badlogic.androidgames.glbasics;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class GLGameTest extends GLGame {
  public Screen getStartScreen() {
    return new TestScreen(this);
  }
  
  class TestScreen extends Screen {
    GLGraphics glGraphics;
    Random rand = new Random();
    
    public TestScreen(Game game) {
      super(game);
      glGraphics = ((GLGame) game).getGLGraphics();
    }
    
    public void present(float deltaDime) {
      GL10 gl = glGraphics.getGL();
      gl.glClearColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
      gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
    
    public void update(float deltaTime){
    }
    
    public void pause(){
    }
    
    public void resume() {
    }
    
    public void dispose(){
    }
  }
}
