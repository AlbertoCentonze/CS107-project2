package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;

public class Level2 extends SuperPacmanArea {
  Entity[] entities = new Entity[4];

  public Level2(LevelManager levels) {
    super(levels);
  }

  @Override
  public String getTitle() {
    return "superpacman/Level2";
  }

  @Override
  protected void createArea() {
    super.createArea();

    // entities[0] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(5, 8));
    // for (Entity ent : entities)
    // registerActor(ent);
  }

}
