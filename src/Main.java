import GOL.GameOfLife;
import geneticAlgorithm.DNA;
import geneticAlgorithm.GeneticAlgorithm;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GOLGeneticAlgorithm genAlg = new GOLGeneticAlgorithm(5, 5, 30, 0.025, 50, 3);

        genAlg.cycleGenerations(50);

        System.out.println(genAlg.getStats());
    }
}