package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea {
  Door nextLevel;
  Key key;

  @Override
  public String getTitle() {
    return "superpacman/Level0";
  }

  @Override
  protected void createArea() {
    super.createArea();

    key = new Key(this, new DiscreteCoordinates(3, 4));
    nextLevel = new Door("superpacman/Level1", SuperPacman.PLAYER_SPAWN_COORDINATES[1], Logic.TRUE, this,
        Orientation.UP, new DiscreteCoordinates(5, 9), new DiscreteCoordinates(6, 9));
    registerActor(nextLevel);
    registerActor(key);
  }

}
