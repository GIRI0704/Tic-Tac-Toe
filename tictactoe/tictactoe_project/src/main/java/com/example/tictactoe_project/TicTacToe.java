package com.example.tictactoe_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class TicTacToe extends Application {
    private int player1_score = 0;
    private int player2_score = 0;
    private boolean player1_Turn = true;
    private Button buttons[][] = new Button[3][3];
    private Label player1, player2;
    private BorderPane createContent()
    {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        // title
        Label title = new Label("Tic Tac Toe");
        title.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        // board
        GridPane gridpane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(150,150);
                button.setStyle("-fx-font-size : 30pt; -fx-font-weight : bold");
                button.setOnAction(event->ButtonClicked(button));
                buttons[i][j] = button;
                gridpane.add(button,j,i);
            }
        }
        root.setCenter(gridpane);
        gridpane.setAlignment(Pos.CENTER);
        // score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        player1 = new Label("Player_1 : 0");
        player1.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        player2 = new Label("Player_2 : 0");
        player2.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        scoreBoard.getChildren().addAll(player1,player2);
        root.setBottom(scoreBoard);
        return root;
    }
    private void ButtonClicked(Button button)
    {
        if(button.getText().equals(""))
        {
            if(player1_Turn) button.setText("X");
            else button.setText("O");
            player1_Turn = !player1_Turn;
            checkWinner();
        }
        return;
    }

    private void checkWinner()
    {
        // checking row
        boolean flag = false;
        for(int row = 0; row < 3; row++)
        {
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) && buttons[row][1].getText().equals(buttons[row][2].getText()) && !buttons[row][0].getText().isEmpty())
            {
                String winner = buttons[row][0].getText();
                flag = true;
                AnnounceWinner(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // checking column
        for(int column = 0; column < 3; column++)
        {
            if(buttons[0][column].getText().equals(buttons[1][column].getText()) && buttons[1][column].getText().equals(buttons[2][column].getText()) && !buttons[0][column].getText().isEmpty())
            {
                String winner = buttons[0][column].getText();
                AnnounceWinner(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // check diagonal top to bottom
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().isEmpty())
        {
            String winner = buttons[0][0].getText();
            AnnounceWinner(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        // check diagonal bottom to top
        if(buttons[2][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[0][2].getText()) && !buttons[2][0].getText().isEmpty())
        {
            String winner = buttons[2][0].getText();
            AnnounceWinner(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        // check for match drawn
        boolean tie = true;
        for(Button row[] : buttons)
        {
            for(Button button : row)
            {
                if(button.getText().isEmpty())
                    tie = false;
            }
        }
        if(tie)
        {
            String winner = "tie";
            AnnounceWinner(winner);
            resetBoard();
            return;
        }

    }

    private void AnnounceWinner(String winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(winner.equals("tie"))
            alert.setContentText("Match has drawn");
        else if(winner.equals("X"))
            alert.setContentText("Congratulations player_1 You are the winner of the game");
        else if(winner.equals("O"))
            alert.setContentText("Congratulations player_2 You are the winner of the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner)
    {
        if(winner.equals("X"))
        {
            player1_score++;
            player1.setText("player_1 : "+player1_score);
        }
        else
        {
            player2_score++;
            player2.setText(("player_2 : "+player2_score));
        }

    }

    private void resetBoard()
    {
        for(Button row[] : buttons)
        {
            for(Button button : row)
            {
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}