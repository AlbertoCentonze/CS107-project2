package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.game.areagame.actor.Axis;

public class Level1 extends SuperPacmanArea {
  private static final String NEXT_LEVEL = "superpacman/Level2";
  public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);

  Door nextLevel;
  Gate[] gates = new Gate[2];

  @Override
  public String getTitle() {
    return "superpacman/Level1";
  }

  @Override
  protected void createArea() {
    super.createArea();

    nextLevel = new Door(NEXT_LEVEL, Level2.PLAYER_SPAWN_COORDINATES, Logic.TRUE, this, Orientation.DOWN,
        new DiscreteCoordinates(14, 0), new DiscreteCoordinates(15, 0));
    registerActor(nextLevel);
    // gates[0] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(14, 2));
    // gates[1] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(15, 2));
    // for (Entity ent : entities)
    // registerActor(ent);
  }

  @Override
  public float getCameraScaleFactor() {
    return 30.f;
  }

}
