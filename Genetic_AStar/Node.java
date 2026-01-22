package Genetic_AStar;

public class Node implements Comparable<Node> {
    public int x;
    public int y;
    public double g;
    public double h;
    public double f;
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.h = 0;
        this.f = Double.MAX_VALUE;
        this.parent = null;
    }

    public void calculate() {
        this.f = this.g + this.h;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.f, other.f);
    }
}
