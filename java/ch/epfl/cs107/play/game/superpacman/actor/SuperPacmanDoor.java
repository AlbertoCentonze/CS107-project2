package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Arrays;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.area.LevelManager;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class SuperPacmanDoor extends Door {

  public SuperPacmanDoor(LevelManager levels, Logic signal, Area area, DiscreteCoordinates[] positions) {
    super(levels.getNextLevelName(), levels.getNextLevelSpawnCoordinates(), signal, area, Orientation.DOWN,
        positions[0], Arrays.copyOfRange(positions, 1, positions.length));
  }

}
