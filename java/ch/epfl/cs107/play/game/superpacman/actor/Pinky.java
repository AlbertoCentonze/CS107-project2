package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends GhostPathfinder {

  public Pinky(Area area, DiscreteCoordinates position) {
    super(area, position, "pinky");
  }

  @Override
  protected Orientation getNextOrientation() {
    // TODO Auto-generated method stub
    return Orientation.UP;
  }

  @Override
  protected Queue<Orientation> getPathScared() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Queue<Orientation> getPathWaiting() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Queue<Orientation> getPathChasing() {
    // TODO Auto-generated method stub
    return null;
  }

}
