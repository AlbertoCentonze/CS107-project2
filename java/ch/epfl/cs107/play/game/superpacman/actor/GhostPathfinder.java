package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class GhostPathfinder extends Ghost {
  final static int SIGHT_RADIUS = 5;

  public GhostPathfinder(Area area, DiscreteCoordinates position, String ghostType) {
    super(area, position, ghostType);
  }

  // abstract public DiscreteCoordinates getTarget();

}
