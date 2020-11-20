package ch.epfl.cs107.play.game.tutos.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;

public class Upgrade {
  private int tier;
  private String baseName = "";
  private Sprite sprite;
  private final int MAX_TIER = 3;

  public Upgrade(PowerupType type) {

  }

  public String getName(boolean shortVersion) {
    return baseName + " " + tier;
  }

  public int getTier() {
    return this.tier;
  }

  public void increaseTier(boolean max) {
    if (max)
      this.tier = MAX_TIER;
    else
      ++this.tier;
  }
}
