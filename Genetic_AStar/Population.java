package Genetic_AStar;
import java.util.*;

public class Population {
    int geneLength;
    double mutationRate;
    List<DNA> members;
    static Random rand = new Random();
    double optimalCost;

    public Population(int size, int geneLength, double mutationRate) {
        this.geneLength = geneLength;
        this.mutationRate = mutationRate;
        members = new ArrayList<>();
        for(int i=0; i<size; i++) {
            members.add(new DNA(geneLength));
        }

        Node start = new Node(0, 0);
        Node end = new Node(AStar.rows-1, AStar.cols-1);
        Result res = AStar.search(start, end, true, new double[]{0,0}, false);
        this.optimalCost = res.cost;
        System.out.println("Optimal Cost (Dijkstra): " + this.optimalCost);
    }

    public void evaluate() {
        for(DNA member: members) {
            Node s = new Node(0, 0);
            Node g = new Node(AStar.rows-1, AStar.cols-1);

            Result res = AStar.search(s, g, false, member.genes, false);

            member.fitness = Fitness.evaluate(res.nodesExpanded, res.solved, res.cost, this.optimalCost);
        }
    }

    public DNA selectParent() {
        DNA best = null;

        for(int i=0; i<3; i++) {
            DNA candidate = members.get(rand.nextInt(members.size()));
            if(best == null || candidate.fitness > best.fitness)
                best = candidate;
        }

        return best;
    }

    public DNA crossover(DNA p1, DNA p2) {
        int splitPoint = rand.nextInt(geneLength);
        DNA child = new DNA(geneLength);

        for(int i=0; i<geneLength; i++) {
            child.genes[i] = (i < splitPoint) ? p1.genes[i] : p2.genes[i];
        }

        return child;
    }

    public DNA getBest() {
        DNA best = members.getFirst();

        for(DNA member: members) {
            if(member.fitness > best.fitness)
                best = member;
        }

        return best;
    }

    public void evolve() {
        List<DNA> nextGen = new ArrayList<>();

        nextGen.add(new DNA(getBest()));

        while(nextGen.size() < members.size()) {
            DNA p1 = selectParent();
            DNA p2 = selectParent();
            DNA child = crossover(p1, p2);
            child.mutate(mutationRate);
            nextGen.add(child);
        }

        members = nextGen;
    }
}
