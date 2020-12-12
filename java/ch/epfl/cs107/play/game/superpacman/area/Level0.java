package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea {
  private static final String NEXT_LEVEL = "superpacman/Level1";
  public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(5, 1);

  Door exitDoor;
  Key key;
  Gate[] gates = new Gate[2];

  @Override
  public float getCameraScaleFactor() {
    return 15.f;
  }

  @Override
  public String getTitle() {
    return "superpacman/Level0";
  }

  @Override
  protected void createArea() {
    super.createArea();

    key = new Key(this, new DiscreteCoordinates(3, 4));
    exitDoor = new Door(NEXT_LEVEL, Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP,
        new DiscreteCoordinates(5, 9), new DiscreteCoordinates(6, 9));
    gates[0] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(5, 8), key);
    gates[1] = new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(6, 8), key);
    for (Entity ent : new Entity[] { key, exitDoor })
      registerActor(ent);
    for (Entity ent : gates)
      registerActor(ent);
  }

  public DiscreteCoordinates getRespawnPoint() {
    return PLAYER_SPAWN_POSITION;
  }
}
