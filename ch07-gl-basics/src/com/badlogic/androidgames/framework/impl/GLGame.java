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
  Screen screen;
  GLGameState state = GLGameState.Initialized;
  Object stateChanged = new Object();
  long startTime = System.nanoTime();
  WakeLock  wakeLock;

}

