package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea {
  private static final String NEXT_LEVEL = "superpacman/Level1";
  public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(5, 1);

  Door exitDoor;
  Key key;
  List<Gate> gates;

  /** Default constructor for Level0 */
  public Level0() {
    gates = new ArrayList<Gate>();
  }

  @Override
  public String getTitle() {
    return "superpacman/Level0";
  }

  @Override
  protected void createArea() {
    super.createArea();

    // Instantiate all the actors
    key = new Key(this, new DiscreteCoordinates(3, 4));
    exitDoor = new Door(NEXT_LEVEL, Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP,
        new DiscreteCoordinates(5, 9), new DiscreteCoordinates(6, 9));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(5, 8), key));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(6, 8), key));
    // Register all the actors
    for (Entity ent : new Entity[] { key, exitDoor })
      registerActor(ent);
    for (Entity ent : gates)
      registerActor(ent);
  }

  @Override
  public DiscreteCoordinates getRespawnPoint() {
    return PLAYER_SPAWN_POSITION;
  }
}
