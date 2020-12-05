package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class LevelManager {
  private int currentLevel = 0;
  List<LevelInfo> levels;

  public LevelManager(String[] levelNames, DiscreteCoordinates[] playerSpawnPoints) {
    levels = new ArrayList<>();
    for (int i = 0; i < levelNames.length; ++i) {
      levels.add(new LevelInfo(levelNames[i], i, playerSpawnPoints[i]));
    }
  }

  public String getLevelName(int index) {
    return levels.get(index).levelName;
  }

  public DiscreteCoordinates getLevelSpawnCoordinates(int index) {
    return levels.get(index).playerSpawnPoint;
  }

  public LevelInfo nextLevel() {
    ++currentLevel;
    return levels.get(currentLevel);
  }

  public class LevelInfo {
    String levelName;
    DiscreteCoordinates playerSpawnPoint;
    // super usefull if for example you want to add a timer

    public LevelInfo(String name, int index, DiscreteCoordinates playerSpawnPoint) {
      this.levelName = name;
      this.playerSpawnPoint = playerSpawnPoint;
    }
  }

}
