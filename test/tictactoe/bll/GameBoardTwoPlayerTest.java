package tictactoe.bll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test Class. You can use it to validate that your GameBoardTwoPlayer class works as intended.
 * DO NOT modify the code in this class.
 */
class GameBoardTwoPlayerTest {

    /**
     * Test of getNextPlayer method, of class GameBoard.
     */
    @Test
    public void testGetNextPlayer()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 0;

        int result = instance.getNextPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextPlayer method, of class GameBoard.
     */
    @Test
    public void testGetNextPlayerAfterOneRound()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 1;

        instance.play(0, 0);
        int result = instance.getNextPlayer();

        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class GameBoard.
     */
    @Test
    public void testPlayAtZeroZero()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int col = 0;
        int row = 0;
        boolean expResult = true;

        boolean result = instance.play(col, row);

        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class GameBoard.
     */
    @Test
    public void testPlayAtOneOne()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int col = 1;
        int row = 1;
        boolean expResult = true;

        boolean result = instance.play(col, row);

        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class GameBoard.
     */
    @Test
    public void testPlayAtTakenSpot()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int col = 1;
        int row = 1;
        boolean expResult = false;

        instance.play(col, row); //Play once
        boolean result = instance.play(col, row); //Play same spot

        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class GameBoard.
     */
    @Test
    public void testIsGameOver()
    {
        IGameModel instance = new GameBoardTwoPlayer();

        instance.play(0, 0); //Player 0
        instance.play(1, 0); //Player 1
        instance.play(0, 1); //Player 0
        instance.play(2, 0); //Player 1
        instance.play(0, 2); //Player 0

        boolean expResult = true;
        boolean result = instance.isGameOver();
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @Test
    public void testGetWinnerPlayerZeroVeritcal()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 0;

        instance.play(0, 0); //Player 0
        instance.play(1, 0); //Player 1
        instance.play(0, 1); //Player 0
        instance.play(2, 0); //Player 1
        instance.play(0, 2); //Player 0

        int result = instance.getWinner();
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @Test
    public void testGetWinnerPlayerOneHorizontal()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 1;

        instance.play(1, 1); //Player 0
        instance.play(0, 0); //Player 1
        instance.play(0, 1); //Player 0
        instance.play(2, 0); //Player 1
        instance.play(1, 2); //Player 0
        instance.play(1, 0); //Player 1

        boolean isGameOver = instance.isGameOver();
        int result = instance.getWinner();
        assertTrue(isGameOver);
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @Test
    public void testGetWinnerDiagonally()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        boolean expResult = true;

        instance.play(0, 0); //Player 0
        instance.play(1, 0); //Player 1
        instance.play(1, 1); //Player 0
        instance.play(2, 0); //Player 1
        instance.play(2, 2); //Player 0

        boolean result = instance.isGameOver();
    }
}