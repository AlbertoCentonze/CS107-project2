package ch.epfl.cs107.play.game.areagame.actor;

public enum Axis {
  // Enumeration elements
  HORIZONTAL, VERTICAL;

  /**
   * Method to get the corresponding orientation
   * 
   * @return (Orientation): right if horizontal, up if vertical
   */
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
