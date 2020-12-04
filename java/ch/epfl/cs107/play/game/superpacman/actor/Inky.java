package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Inky extends Ghost {

  public Inky(Area area, DiscreteCoordinates position, DiscreteCoordinates respawnPoint) {
    super(area, position, respawnPoint, "inky");
  }

  @Override
  protected Orientation getNextOrientation() {
    // TODO Auto-generated method stub
    return null;
  }

}
