import java.util.logging.Logger;
import java.util.Scanner;

public class App {
    static int row;
    static int col;
    static char currentPlayer;
    static char[][] board = { { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' } };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Logger logger = Logger.getLogger("MyLoger");
        System.out.print("Choose which player are you X or O  - ");
        String input = scanner.next();
        currentPlayer = input.charAt(0);
        currentPlayer = Character.toUpperCase(currentPlayer);
        boolean gameOver = false;

        displayBoard();
        while (!gameOver) {
            logger.info("Player " + currentPlayer + ", enter row and column (e.g., 1 2): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if (isValidMove()) {
                board[row][col] = currentPlayer;
                if (isWin() && isDraw()) {
                    System.out.println("\n\n\tPlayers " + currentPlayer + " wins!");
                    gameOver = true;
                    continue;
                }
                displayBoard();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
            // scanner.close();
        }
    }

    static void displayBoard() {

        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    static boolean isValidMove() {
        // Check if the row and column are within the valid range (0-2)
        if (row > 2 || row < 0 || col > 2 || col < 0) {
            System.out.println("Invalid range!");
            return false;
        }
        // check if cell is occupied
        if (board[row][col] != ' ') {
            System.out.println("Cell is already occupied");
            return false;
        }
        // invalid move
        return true;
    }

    static boolean isWin() {
        // check for the diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        // check for all the rows
        for (row = 0; row < 3; row++) {
            if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
                return true;
            }
        }
        // check for all the columns
        for (col = 0; col < 3; col++) {
            if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    static boolean isDraw() {
        for (row = 0; row < 3; row++) {
            for (col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
