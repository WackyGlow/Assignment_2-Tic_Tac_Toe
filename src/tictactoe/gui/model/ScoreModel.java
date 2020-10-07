package tictactoe.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tictactoe.gui.controller.TicTacViewController;

public class ScoreModel {

    private ObservableList<String> winners;
    private int p0WinAmount;
    private int p1WinAmount;

    public ScoreModel() {
        winners = FXCollections.observableArrayList();
        p0WinAmount = 0;
        p1WinAmount = 0;
    }

    public ObservableList<String> getWinners() {

        return winners;
    }

    public void setNextWinner(String winner) {
        //TODO Implement this method.
        switch (winner){

            case "X":
                p0WinAmount++;
                if (p0WinAmount == 1){
                    getWinners().add("Player0: " + p0WinAmount + " Win!");
                } else {
                    getWinners().add("Player0: " + p0WinAmount + " Wins!");
                }
                break;
            case "O":
                p1WinAmount++;
                if (p1WinAmount == 1){
                    getWinners().add("Player1: " + p1WinAmount + " Win!");
                } else {
                    getWinners().add("Player1: " + p1WinAmount + " Wins!");
                }
                break;
        }
    }

}