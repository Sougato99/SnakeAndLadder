package com.example.snakeandladdermarch;

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

public class SnakeLadder extends Application {

    public static final int tileSize = 40, width = 10, height = 10, buttonLine = height * tileSize + 50, infoLine = height * tileSize + 20;


    Player playerFirst, playerSecond;

    boolean firstPlayerTurn = true, gameStart = false;
    int diceValue;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize + 100);

        //100 tiles on UI

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(i * tileSize);
                tile.setTranslateY(j * tileSize);
                root.getChildren().addAll(tile);
            }
        }
        Image img = new Image("C:\\Users\\SOUGATO KAR\\IdeaProjects\\SnakeAndLadderMarch\\src\\img.png");
        ImageView boardImage = new ImageView();
        boardImage.setFitWidth(width * tileSize);
        boardImage.setFitHeight(height * tileSize);
        boardImage.setImage(img);

        root.getChildren().addAll(boardImage);

        //Buttons and Info
        Button startButton = new Button("Start");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonLine);

        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(10);
        playerOneButton.setTranslateY(buttonLine);

        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);

        Label diceLabel = new Label("Start the Game");
        diceLabel.setTranslateX(180);
        diceLabel.setTranslateY(infoLine);


        //Players
        playerFirst = new Player("Sougato", Color.BLACK, tileSize/2);
        playerSecond = new Player("Russell", Color.ANTIQUEWHITE, tileSize/2-5);

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(firstPlayerTurn){
                        diceValue = rollDice();
                        diceLabel.setText("Dice" + diceValue);
                        playerFirst.movePlayer(diceValue);
                        firstPlayerTurn = !firstPlayerTurn;
                        if(playerFirst.checkWinner()){
                            diceLabel.setText("Winner is " + playerFirst.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn = true;
                            gameStart = false;
                        }
                    }
                }
            }
        });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(!firstPlayerTurn){
                        diceValue = rollDice();
                        diceLabel.setText("Dice" + diceValue);
                        playerSecond.movePlayer(diceValue);
                        firstPlayerTurn = !firstPlayerTurn;
                        if(playerSecond.checkWinner()){
                            diceLabel.setText("Winner is " + playerSecond.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn = true;
                            gameStart = false;
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart = true;
                startButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();
            }
        });


        root.getChildren().addAll(startButton, playerOneButton, playerTwoButton, diceLabel, playerFirst.getCoin(), playerSecond.getCoin());


        return root;
    }


    private int rollDice(){
        return (int) (Math.random()*6+1);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}