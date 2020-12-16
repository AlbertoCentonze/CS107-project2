package ch.epfl.cs107.play;

import java.util.Scanner;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.hyperpacman.HyperPacman;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.io.DefaultFileSystem;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.swing.SwingWindow;

/**
 * Main entry point.
 */
public class Play {

  /** One second in nano second */
  private static final float ONE_SEC = 1E9f;

  /**
   * Main entry point.
   * 
   * @param args (Array of String): ignored
   */
  public static void main(String[] args) {

    // Define cascading file system
    final FileSystem fileSystem = new ResourceFileSystem(DefaultFileSystem.INSTANCE);

    // Selecting gamemode
    Scanner keyboard = new Scanner(System.in);
    String[] modeNames = { "SuperPacman", "HyperPacman" };
    Game[] modes = { new SuperPacman(), new HyperPacman() };
    System.out.println("Welcome into my game, please select a gamemode");
    for (int i = 0; i < modeNames.length; ++i)
      System.out.println((i + 1) + ". " + modeNames[i]);

    int selectedMode;
    do {
      System.out.println("Enter the number of the desired gamemode");
      selectedMode = keyboard.nextInt() - 1;
    } while (selectedMode < 0 || selectedMode > modes.length);
    keyboard.close();

    // final Game game = new SuperPacman();
    final Game game = modes[selectedMode];

    // Use Swing display
    final Window window = new SwingWindow(game.getTitle(), fileSystem, 550, 550);
    window.registerFonts(ResourcePath.FONTS);

    try {

      if (game.begin(window, fileSystem)) {
        // recorder.start();
        // replayer.start("record1.xml");

        // Use system clock to keep track of time progression
        long currentTime = System.nanoTime();
        long lastTime;
        final float frameDuration = ONE_SEC / game.getFrameRate();

        // Run until the user try to close the window
        while (!window.isCloseRequested()) {

          // Compute time interval
          lastTime = currentTime;
          currentTime = System.nanoTime();
          float deltaTime = (currentTime - lastTime);

          try {
            int timeDiff = Math.max(0, (int) (frameDuration - deltaTime));
            Thread.sleep((int) (timeDiff / 1E6), (int) (timeDiff % 1E6));
          } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted");
          }

          currentTime = System.nanoTime();
          deltaTime = (currentTime - lastTime) / ONE_SEC;

          // Let the game do its stuff
          game.update(deltaTime);

          // Render and update input
          window.update();
          // recorder.update();
          // replayer.update();
        }
      }
      // recorder.stop("record1.xml");
      game.end();

    } finally {
      // Release resources
      window.dispose();
    }
  }

}
