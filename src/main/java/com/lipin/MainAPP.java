package com.lipin;

import com.lipin.Controller.RootController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX
 */
public class MainAPP extends Application {
    public Stage mainStage;
    private Parent buttonLayout;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        initbuttonLayout();

        StackPane root = new StackPane();
        root.getChildren().addAll(buttonLayout);

        Scene scene = new Scene(root);
        mainStage.initStyle(StageStyle.UTILITY);
        mainStage.centerOnScreen();
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();

    }

    private void initbuttonLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RootLayout.fxml"));
        buttonLayout = loader.load();
        RootController controller = loader.getController();
        controller.setMainAppStage(this);
        controller.bindDialog(mainStage);
    }

    /**
     * 儲存英文單字入xml中
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
