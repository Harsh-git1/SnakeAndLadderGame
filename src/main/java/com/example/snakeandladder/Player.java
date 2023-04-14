package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private Circle coin;
    private int currentPoisiton;

    private String  name;
    private Board newBoard = new Board();
    public Player(int tileSize, Color coinColor,String PlayerName) {
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPoisiton = 0;
        movePlayer(1);
        name = PlayerName;
    }

    public void movePlayer(int diceValue) {
       if(currentPoisiton+diceValue <= 100) {
           currentPoisiton += diceValue;
           TranslateTransition secondAnimation = null, firstAnimation = translateAnimation(diceValue);

           int newPositon = newBoard.getnewPosition(currentPoisiton);
           if(newPositon != currentPoisiton && newPositon != -1) {
               currentPoisiton = newPositon;
               secondAnimation = translateAnimation(6);
           }

           if(secondAnimation == null) {
               firstAnimation.play();
           }else {
               SequentialTransition sequentialTransition = new SequentialTransition(firstAnimation,
                       new PauseTransition(Duration.millis(500)) , secondAnimation);
               sequentialTransition.play();
           }
       }
    }

    private TranslateTransition translateAnimation(int diceValue) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(diceValue*250), coin);
        animate.setToX(newBoard.getXCoordinate(currentPoisiton));
        animate.setToY(newBoard.getYCoordinate(currentPoisiton));
        animate.setAutoReverse(false);
        return animate;
    }
    public void startingPosition() {
        currentPoisiton = 0;
        movePlayer(1);
    }
    boolean isWinner() {
        if(currentPoisiton == 100) {
            return true;
        }
        return false;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPoisiton() {
        return currentPoisiton;
    }

    public String getName() {
        return name;
    }
}
