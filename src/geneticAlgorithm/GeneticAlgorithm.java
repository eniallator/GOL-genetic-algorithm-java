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

    public void cycle(Runnable runnable) {
        if (this.genNum > 0)
            this.population.newGeneration();
        this.population.testCreatures(runnable);
        this.genNum ++;
    }

    public void cycle(Runnable runnable, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Gen: " + this.genNum);
            this.cycle(runnable);
        }
    }

    public String stats() {
        StringBuilder builder = new StringBuilder();

        builder.append("Generation #" + this.genNum + "\r\n");
        builder.append(this.population.getStats());

        return builder.toString();
    }

    public boolean[][] getBestSequence() {
        return this.population.getBest();
    }
}
