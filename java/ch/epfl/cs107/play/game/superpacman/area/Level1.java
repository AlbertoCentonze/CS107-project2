package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.game.areagame.actor.Axis;

public class Level1 extends SuperPacmanArea {
  private static final String NEXT_LEVEL = "superpacman/Level2";
  public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);

  Door exitDoor;
  List<Gate> gates;

  /** Default constructor for Level2 */
  public Level1() {
    gates = new ArrayList<>();
  }

  @Override
  public String getTitle() {
    return "superpacman/Level1";
  }

  @Override
  protected void createArea() {
    super.createArea();

    exitDoor = new Door(NEXT_LEVEL, Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN,
        new DiscreteCoordinates(14, 0), new DiscreteCoordinates(15, 0));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(14, 2), this));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(15, 2), this));
    for (Gate gate : gates)
      registerActor(gate);
    registerActor(exitDoor);
  }

  @Override
  public DiscreteCoordinates getRespawnPoint() {
    return PLAYER_SPAWN_POSITION;
  }

}
