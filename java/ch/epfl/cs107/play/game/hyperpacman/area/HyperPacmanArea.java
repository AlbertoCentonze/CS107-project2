package ch.epfl.cs107.play.game.hyperpacman.area;

import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;

public class HyperPacmanArea extends SuperPacmanArea {
  @Override
  protected void createArea() {
    ((HyperPacmanBehavior) behavior).generateMaze(this);
  }

  @Override
  public String getTitle() {
    return "random";
  }

  @Override
  public float getCameraScaleFactor() {
    return 40;
  }

}
