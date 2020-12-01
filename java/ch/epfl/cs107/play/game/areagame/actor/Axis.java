package ch.epfl.cs107.play.game.areagame.actor;

public enum Axis {
  HORIZONTAL, VERTICAL;

  public Orientation toOrientation() {
    switch (this) {
      case HORIZONTAL:
        return Orientation.RIGHT;
      case VERTICAL:
        return Orientation.UP;
      default:
        return Orientation.RIGHT;

    }
  }
}
