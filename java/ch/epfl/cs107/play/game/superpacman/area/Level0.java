package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends SuperPacmanArea {
  // Door nextLevel = new Door("superpacman/Level1",
  // SuperPacman.PLAYER_SPAWN_COORDINATES[1], null, area, Orientation.UP,
  // position)

  @Override
  public String getTitle() {
    return "superpacman/Level0";
  }

  @Override
  protected void createArea() {
    super.createArea();
    // TODO Auto-generated method stub

  }

}
