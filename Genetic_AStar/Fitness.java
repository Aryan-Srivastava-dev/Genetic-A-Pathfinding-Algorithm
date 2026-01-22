package Genetic_AStar;

public class Fitness {
    static public double evaluate(int nodesExpanded, boolean isSolved, double totalCost, double optimalCost) {
        if(!isSolved || totalCost > optimalCost + 1e-6)
            return 1e-6;

        return (1.0 / nodesExpanded);
    }
}
