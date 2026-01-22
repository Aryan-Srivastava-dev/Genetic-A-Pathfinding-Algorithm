package Genetic_AStar;
import java.util.Random;

public class DNA {
    double[] genes;
    double fitness;
    static Random rand = new Random();

    public DNA(int geneLength) {
        genes = new double[geneLength];
        for(int i=0; i<geneLength; i++)
            genes[i] = rand.nextDouble();
    }

    public DNA(DNA other) {
        this.genes = other.genes.clone();
        this.fitness = other.fitness;
    }

    public void mutate(double mutationRate) {
        for(int i=0; i<genes.length; i++) {
            if(rand.nextDouble() < mutationRate) {
                genes[i] += rand.nextGaussian() * 0.2;
                genes[i] = Math.max(0, Math.min(1, genes[i]));
            }
        }
    }
}
