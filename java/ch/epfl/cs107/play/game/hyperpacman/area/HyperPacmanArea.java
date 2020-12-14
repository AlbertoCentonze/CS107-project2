package ch.epfl.cs107.play.game.hyperpacman.area;

import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class HyperPacmanArea extends SuperPacmanArea {
  @Override
  protected void createArea() {
  }

  @Override
  public String getTitle() {
    return "random";
  }

  @Override
  public float getCameraScaleFactor() {
    return 40;
  }

  @Override
  public DiscreteCoordinates getRespawnPoint() {
    // TODO Auto-generated method stub
    return null;
  }

}
