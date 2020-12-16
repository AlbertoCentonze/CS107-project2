package ch.epfl.cs107.play.game.hyperpacman.generator;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Hunt and kill maze generating algorithm (translated from a good javascript
 * implementation and adapted to OOP (using the nodes as an inner class)). You
 * can find the link to the original source in the README
 */
public class Generator {
  int size;
  GeneratorNode[][] grid;

  /**
   * Generator default constructor
   * 
   * @param size (int):
   */
  public Generator(int size) {
    this.size = size / 2;
    this.grid = new GeneratorNode[size][size];
    for (int x = 0; x < this.size; x++) {
      for (int y = 0; y < this.size; y++) {
        this.grid[x][y] = new GeneratorNode(x, y);
      }
    }
  }

  /**
   * Use the hunt and kill algorithm to build the maze
   * 
   * @return (boolean[][]) 2D array containing the walls as true and free space as
   *         false
   */
  public boolean[][] getMaze() {
    GeneratorNode start = this.grid[(int) Math.floor(Math.random() * this.size)][(int) Math
        .floor(Math.random() * this.size)];
    start.visited = true;
    GeneratorNode current = start;
    while (true) {
      // Hunt
      List<GeneratorNode> neighbors = current.getNeighbors(this.grid, this.size);
      if (neighbors.size() == 0) {
        boolean found = false;

        hunt: for (int y = 0; y < size; y++) {
          for (int x = 0; x < size; x++) {
            GeneratorNode currentNode = grid[x][y];
            if (!currentNode.visited) {
              neighbors = currentNode.getVisitedNeighbors(this.grid, this.size);
              if (neighbors.size() != 0) {
                found = true;
                GeneratorNode neighbor = neighbors.get((int) Math.floor(Math.random() * neighbors.size()));
                current = currentNode;
                grid[current.x][current.y].visited = true;
                grid[neighbor.x][neighbor.y].addChildren(current);
                break hunt;
              }
            }
          }
        }
        if (!found)
          return buildMaze();

      } else {
        // Kill
        GeneratorNode next = neighbors.get((int) Math.floor(Math.random() * neighbors.size()));
        current.addChildren(next);
        current = next;
        grid[current.x][current.y].visited = true;
        current.visited = true;
      }
    }
  }

  /**
   * Converts the nodes into a boolean[][]
   * 
   * @return (boolean[][]) 2D array containing the walls as true and free space as
   *         false
   */
  private boolean[][] buildMaze() {
    boolean[][] maze = new boolean[this.size * 2 + 1][this.size * 2 + 1];
    for (int x = 0; x < this.size * 2 + 1; x++) {
      for (int y = 0; y < this.size * 2 + 1; y++) {
        maze[x][y] = true;
      }
    }

    for (int x = 0; x < this.size; x++) {
      for (int y = 0; y < this.size; y++) {
        if (this.grid[x][y].visited) {
          maze[x * 2 + 1][y * 2 + 1] = false;
          for (GeneratorNode child : grid[x][y].children) {
            if (child.x < x) {
              maze[x * 2][y * 2 + 1] = false;
            }
            if (child.x > x) {
              maze[x * 2 + 2][y * 2 + 1] = false;
            }
            if (child.y < y) {
              maze[x * 2 + 1][y * 2] = false;
            }
            if (child.y > y) {
              maze[x * 2 + 1][y * 2 + 2] = false;
            }
          }
        }
      }
    }
    return maze;
  }

  /** Inner class that defines a Node for the generator */
  private class GeneratorNode {
    private int x;
    private int y;
    private boolean visited;
    private List<GeneratorNode> children;

    /**
     * Default constructor for a Node
     * 
     * @param x (int): x-coordinate of the node
     * @param y (int): y-coordinate of the node
     */
    GeneratorNode(int x, int y) {
      this.x = x;
      this.y = y;
      this.visited = false;
      this.children = new ArrayList<>();
    }

    /**
     * Adds a children node
     * 
     * @param node (GeneratorNode): the children to add
     */
    public void addChildren(GeneratorNode node) {
      children.add(node);
    }

    /**
     * Method to compute the neighbors of a node
     * 
     * @param grid
     * @param size
     * @return
     */
    public List<GeneratorNode> getNeighbors(GeneratorNode[][] grid, int size) {
      List<GeneratorNode> neighbors = new ArrayList<GeneratorNode>();
      if (this.x > 0 && !grid[this.x - 1][this.y].visited) {
        neighbors.add(grid[this.x - 1][this.y]);
      }
      if (this.x < size - 1 && !grid[this.x + 1][this.y].visited) {
        neighbors.add(grid[this.x + 1][this.y]);
      }
      if (this.y > 0 && !grid[this.x][this.y - 1].visited) {
        neighbors.add(grid[this.x][this.y - 1]);
      }
      if (this.y < size - 1 && !grid[this.x][this.y + 1].visited) {
        neighbors.add(grid[this.x][this.y + 1]);
      }
      return neighbors;
    }

    public List<GeneratorNode> getVisitedNeighbors(GeneratorNode[][] grid, int size) {
      List<GeneratorNode> neighbors = new ArrayList<GeneratorNode>();
      if (this.x > 0 && grid[this.x - 1][this.y].visited) {
        neighbors.add(grid[this.x - 1][this.y]);
      }
      if (this.x < size - 1 && grid[this.x + 1][this.y].visited) {
        neighbors.add(grid[this.x + 1][this.y]);
      }
      if (this.y > 0 && grid[this.x][this.y - 1].visited) {
        neighbors.add(grid[this.x][this.y - 1]);
      }
      if (this.y < size - 1 && grid[this.x][this.y + 1].visited) {
        neighbors.add(grid[this.x][this.y + 1]);
      }
      return neighbors;
    }
  }

  public static void arrayToImage(boolean[][] data) {
    int height = data.length;
    int width = data[0].length;
    BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (data[i][j]) {
          outImage.setRGB(j, i, 0x00000000);
        } else {
          outImage.setRGB(j, i, 0xffffffff);
        }
      }
    }
    try {
      // Save as PNG
      File file = new File("res/images/behaviors/superpacman/random.png");
      ImageIO.write(outImage, "png", file);
    } catch (IOException e) {
    }
  }
}
