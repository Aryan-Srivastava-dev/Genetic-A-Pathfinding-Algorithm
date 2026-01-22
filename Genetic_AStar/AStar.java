package Genetic_AStar;
import java.util.*;

public class AStar {
    static final int rows = 100;
    static final int cols = 100;

    static int[][] grid = generateComplexGrid(100, 0.3);

    static int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public static Result search(Node start, Node goal, boolean isDijkstra, double[] genes, boolean print) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        boolean[][] closedSet = new boolean[rows][cols];
        Node[][] nodes = new Node[rows][cols];

        int nodesExpanded = 0;

        start.g = 0;
        start.h = calculateHeuristic(start, goal, genes);
        start.calculate();
        openSet.add(start);
        nodes[start.x][start.y] = start;

        while(!openSet.isEmpty()) {
            Node current = openSet.poll();

            if(closedSet[current.x][current.y]) continue;

            if(current.x == goal.x && current.y == goal.y) {
                if(print)
                    printPath(current);
                return new Result(nodesExpanded, current.g, true);
            }

            closedSet[current.x][current.y] = true;
            nodesExpanded++;

            for(int[] dir: directions) {
                int nextX = current.x + dir[0];
                int nextY = current.y + dir[1];

                if(!isValid(nextX, nextY) || closedSet[nextX][nextY])
                    continue;

                if(dir[0] != 0 && dir[1] != 0) {
                    if(grid[current.x][nextY] == 1 || grid[nextX][current.y] == 1)
                        continue;
                }

                double moveCost = (dir[0] != 0 && dir[1] != 0) ? Math.sqrt(2) : 1;
                double tentativeG = current.g + moveCost;

                Node neighbour = nodes[nextX][nextY];
                if(neighbour == null) {
                    neighbour = new Node(nextX, nextY);
                    nodes[nextX][nextY] = neighbour;
                }

                if(tentativeG < neighbour.g) {
                    neighbour.g = tentativeG;
                    neighbour.h = isDijkstra ? 0 : calculateHeuristic(neighbour, goal, genes);
                    neighbour.calculate();
                    neighbour.parent = current;

                    openSet.add(neighbour);
                }
            }
        }
        return new Result(nodesExpanded, 0, false);
    }

    public static double calculateHeuristic(Node first, Node second, double[] genes) {
        double manhattanDist = genes[0] * (Math.abs(first.x - second.x) + Math.abs(first.y - second.y));

        double distX = Math.pow(first.x - second.x, 2);
        double distY = Math.pow(first.y - second.y, 2);
        double euclideanDist = genes[1] * Math.sqrt(distX + distY);

        return manhattanDist + euclideanDist;
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 0;
    }

    public static int[][] generateComplexGrid(int size, double obstacleDensity) {
        int[][] newGrid = new int[size][size];
        Random r = new Random(42);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (r.nextDouble() < obstacleDensity) {
                    newGrid[i][j] = 1;
                } else {
                    newGrid[i][j] = 0;
                }
            }
        }

        newGrid[0][0] = 0;
        newGrid[size - 1][size - 1] = 0;

        return newGrid;
    }

    public static void printPath(Node node) {
        List<Node> path = new ArrayList<>();

        while(node != null) {
            path.add(node);
            node = node.parent;
        }

        Collections.reverse(path);
        for(Node n: path) {
            System.out.println("(" + n.x + ", " + n.y + ")");
            grid[n.x][n.y] = 7;
        }

        for(int i=0; i<rows; i++) {
            for(int j = 0; j<cols; j++)
                System.out.print(grid[i][j] + "\t");
            System.out.println();
        }
    }
}
