package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea {
  Door nextLevel;
  Key key;
  Entity[] entities = new Entity[4];

  @Override
  public String getTitle() {
    return "superpacman/Level0";
  }

  @Override
  protected void createArea() {
    super.createArea();

    entities[0] = new Key(this, new DiscreteCoordinates(3, 4));
    entities[1] = new Door("superpacman/Level1", SuperPacman.PLAYER_SPAWN_COORDINATES[1], Logic.TRUE, this,
        Orientation.UP, new DiscreteCoordinates(5, 9), new DiscreteCoordinates(6, 9));
    entities[2] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(5, 8));
    entities[3] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(6, 8));
    for (Entity ent : entities)
      registerActor(ent);
  }

}
