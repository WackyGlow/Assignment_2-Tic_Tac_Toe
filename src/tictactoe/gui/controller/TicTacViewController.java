/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import tictactoe.bll.GameBoardFactory;
import tictactoe.bll.IGameModel;
import tictactoe.gui.model.ScoreModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Stegger
 */
public class TicTacViewController implements Initializable {
    @FXML
    private ChoiceBox<GameBoardFactory.GAME_MODE> choicePlayMode;

    @FXML
    private ListView lstScores;

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    /**
     * The prefix text that is shown before the actual player who's turn it is.
     */
    private static final String TXT_PLAYER = "Player: ";

    private GameBoardFactory.GAME_MODE currentGameMode;
    private IGameModel game;
    private ScoreModel scoreModel;

    /**
     * Initialize method is called at construction time AFTER the constructor is called, and after all GUI controls are created.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scoreModel = new ScoreModel();
        lstScores.setItems(scoreModel.getWinners());
        choicePlayMode.getItems().addAll(GameBoardFactory.GAME_MODE.values());
        choicePlayMode.getSelectionModel().selectLast();
        currentGameMode = choicePlayMode.getSelectionModel().getSelectedItem();

        game = GameBoardFactory.getGameModel(currentGameMode);
        setPlayer();
    }

    /**
     * Event handler that is called whenever a Player clicks a button in the game field.
     *
     * @param event The Button click event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r)) {
                Button btn = (Button) event.getSource();
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    scoreModel.setNextWinner(xOrO);
                } else {
                    setPlayer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Eventhandler that is called whenever the players want's to start a new game.
     * The method will switch game mode if the player has chosen so.
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        if (currentGameMode == choicePlayMode.getSelectionModel().getSelectedItem()) {
            game.newGame();
        } else {
            currentGameMode = choicePlayMode.getSelectionModel().getSelectedItem();
            game = GameBoardFactory.getGameModel(currentGameMode);
        }
        setPlayer();
        clearBoard();
    }

    /**
     * Updates the label displaying who's turn it is.
     */
    private void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }

    /**
     * Updates the label to display the winner of the game.
     *
     * @param winner
     */
    private void displayWinner(int winner) {
        String message = "";
        switch (winner) {
            case -1:
                message = "It's a draw";
                break;
            default:
                message = "Player " + winner + " wins!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the board so that all Buttons of the board do NOT display anything.
     */
    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

}
