package geneticAlgorithm;

public class GeneticAlgorithm {
    private Population population;
    private int genNum;

    public GeneticAlgorithm(int width, int height, double mutationChance, int numCreatures, int creaturesToKeep) {
        this.population = new Population(width, height, mutationChance, numCreatures, creaturesToKeep);
        this.genNum = 0;
    }

    public int getGenNum() {
        return this.genNum;
    }

    public void cycle(GenAlgRunnable runnable) {
        if (this.genNum > 0)
            this.population.newGeneration();
        this.population.testCreatures(runnable);
        this.genNum ++;
    }

    public void cycle(GenAlgRunnable runnable, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Gen: " + this.genNum);
            this.cycle(runnable);
        }
    }

    public String stats() {
        return "Generation #" + this.genNum + "\r\n" + this.population.getStats();
    }

    public boolean[][] getBestSequence() {
        return this.population.getBest();
    }
}
