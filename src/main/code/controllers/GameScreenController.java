package main.code.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.code.Main;

import java.io.IOException;
import java.util.ArrayList;



public class GameScreenController {

    public TextField newPlayer;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public ListView playerListBox;
    public Text currentPlayer;
    public Text currentPlayer1;
    public ImageView cardImageView;

    @FXML
    public void initialize() throws IOException {
        Image img =  Main.game.getTopOfCardDeck();
        cardImageView.setImage(img);

        gameStartup();

    }

    public void nextButton(ActionEvent event) throws IOException {
        Image img =   Main.game.getTopOfCardDeck();
        cardImageView.setImage(img);

        updatePlayerList();
    }

    private void gameStartup(){
        ArrayList<String> playerList = Main.game.getPlayerList();

        for (int i = 0; i < playerList.size(); i++ ){
            playerListBox.getItems().add(playerList.get(i));
        }

        String pl = (String) playerListBox.getItems().get(0);
        playerListBox.getItems().remove(playerListBox.getItems().get(0));

        currentPlayer.setText(pl);
        currentPlayer1.setText(pl);
    }

    private void updatePlayerList(){
        String pl = currentPlayer.getText();
        playerListBox.getItems().add(pl);

        pl = (String) playerListBox.getItems().get(0);
        playerListBox.getItems().remove(playerListBox.getItems().get(0));

        currentPlayer.setText(pl);
        currentPlayer1.setText(pl);

    }

    public void quitButton(ActionEvent event) throws IOException {
        Main.game.clearGame();

        root = FXMLLoader.load(getClass().getResource("/main/resources/fxml/startScreen/start_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAddPlayer(ActionEvent event) {
        String name = newPlayer.getText();
        if (name.length() >0) {
            playerListBox.getItems().add(name);
        }
        newPlayer.setText("");
    }

    public void deletePlayer(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2){
            playerListBox.getItems().removeAll(playerListBox.getSelectionModel().getSelectedItems());
        }
    }
}
