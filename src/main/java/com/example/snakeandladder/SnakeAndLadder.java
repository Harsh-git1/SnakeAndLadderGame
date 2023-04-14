package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class SnakeAndLadder extends Application {
    public static final int tileSize = 40, height = 10 , width = 10;
    public static final int buttonLine = tileSize*height + 50, infoLine = buttonLine - 30;

    private static Dice dice = new Dice();
    private Player playerOne , playerTwo;
    private boolean gameStart = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        for(int i=0; i<height; i++) {
              for(int j=0; j<width; j++) {
                  Tile tile = new Tile(tileSize);
                  tile.setTranslateX(j*tileSize);
                  tile.setTranslateY(i*tileSize);
                  root.getChildren().add(tile);
              }
        }

        Image img  = new Image("E:\\SnakeAndLadder\\src\\main\\images (7).jpeg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);

        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start The Game");

        playerOne = new Player(tileSize, Color.BLACK, "Raunak");
        playerTwo = new Player(tileSize-6, Color.RED, "Harsh");

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart) {
                    if(playerOneTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerOne.movePlayer(diceValue);

                        //winning condition
                        if(playerOne.isWinner()) {
                            playerOneTurn = false;
                            playerTwoTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("ReStart");
                            diceLabel.setText(playerOne.getName() + " is Winner");
                            diceLabel.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
                            startButton.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
                            gameStart = false;
                        }else {
                            playerOneTurn = false;
                            playerTwoTurn = true;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText(playerTwo.getName() + "'s turn");
                        }


                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart) {
                    if(playerTwoTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);

                        //Winning Condition
                        if(playerTwo.isWinner()) {
                            diceLabel.setText("And the Winner is " + playerTwo.getName() );
                            playerTwoTurn = false;
                            playerOneTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("ReStart");
                            diceLabel.setText(playerTwo.getName() + " is Winner");
                            diceLabel.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
                            startButton.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
                            gameStart = false;
                        }else {
                            playerTwoTurn = false;
                            playerOneTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText(playerOne.getName() + "'s turn");
                        }

                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                diceLabel.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
                playerOneTurn = true;

                playerOneLabel.setText(playerOne.getName() + "'s turn");
                playerOneButton.setDisable(false);
                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);

                playerOne.startingPosition();
                playerTwo.startingPosition();
            }
        });
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerOneLabel.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        playerTwoLabel.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);
        diceLabel.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");

        root.getChildren().addAll(board, playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel, playerOne.getCoin(), playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}