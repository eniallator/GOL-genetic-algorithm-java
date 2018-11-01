import GOL.GameOfLife;
import geneticAlgorithm.GeneticAlgorithm;

public class GOLGeneticAlgorithm {
    private GeneticAlgorithm genAlg;

    public GOLGeneticAlgorithm(int width, int height, int numCreatures, int creaturesToKeep) {
        this.genAlg = new GeneticAlgorithm(width, height, numCreatures, creaturesToKeep);
    }

    public void cycleGenerations(int cycles) {
        this.genAlg.cycle((boolean[][] dna) -> {
            GameOfLife gol = new GameOfLife(dna);
            gol.cycle(cycles);
            return (double) gol.getAliveCells();
            }, cycles);
    }

    public String getStats() {
        return this.genAlg.stats();
    }
}
