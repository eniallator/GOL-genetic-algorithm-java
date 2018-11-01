package geneticAlgorithm;

import java.util.Random;

public class DNA {
    private static Random rand = new Random();

    private boolean[] sequence;
    private double mutationChance;
    private int width;
    private int height;

    public DNA(int width, boolean[] sequence, double mutationChance) {
        this.sequence = sequence;
        this.mutationChance = mutationChance;
        this.width = width;
        this.height = this.sequence.length / width;
    }

    public DNA(int width, int height, double mutationChance) {
        this.sequence = new boolean[width*height];
        this.mutationChance = mutationChance;
        this.width = width;
        this.height = height;

        for (int i = 0; i < width * height; i++)
            sequence[i] = rand.nextBoolean();
    }

    public boolean[][] getSequence() {
        boolean[][] gridSequence = new boolean[this.height][this.width];

        for (int i = 0; i < this.height; i++)
            System.arraycopy(this.sequence, i * this.width, gridSequence[i], 0, this.width);

        return gridSequence;
    }

    public DNA splice(DNA other) {
        boolean[] newSequence = new boolean[this.sequence.length];

        for (int i = 0; i < this.sequence.length; i++) {
            boolean nextPiece = rand.nextBoolean() ? this.sequence[i] : other.sequence[i];
            newSequence[i] = (Math.random() < this.mutationChance) != nextPiece;
        }

        return new DNA(this.width, newSequence, this.mutationChance);
    }
}
