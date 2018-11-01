package geneticAlgorithm;

public class Creature {
    private DNA dna;
    private int width;
    private int height;
    double fitness;

    public Creature(int width, int height) {
        this.width = width;
        this.height = height;
        this.dna = new DNA(width, height);
    }

    public Creature(int width, int height, DNA dna) {
        this.width = width;
        this.height = height;
        this.dna = dna;
    }

    public Creature mate(Creature other) {
        DNA childDNA = this.dna.splice(other.dna);
        return new Creature(this.width, this.height, childDNA);
    }

    public void testFitness(Runnable runnable) {
        this.fitness = runnable.getFitness(this.dna.getSequence());
    }

    public boolean[][] getSequence() {
        return this.dna.getSequence();
    }
}
