package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea {
  Door nextLevel;

  @Override
  public String getTitle() {
    return "superpacman/Level1";
  }

  @Override
  protected void createArea() {
    super.createArea();

    nextLevel = new Door("superpacman/Level2", SuperPacman.PLAYER_SPAWN_COORDINATES[1], Logic.TRUE, this,
        Orientation.DOWN, new DiscreteCoordinates(14, 0), new DiscreteCoordinates(15, 0));
    registerActor(nextLevel);
  }

}
