package Genetic_AStar;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int geneLength = 2;
        int popSize = 100;
        double mutationRate = 0.06;
        int gen = 1;

        Population pop = new Population(popSize, geneLength, mutationRate);

        while (gen <= 100) {
            pop.evaluate();
            System.out.println("Generation: " + gen);
            System.out.println("Total Nodes Expanded: " + 1.0/pop.getBest().fitness);
            System.out.println("Best Weights: " + Arrays.toString(pop.getBest().genes));
            gen++;
            pop.evolve();
        }

        Node start = new Node(0, 0);
        Node end = new Node(AStar.rows-1, AStar.cols-1);
        AStar.search(start, end, false, pop.getBest().genes, true);
    }
}
