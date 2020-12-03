package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.game.rpg.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea {
  Entity[] entities = new Entity[4];

  @Override
  public String getTitle() {
    return "superpacman/Level2";
  }

  @Override
  protected void createArea() {
    super.createArea();

    entities[0] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(5, 8));
    for (Entity ent : entities)
      registerActor(ent);
  }

}
