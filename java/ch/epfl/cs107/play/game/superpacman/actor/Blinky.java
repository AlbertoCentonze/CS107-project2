package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Blinky extends Ghost {

  public Blinky(Area area, DiscreteCoordinates position) {
    super(area, position, "blinky");
  }

  @Override
  protected Orientation getNextOrientation() {
    return Orientation.pickRandomly();
  }

}
