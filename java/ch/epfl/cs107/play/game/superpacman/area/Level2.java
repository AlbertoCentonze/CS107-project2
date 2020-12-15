package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;

public class Level2 extends SuperPacmanArea {
  public final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);
  List<Gate> gates;
  List<Key> keys;

  /** Default constructor for Level2 */
  public Level2() {
    super();
    gates = new ArrayList<Gate>();
    keys = new ArrayList<Key>();
  }

  @Override
  public String getTitle() {
    return "superpacman/Level2";
  }

  @Override
  protected void createArea() {
    super.createArea();

    keys.add(new Key(this, new DiscreteCoordinates(3, 16)));
    keys.add(new Key(this, new DiscreteCoordinates(26, 16)));
    keys.add(new Key(this, new DiscreteCoordinates(2, 8)));
    keys.add(new Key(this, new DiscreteCoordinates(27, 8)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(8, 14), keys.get(0)));
    gates.add(new Gate(this, Axis.VERTICAL, new DiscreteCoordinates(5, 12), keys.get(0)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(8, 10), keys.get(0)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(8, 8), keys.get(0)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(21, 14), keys.get(1)));
    gates.add(new Gate(this, Axis.VERTICAL, new DiscreteCoordinates(24, 12), keys.get(1)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(21, 10), keys.get(1)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(21, 8), keys.get(1)));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(10, 2), new And(keys.get(2), keys.get(3))));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(19, 2), new And(keys.get(2), keys.get(3))));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(12, 8), new And(keys.get(2), keys.get(3))));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(17, 8), new And(keys.get(2), keys.get(3))));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(14, 3), this));
    gates.add(new Gate(this, Axis.HORIZONTAL, new DiscreteCoordinates(15, 3), this));

    for (Gate gate : gates)
      registerActor(gate);
    for (Key key : keys)
      registerActor(key);
  }

  @Override
  public DiscreteCoordinates getRespawnPoint() {
    return PLAYER_SPAWN_POSITION;
  }
}
