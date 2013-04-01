package com.badlogic.androidgames.framework.impl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;

public abstract class GLGame extends Activity implements Game, Renderer {
  enum GLGameState {
    Initialized,
    Running,
    Paused,
    Finished,
    Idle
  }

  GLSurfaceView glView;
  GLGraphics glGraphics;
  Audio audio;
  Input input;
  FileIO fileIO;
  Screen screen;
  GLGameState state = GLGameState.Initialized;
  Object stateChanged = new Object();
  long startTime = System.nanoTime();
  WakeLock  wakeLock;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
    glView = new GLSurfaceView(this);
    glView.setRenderer(this);
    setContentView(glView);

    glGraphics = new GLGraphics(glView);
    fileIO = new AndroidFileIO(this);
    audio = new AndroidAudio(this);
    // We no longer scale as in AndroidGame
    input = new AndroidInput(this, glView, 1, 1);
    PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
    wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
  }

  public void onResume() {
    super.onResume();
    glView.onResume();
    wakeLock.acquire();
  }

  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    glGraphics.setGL(gl);

    // Using an object as lock, but could have used the GLGame instance
    // or a proper lock as well.
    synchronized (stateChanged) {
      if (state == GLGameState.Initialized) {
        screen = getStartScreen();
      }
      state = GLGameState.Running;
      screen.resume();
      startTime = System.nanoTime();
    }
  }

  public void onSurfaceChanged(GL10 gl, int width, int height) {
    // Nothing to do here
  }

  public void onDrawFrame(GL10 gl) {
    GLGameState state = null;

    synchronized(stateChanged) {
      state = this.state;
    }

    switch (state) {
      case Running:
        float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
        startTime = System.nanoTime();
        screen.update(deltaTime);
        screen.present(deltaTime);
        break;
      case Paused:
        screen.pause();
        synchronized (stateChanged) {
          this.state = GLGameState.Idle;
          stateChanged.notifyAll();
        }
        break;
      case Finished:
        screen.pause();
        screen.dispose();
        synchronized (stateChanged) {
          this.state = GLGameState.Idle;
          stateChanged.notifyAll();
        }
        break;
      default:
        break;
    }
  }

  public void onPause() {
    synchronized (stateChanged) {
      state = isFinishing() ? GLGameState.Finished : GLGameState.Paused;
      while(true) {
        try {
          stateChanged.wait();
          break;
        } catch(InterruptedException e) {
          continue;
        }
      }
    }
    wakeLock.release();
    glView.onPause();
    super.onPause();
  }

  public GLGraphics getGLGraphics() {
    return glGraphics;
  }

  public Input getInput() {
    return input;
  }

  public FileIO getFileIO() {
    return fileIO;
  }

  public Graphics getGraphics() {
    throw new IllegalStateException("We are using OpenGL!");
  }
  
  public Audio getAudio() {
    return audio;
  }
  
  public void setScreen(Screen screen){
    if (screen == null){
      throw new IllegalArgumentException("Screen must not be null");
    }
    this.screen.pause();
    this.screen.dispose();
    screen.resume();
    screen.update(0);
    this.screen = screen;
  }
  
  public Screen getCurrentScreen() {
    return screen;
  }
}
