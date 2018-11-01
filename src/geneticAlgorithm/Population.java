package geneticAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Population {
    private int width;
    private int height;
    private int creaturesToKeep;
    private Creature[] creatures;

    public Population(int width, int height, int numCreatures, int creaturesToKeep) {
        this.width = width;
        this.height = height;
        this.creaturesToKeep = creaturesToKeep;
        this.creatures = new Creature[numCreatures];

        for (int i = 0; i < numCreatures; i++) {
            this.creatures[i] = new Creature(width, height);
        }
    }

    private static Comparator<Creature> creatureComparator = new Comparator<Creature>() {
        @Override
        public int compare(Creature a, Creature b) {
            return Double.compare(a.fitness, b.fitness);
        }
    };

    public Population(int width, int height, int creaturesToKeep, Creature[] creatures) {
        this.width = width;
        this.height = height;
        this.creaturesToKeep = creaturesToKeep;
        this.creatures = creatures;
    }

    private Creature[] getOldBest() {
        Creature[] nextGen = new Creature[this.creatures.length];
        System.arraycopy(creatures, 0, nextGen, 0, this.creaturesToKeep);
        return nextGen;
    }

    private int chooseMate(double totalWeight, int index) {
        double weightChosen = Math.random() * totalWeight;
        double weightSoFar = 0.0f;

        while (weightSoFar < weightChosen) {
            index = (index + 1) % this.creatures.length;

            double currFitness = this.creatures[index].fitness;
            weightSoFar += currFitness;
            if (weightSoFar > weightChosen) {
                return index;
            }
        }

        return -1;
    }

    private Creature[] getMatingPair() {
        Creature[] matingPair = new Creature[2];
        Arrays.fill(matingPair, null);

        double totalWeight = 0.0f;
        for (Creature creature : this.creatures)
            totalWeight += creature.fitness;

        int creatureIndex = 0;

        for (int i = 0; i < 2; i++) {
            creatureIndex = this.chooseMate(totalWeight, creatureIndex);
            matingPair[i] = this.creatures[creatureIndex];
            totalWeight -= this.creatures[creatureIndex].fitness;
        }

        return matingPair;
    }

    public void testCreatures(Runnable runnable) {
        for (Creature creature : this.creatures)
            creature.testFitness(runnable);
    }

    public void newGeneration() {
        Arrays.sort(this.creatures, creatureComparator);
        Creature[] nextGen = this.getOldBest();

        for (int i = this.creaturesToKeep; i < this.creatures.length; i++) {
            Creature[] matingPair = this.getMatingPair();
            nextGen[i] = matingPair[0].mate(matingPair[1]);
        }

        this.creatures = nextGen;
    }

    public String getStats() {
        Arrays.sort(this.creatures, creatureComparator);
        StringBuilder builder = new StringBuilder();

        builder.append("Total creatures: " + this.creatures.length + "\r\n");
        builder.append("Highest fitness: " + this.creatures[this.creatures.length - 1].fitness + "\r\n");
        builder.append("Lowest fitness: " + this.creatures[0].fitness + "\r\n");

        double avg = 0.0f;

        for (Creature creature : this.creatures)
            avg += creature.fitness / this.creatures.length;

        builder.append("Average fitness: " + avg + "\r\n");

        builder.append("Best Creature:\r\n");

        for (boolean[] row : this.creatures[this.creatures.length - 1].getSequence()) {
            builder.append('|');
            for (boolean piece : row) {
                if (piece)
                    builder.append('#');
                else
                    builder.append(' ');
            }
            builder.append("|\r\n");
        }

        return builder.toString();
    }
}
