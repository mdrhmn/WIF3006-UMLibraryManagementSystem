package com.lmsspringclient;

import java.io.IOException;
import static javafx.application.Application.launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

@Configuration
public class MainActivity extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        try {
            /**
             * Load GUI design from FXML file
             */
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_LOGIN.fxml"));
            root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
            Scene scene = new Scene(root);

            /**
             * Prevent user from resizing the window (to avoid glitches)
             */
            stage.setTitle("UM Library Management System: Sign In Menu");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
