package ch.epfl.cs107.play.math;

public class RandomGenerator {
  private static java.util.Random instance;

  public static java.util.Random getInstance() {
    if (instance == null)
      instance = new java.util.Random();
    return instance;
  }

  /**
   * Method that allows to return a weighted boolean
   * 
   * @param probability (float) of being true
   * @return a boolean with a certain probability of being true
   */
  public static boolean getRandomBoolean(float probability) {
    instance = new java.util.Random();
    return instance.nextFloat() < probability;
  }
}
