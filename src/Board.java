public class Board {
    char[][] grid;
    private int size;
    private int moves;

    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        initializeBoard();
    }

    // Initialisiere das Spielfeld
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    // Platziere das Symbol eines Spielers auf dem Spielfeld
    public void makeMove(int row, int col, char symbol) {
        grid[row][col] = symbol;
        moves++;
    }

    // Überprüfe, ob das Spielfeld an der gegebenen Position leer ist
    public boolean isEmptyCell(int row, int col) {
        return grid[row][col] == ' ';
    }

    // Überprüfe, ob das Spielfeld voll ist (Unentschieden)
    public boolean isFull() {
        return moves == size * size;
    }

    // Anzeige des aktuellen Spielfelds
    public void displayBoard() {
        System.out.println("Spielfeld:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j]);
                if (j < size - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < size - 1) {
                for (int k = 0; k < size * 4 - 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }

    // Getter-Methode für die Spielfeldgröße
    public int getSize() {
        return size;
    }
}
