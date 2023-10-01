public class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    // Methode, um einen Zug auf dem Spielfeld zu machen
    public void makeMove(Board board, int row, int col) {
        if (board.isEmptyCell(row, col)) {
            board.makeMove(row, col, symbol);
        } else {
            System.out.println("Ungültiger Zug. Das Feld ist bereits belegt.");
        }
    }
}
