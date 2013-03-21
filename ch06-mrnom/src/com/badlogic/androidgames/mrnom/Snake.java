package com.badlogic.androidgames.mrnom;

import java.util.ArrayList;
import java.util.List;

public class Snake {
  public static final int UP = 0;
  public static final int LEFT = 1;
  public static final int DOWN = 2;
  public static final int RIGHT = 3;

  public List<SnakePart> parts = new ArrayList<SnakePart>();

  public int direction;

  public Snake() {
    direction = UP;
    parts.add(new SnakePart(5, 6));
    parts.add(new SnakePart(5, 7));
    parts.add(new SnakePart(5, 8));
  }

  public void turnLeft() {
    direction += 1;
    if (direction > RIGHT){
      direction = UP;
    }
  }

  public void turnRight() {
    direction -= 1;
    if (direction < UP) {
      direction = RIGHT;
    }
  }

  public void eat() {
    SnakePart end = parts.get(parts.size() - 1);
    parts.add(new SnakePart(end.x, end.y));
  }

  private int bound(int position, int min, int max){
    if (position < min) {
      return max;
    }

    if (position > max) {
      return min;
    }

    return position;
  }

  public void advance() {
    SnakePart head = parts.get(0);

    // add the new part
    for (int i = parts.size() - 1; i > 0; i--) {
      SnakePart before = parts.get(i - 1);
      SnakePart part = parts.get(i);
      part.x = before.x;
      part.y = before.y;
    }

    // move the head according to direction
    switch(direction) {
      case UP:
        head.y -= 1;
        break;
      case LEFT:
        head.x -= 1;
        break;
      case DOWN:
        head.y += 1;
        break;
      case RIGHT:
        head.x += 1;
        break;
      default:
    	break;
    }

    // ensure the snake is still in the world bounds
    head.x = bound(head.x, 0, 9);
    head.y = bound(head.y, 0, 12);
  }

  public boolean checkBitten() {
    int len = parts.size();
    SnakePart head = parts.get(0);
    for (int i = 1; i < len; i++) {
      SnakePart part = parts.get(i);
      if (part.x == head.x && part.y == head.y) {
        return true;
      }
    }
    return false;
  }
}
