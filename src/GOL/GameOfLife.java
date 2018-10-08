package GOL;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLife {
    private ArrayList<int[]> cells;

    public GameOfLife(boolean[][] startingCells) {
        cells = new ArrayList<>();

        for (int i = 0; i < startingCells.length; i++) {
            for (int j = 0; j < startingCells[i].length; j++) {
                if (startingCells[i][j]) {
                    int[] coords = {j, i};
                    cells.add(coords);
                }
            }
        }
    }

    private int getAliveNeighbours(int[] currCell) {
        int aliveNeighbours = 0;
        for (int[] otherCell : this.cells)
            if (!Arrays.equals(currCell, otherCell) && Math.abs(currCell[0] - otherCell[0]) <= 1 && Math.abs(currCell[1] - otherCell[1]) <= 1)
                aliveNeighbours ++;

        return aliveNeighbours;
    }

    private boolean[][] getDeadNeighbours(int[] currCell) {
        boolean[][] deadNeighbours = new boolean[3][3];

        for (boolean[] row : deadNeighbours)
            Arrays.fill(row, true);

        deadNeighbours[1][1] = false;

        for (int[] otherCell : this.cells) {
            int[] diff = {otherCell[0] - currCell[0], otherCell[1] - currCell[1]};

            if (Math.abs(diff[0]) <= 1 && Math.abs(diff[1]) <= 1)
                deadNeighbours[diff[0] + 1][diff[1] + 1] = false;
        }

        return deadNeighbours;
    }

    private void stackDeadCells(ArrayList<int[]> deadCells, boolean[][] deadNeighbours, int[] currCell) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (deadNeighbours[j + 1][i + 1]) {
                    int[] currEntry = {currCell[0] + j, currCell[1] + i, 1};
                    boolean added = false;

                    for (int[] deadCell : deadCells) {
                        if (deadCell[0] == currEntry[0] && deadCell[1] == currEntry[1]) {
                            added = true;
                            deadCell[2]++;
                        }
                    }

                    if (!added)
                        deadCells.add(currEntry);
                }
            }
        }
    }

    public void cycle() {
        ArrayList<int[]> nextGeneration = new ArrayList<>();
        ArrayList<int[]> deadCells = new ArrayList<>();

        for (int[] currCell : this.cells) {
            int aliveNeighbours = this.getAliveNeighbours(currCell);
            boolean[][] deadNeighbours = this.getDeadNeighbours(currCell);
            this.stackDeadCells(deadCells, deadNeighbours, currCell);

            if (aliveNeighbours >= 2 && aliveNeighbours <= 3)
                nextGeneration.add(currCell);
        }

        for (int[] currCell : deadCells) {
            if (currCell[2] == 3) {
                int[] cell = {currCell[0], currCell[1]};
                nextGeneration.add(cell);
            }
        }

        this.cells = nextGeneration;
    }

    public void cycle(int times) {
        for (int i = 0; i < times; i++)
            this.cycle();
    }

    public String toString(ArrayList<int[]> cells) {
        StringBuilder builder = new StringBuilder();

        for (int[] cell : cells)
            builder.append(Arrays.toString(cell));

        return builder.toString();
    }

    public String toString() {
        return this.toString(this.cells);
    }
}
