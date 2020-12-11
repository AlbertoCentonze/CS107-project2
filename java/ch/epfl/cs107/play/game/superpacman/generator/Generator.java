package ch.epfl.cs107.play.game.superpacman.generator;

import java.util.List;
import java.util.ArrayList;

public class Generator {
  public static void main(String[] args) {
    Generator mazeGenerator = new Generator(5);
    boolean[][] maze = mazeGenerator.getMaze();
    for (int x = 0; x < maze.length; ++x) {
      System.out.println();
      for (int y = 0; y < maze[x].length; ++y) {
        System.out.print(maze[x][y] ? "W" : "O");
      }
    }
    System.out.println();
    System.out.println("done");
  }

  int size;
  Node[][] grid;

  public Generator(int size) {
    this.size = size;
    this.grid = new Node[size][size];
    for (int x = 0; x < this.size; x++) {
      for (int y = 0; y < this.size; y++) {
        this.grid[x][y] = new Node(x, y);
      }
    }
  }

  public boolean[][] getMaze() {
    Node start = this.grid[(int) Math.floor(Math.random() * this.size)][(int) Math.floor(Math.random() * this.size)];
    start.visited = true;
    Node current = start;
    while (true) {
      // Hunt
      List<Node> neighbors = current.getNeighbors(this.grid, this.size);
      if (neighbors.size() == 0) {
        boolean found = false;

        hunt: for (int y = 0; y < size; y++) {
          for (int x = 0; x < size; x++) {
            Node currentNode = grid[x][y];
            if (!currentNode.visited) {
              neighbors = currentNode.getVisitedNeighbors(this.grid, this.size);
              if (neighbors.size() != 0) {
                found = true;
                Node neighbor = neighbors.get((int) Math.floor(Math.random() * neighbors.size()));
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
        Node next = neighbors.get((int) Math.floor(Math.random() * neighbors.size()));
        current.addChildren(next);
        current = next;
        grid[current.x][current.y].visited = true;
        current.visited = true;
      }
    }
  }

  public boolean[][] buildMaze() {
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
          for (Node child : grid[x][y].children) {
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

  class Node {
    private int x;
    private int y;
    private boolean visited;
    private List<Node> children;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
      this.visited = false;
      this.children = new ArrayList<>();
    }

    public void addChildren(Node node) {
      children.add(node);
    }

    public List<Node> getNeighbors(Node[][] grid, int size) {
      List<Node> neighbors = new ArrayList<Node>();
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

    public List<Node> getVisitedNeighbors(Node[][] grid, int size) {
      List<Node> neighbors = new ArrayList<Node>();
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
}
