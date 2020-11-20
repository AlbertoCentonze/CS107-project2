package ch.epfl.cs107.play.game.tutos.actor;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
  private int health;
  private int startingHealth;
  public boolean isDead = false;
  public Map<Upgrade, Boolean> upgrades = new HashMap<Upgrade, Boolean>();

  public PlayerStats() {
    this.health = startingHealth;
  }

  public int getHealth() {
    return this.health;
  }

  private void setHealth(int value) {
    this.health = value;
  }

  public void takeDamage(int damage) {
    if (health <= 0) {
      isDead = true;
    }
    setHealth(damage);
  }

}
