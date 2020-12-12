package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.game.areagame.actor.Background;

public class Level1 extends SuperPacmanArea {
  private static final String NEXT_LEVEL = "superpacman/Level2";
  public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);

  Door exitDoor;
  Gate[] gates = new Gate[2];

  @Override
  public String getTitle() {
    return "superpacman/Level1";
  }

  @Override
  protected void createArea() {
    super.createArea();
    registerActor(new Background(this));

    exitDoor = new Door(NEXT_LEVEL, Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN,
        new DiscreteCoordinates(14, 0), new DiscreteCoordinates(15, 0));
    registerActor(exitDoor);
    gates[0] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(14, 2), this);
    gates[1] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(15, 2), this);

    for (Gate gate : gates)
      registerActor(gate);
  }

  @Override
  public float getCameraScaleFactor() {
    return 40.f;
  }

  public DiscreteCoordinates getRespawnPoint() {
    return PLAYER_SPAWN_POSITION;
  }

}
