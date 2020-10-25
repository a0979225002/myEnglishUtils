package com.lipin.Controller;

import com.jfoenix.controls.JFXButton;
import com.lipin.MainAPP;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Transform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    public JFXButton b1;
    public JFXButton b2;
    public JFXButton b3;
    public JFXButton b4;

    public Stage dialogStage;
    public TranslateTransition ttButton1;
    public TranslateTransition ttButton2;
    public TranslateTransition ttButton3;
    public TranslateTransition ttButton4;

    MainAPP mainAPP;
    AllExerciseController allExerciseController;
    TranslateTransition ttDialog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialogStage = new Stage();

        addDialogAnimation(dialogStage);
        addButtonAnimation();

        initDialogStage("AllExerciseLayout.fxml", allExerciseController);
        System.out.println(allExerciseController);

    }

    public void setMainAppStage(MainAPP mainApp) {
        this.mainAPP = mainApp;
    }

    private void initDialogStage(String URL, AllExerciseController controller) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAPP.class.getResource(URL));
            parent = loader.load();
            controller = loader.getController();
            controller.setStage(dialogStage,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);
        dialogStage.setTitle("請輸入練習參數");


    }

    //dialog 漸變動畫
    private void addDialogAnimation(Stage stage) {
        Pane pane = new Pane();
        ttDialog = new TranslateTransition();
        ttDialog.setFromX(0);
        ttDialog.setToX(100);
        ttDialog.setDuration(Duration.seconds(0.5));
        ttDialog.setNode(pane);

        pane.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double value = t1.doubleValue() / 100;
                stage.setOpacity(value);
            }
        });
    }

    //button動畫
    public void addButtonAnimation() {

        ttButton1 = new TranslateTransition();
        ttButton1.setToX(-300);
        ttButton1.setToY(-300);
        ttButton1.setDuration(Duration.seconds(1));
        ttButton1.setNode(b1);

        ttButton2 = new TranslateTransition();
        ttButton2.setToX(300);
        ttButton2.setToY(-300);
        ttButton2.setDuration(Duration.seconds(1));
        ttButton2.setNode(b2);

        ttButton3 = new TranslateTransition();
        ttButton3.setToX(-300);
        ttButton3.setToY(300);
        ttButton3.setDuration(Duration.seconds(1));
        ttButton3.setNode(b3);

        ttButton4 = new TranslateTransition();
        ttButton4.setToX(300);
        ttButton4.setToY(300);
        ttButton4.setDuration(Duration.seconds(1));
        ttButton4.setNode(b4);


    }

    //綁定父類stage,讓dialog.show()方法執行後,無法點擊dialog後面的stage
    public void bindDialog(Stage stage) {
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);
    }

    /**
     * 測驗全部英文單字
     *
     * @param actionEvent
     */
    public void allExercise(ActionEvent actionEvent) throws IOException {
        dialogStage.setOpacity(0);
        dialogStage.show();
        ttDialog.play();
    }

    /**
     * 單獨測驗匯入的單字
     *
     * @param actionEvent
     */
    public void aloneExercise(ActionEvent actionEvent) {


    }

    /**
     * 測驗句子撰寫能例
     *
     * @param actionEvent
     */
    public void sentenceExercice(ActionEvent actionEvent) {


    }

    /**
     * 測驗聽力
     *
     * @param actionEvent
     */
    public void listenExercise(ActionEvent actionEvent) {


    }
}
