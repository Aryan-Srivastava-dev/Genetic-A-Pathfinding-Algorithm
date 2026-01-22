package Genetic_AStar;

public class Result {
    public int nodesExpanded;
    public double cost;
    public boolean solved;

    public Result(int nodesExpanded, double cost, boolean solved) {
        this.nodesExpanded = nodesExpanded;
        this.cost = cost;
        this.solved = solved;
    }
}
