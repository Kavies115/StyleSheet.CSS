package main.code.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.code.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SelectScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public ListView topicListBox;
    public ComboBox topicComboBox;

    public TextField playerTextbox;
    public ListView playerListBox;

    @FXML
    public void initialize() {
        findCardDecks();
    }

    public void backButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/resources/fxml/startScreen/start_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void enterPlayerName(ActionEvent actionEvent) {
        String playerName = playerTextbox.getText();
        playerTextbox.clear();
        playerListBox.getItems().add(playerName);
    }

    public void deletePlayer(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2){
             playerListBox.getItems().removeAll(playerListBox.getSelectionModel().getSelectedItems());
        }
    }

    private void findCardDecks(){
        //Creating a File object for directory
        File directoryPath = new File("src/main/resources/cards");
        //List of all files and directories
        String contents[] = directoryPath.list();
        for(int i=0; i<contents.length; i++) {
            topicComboBox.getItems().add(contents[i].toString());
        }
    }

    public void enterTopic(ActionEvent actionEvent) {
        topicListBox.getItems().add(topicComboBox.getSelectionModel().getSelectedItem().toString());
        topicComboBox.getItems().remove(topicComboBox.getSelectionModel().getSelectedIndex());
        topicComboBox.getSelectionModel().clearSelection();
    }

    public void deleteTopic(MouseEvent mouseEvent) {if (mouseEvent.getClickCount() == 2){
        String topicName = removeFirstandLast((topicListBox.getSelectionModel().getSelectedItems().toString()));
        topicListBox.getItems().removeAll(topicListBox.getSelectionModel().getSelectedItems());
        topicComboBox.getItems().add(topicName);

        }
    }

    private static String removeFirstandLast(String str) {
        str = str.substring(1, str.length() - 1);
        return str;
    }

    public void startButton(ActionEvent event) throws IOException {
        setGameUp();
        loadGameScreen(event);
        setPlayers();
    }

    private void loadGameScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/resources/fxml/gameScreen/game_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setGameUp(){
        getPlayers();
        getDecks();
    }

    private void getPlayers(){
        ArrayList<String> playerList = new ArrayList<String>();
        for (int i = 0; i < playerListBox.getItems().size(); i++){
            playerList.add(playerListBox.getItems().get(i).toString());
        }
        Main.game.setPlayerList(playerList);
    }

    private void getDecks(){
        ArrayList<String> deckList = new ArrayList<String>();
        for (int i = 0; i < topicListBox.getItems().size(); i++){
            deckList.add(topicListBox.getItems().get(i).toString());
        }
        Main.game.setCardDirectories(deckList);
    }

    private void setPlayers(){
        ArrayList<String> playerList = new ArrayList<String>();
        for (int i = 0; i < playerListBox.getItems().size(); i++){
            playerList.add(playerListBox.getItems().get(i).toString());
        }
        Main.game.setPlayerList(playerList);
    }
}
