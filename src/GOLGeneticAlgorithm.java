import GOL.GameOfLife;
import geneticAlgorithm.GeneticAlgorithm;

public class GOLGeneticAlgorithm {
    private GeneticAlgorithm genAlg;
    private CreatureRunLink runLink;
    private int iterations;

    public GOLGeneticAlgorithm(int width, int height, int iterations, double mutationChance, int numCreatures, int creaturesToKeep) {
        this.genAlg = new GeneticAlgorithm(width, height, mutationChance, numCreatures, creaturesToKeep);
        this.runLink = new CreatureRunLink();
        this.iterations = iterations;
    }

    public void cycleGenerations(int cycles) {
        this.genAlg.cycle((boolean[][] dna) -> {
            GameOfLife gol = new GameOfLife(dna);
            gol.cycle(this.iterations);
            return (double) gol.getAliveCells();
        }, cycles);
    }

    public String getStats() {
        String stats = this.genAlg.stats();
        boolean[][] bestDna = this.genAlg.getBestSequence();
        stats += this.runLink.createLink(bestDna);
        return stats;
    }
}
