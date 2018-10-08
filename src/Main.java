import GOL.GameOfLife;

public class Main {
    public static void main(String[] args) {
            boolean[][] startingCells = {
                {false, true, false},
                {true, true, false},
                {false, true, true}
        };

        GameOfLife gol = new GameOfLife(startingCells);

        System.out.println(gol.toString());

        gol.cycle();

        System.out.println(gol.toString());
    }
}