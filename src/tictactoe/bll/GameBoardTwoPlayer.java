package tictactoe.bll;

import java.util.Arrays;

/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {

    private int currentPlayer;
    private int[][] cordsPlayed;
    private int roundsPlayed;
    private int winPlayer;

    protected GameBoardTwoPlayer() {
        resetGame();
    }

    public void resetGame(){
        currentPlayer = 0;
        roundsPlayed = 0;
        winPlayer = -1;

        cordsPlayed = new int[3][3];
        for (int[] row : cordsPlayed){
            Arrays.fill(row, -1);
        }
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {
        //TODO Implement this method * DONE *
        return (currentPlayer == 0 ? 0 : 1);


    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) {
        //TODO Implement this method * DONE *
        int n = cordsPlayed[row][col];
        if(this.isGameOver()){
            return false;
        }
        if (n == -1){
            cordsPlayed[row][col] = currentPlayer;

            if (currentPlayer == 0) {
                currentPlayer = 1;
            } else {
                currentPlayer = 0;
            }
            roundsPlayed++;
            return true;
        } else {
            return false;
        }




    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    @Override
    public boolean isGameOver() {
        //TODO Implement this method *DONE*
        if (roundsPlayed < 9) {
            if (checkDiag() == -1 && checkHor() == -1 && checkVert() == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        //TODO Implement this method *DONE*
        checkWinners();
        return winPlayer;
    }

    private void checkWinners() {
        checkHor();
        checkVert();
        checkDiag();
    }

    private int checkDiag() {
        if ((cordsPlayed[0][0] == 0 && cordsPlayed[1][1] == 0 && cordsPlayed[2][2] == 0) || (cordsPlayed[0][0] == 1 && cordsPlayed[1][1] == 1 && cordsPlayed[2][2] == 1)){
            winPlayer = cordsPlayed[0][0];
            return winPlayer;
        } else if ((cordsPlayed[2][0] == 0 && cordsPlayed[1][1] == 0 && cordsPlayed[0][2] == 0) || (cordsPlayed[2][0] == 1 && cordsPlayed[1][1] == 1 && cordsPlayed[0][2] == 1)){
            winPlayer = cordsPlayed[2][0];
            return winPlayer;
        } else {
            return -1;
        }
    }

    private int checkVert() {
        boolean gameWon = false;
        for (int i = 0; i < cordsPlayed.length; i++) {
            if ((cordsPlayed[0][i] == 0 && cordsPlayed[1][i] == 0 && cordsPlayed[2][i] == 0)
                    || (cordsPlayed[0][i] == 1 && cordsPlayed[1][i] == 1 && cordsPlayed[2][i] == 1)) {
                gameWon = true;
                winPlayer = cordsPlayed[0][i];
            }
        }
        if (gameWon) {
            return winPlayer;
        } else {
            return -1;
        }
    }

    private int checkHor() {
        boolean gameWon = false;
        for (int[] row: cordsPlayed) {
            if ((row[0] == 0 && row[1] == 0 && row[2] == 0) || (row[0] == 1 && row[1] == 1 && row[2] == 1)) {
                gameWon = true;
                winPlayer = row[0];
            }
        }
        if(gameWon) {
            return winPlayer;
        } else {
            return -1;
        }
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        //TODO Implement this method * DONE*
        resetGame();

    }

}