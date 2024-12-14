
package com.example.mpfirstfitallocation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/mpfirstfitallocation/firstfitallocation-view.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("First Fit Memory Allocation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

