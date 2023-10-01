import java.util.Scanner;

public class TikTakToeGame {
    public static void main(String[] args) {
        //Zwei Spieler Objekte erstellen Spielerinitialisierung
        Player player1 = new Player("Spieler 1",  'X');
        Player player2 = new Player("Spieler 2", 'O');
        //Spielfeldinitialisierung Spielfeldobjekt mit Grösse 3x3 erstellen
        Board board = new Board(3);
        //
        Player currentPlayer = player1; // Der Spieler 1 beginnt

        while (true) {
            board.displayBoard();
            System.out.println(currentPlayer.getName() + ",  ist an der Reihe.");
            int row = getPlayerInput("Zeile");
            int col = getPlayerInput("Spalte");

            currentPlayer.makeMove(board, row, col);

            if (checkGameResult(board, currentPlayer)) {
                board.displayBoard();
                System.out.println(currentPlayer.getName() + " gewinnt! Herzlichen Glückwunsch!");
                break;
            } else if (board.isFull()) {
                board.displayBoard();
                System.out.println("Das Spiel endet unentschieden!");
                break;
            }

            // Spieler wechseln
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }

    // Hilfsmethode zur Eingabe einer Zeile oder Spalte von einem Spieler
    private static int getPlayerInput(String dimension) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print("Geben Sie die " + dimension + " (0, 1, 2) ein: ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0 && input <= 2) {
                    break;
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 0 und 2 ein.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 0 und 2 ein.");
                scanner.next(); // Leere den Scanner-Puffer
            }
        }
        return input;
    }

    // Überprüfe, ob der aktuelle Spieler gewonnen hat
    private static boolean checkGameResult(Board board, Player currentPlayer) {
        int size = board.getSize();
        char symbol = currentPlayer.getSymbol();

        // Überprüfe Reihen und Spalten
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < size; j++) {
                if (board.grid[i][j] != symbol) {
                    rowWin = false;
                }
                if (board.grid[j][i] != symbol) {
                    colWin = false;
                }
            }
            if (rowWin || colWin) {
                return true;
            }
        }

        // Überprüfe Diagonalen
        boolean diagonal1Win = true;
        boolean diagonal2Win = true;
        for (int i = 0; i < size; i++) {
            if (board.grid[i][i] != symbol) {
                diagonal1Win = false;
            }
            if (board.grid[i][size - 1 - i] != symbol) {
                diagonal2Win = false;
            }
        }
        return diagonal1Win || diagonal2Win;
    }
}

