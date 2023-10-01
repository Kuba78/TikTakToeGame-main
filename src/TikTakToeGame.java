import java.util.Scanner;

public class TikTakToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Zwei Spieler Objekte erstellen Spielerinitialisierung
        Player player1 = new Player("Spieler 1", 'X');
        Player player2 = new Player("Spieler 2", 'O');

        // Spielfeldinitialisierung Spielfeldobjekt mit Größe 3x3 erstellen
        Board board = new Board(3);



        Player currentPlayer = player1; // Der Spieler 1 beginnt

        while (true) {
            board.displayBoard();
            System.out.println(currentPlayer.getName() + ", ist an der Reihe.");
            int move = getPlayerInput(currentPlayer);

            int row = (move - 1) / board.getSize();
            int col = (move - 1) % board.getSize();


            if (board.isEmptyCell(row, col)) {
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
            } else {
                System.out.println("Dieses Feld ist bereits belegt. Bitte wählen Sie ein anderes Feld.");
            }
        }

        scanner.close(); // Den Scanner schließen, um Ressourcen freizugeben
    }

    // Hilfsmethode zur Eingabe einer Nummer für den Spielzug eines Spielers
    private static int getPlayerInput(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print(currentPlayer.getName() + ", geben Sie die Nummer des Feldes (1 bis 9) ein: ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= 9) {
                    break;
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 9 ein.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 9 ein.");
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
