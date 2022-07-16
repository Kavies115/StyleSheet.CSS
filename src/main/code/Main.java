package main.code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.code.game.Game;

public class Main extends Application {

    public static Game game = new Game();

    @Override
    public void start(Stage stage) throws Exception{
      try{
          Parent root = FXMLLoader.load(getClass().getResource("/main/resources/fxml/startScreen/start_Screen.fxml"));
          Scene scene = new Scene(root);
          stage.setTitle("Drwinkyyyy");
          stage.setResizable(false);
          stage.getIcons().add(new Image("main/resources/imgs/logo.png"));
          stage.setScene(scene);
          stage.show();
      } catch (Exception e){

      }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
