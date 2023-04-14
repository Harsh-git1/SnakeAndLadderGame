package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordintates;
    ArrayList<Integer> snakeLadderPosition;

    public Board(){
        positionCoordintates = new ArrayList<>();
        populatePositionCoordinate();
        populateSnakeLadder();
    }


    public void populatePositionCoordinate(){

        //add dummy value
        positionCoordintates.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeAndLadder.height; i++) {
            for (int j = 0; j < SnakeAndLadder.width; j++) {

                int xCord = 0;
                if(i%2 == 0) {
                     xCord = j*SnakeAndLadder.tileSize + SnakeAndLadder.tileSize/2;
                }else {
                     xCord = SnakeAndLadder.tileSize*(SnakeAndLadder.height - j) - SnakeAndLadder.tileSize/2;
                }
                int yCord = SnakeAndLadder.tileSize*(SnakeAndLadder.height - i) - SnakeAndLadder.tileSize/2;

                positionCoordintates.add(new Pair<>(xCord, yCord));
            }

        }
    }

    private void populateSnakeLadder() {
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4, 25);
        snakeLadderPosition.set(13, 46);
        snakeLadderPosition.set(27, 5);
        snakeLadderPosition.set(33, 49);
        snakeLadderPosition.set(40, 3);
        snakeLadderPosition.set(42, 63);
        snakeLadderPosition.set(43, 18);
        snakeLadderPosition.set(50, 69);
        snakeLadderPosition.set(54, 31);
        snakeLadderPosition.set(62, 81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(76, 58);
        snakeLadderPosition.set(89, 53);
        snakeLadderPosition.set(99, 41);
    }
    public int getnewPosition(int currPosition){
        if(currPosition >= 1 && currPosition<= 100) {
            return snakeLadderPosition.get(currPosition);
        }
        return -1;
    }

    public int getXCoordinate(int position) {
        if(position >= 1 && position <= 100) {
            return positionCoordintates.get(position).getKey();
        }
        return -1;
    }

    public int getYCoordinate(int position) {
        if(position >= 1 && position <= 100) {
            return positionCoordintates.get(position).getValue();
        }
        return -1;
    }

}

