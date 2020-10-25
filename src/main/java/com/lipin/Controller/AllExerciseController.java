package com.lipin.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.lipin.Model.AllExerciseModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class AllExerciseController implements Initializable {

    public JFXTextField numberOfQuestions;
    public JFXTextField questionsTime;
    public JFXToggleButton autoSave;
    public JFXButton playButton;
    private Stage dialogStage;
    private RootController rootController;
    private AllExerciseModel model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new AllExerciseModel();
        textFieldListener();
    }

    /**
     * 監聽用戶輸入的文字
     */
    private void textFieldListener() {

        numberOfQuestions.setTextFormatter(new TextFormatter<String>(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                //將fromString(String s) 的參數傳過來,顯示給用戶
                if (s != null && !s.equals("")) {
                    //只能輸入數字
                    if (s.trim().matches("[0-9]*")) {
                        //且不能大於100,如果大於100 ,更改用戶輸入(變成100)
                        if (Integer.valueOf(s) > 100) {
                            return 100 + "";
                            //不能是0,如果是0,更改用戶輸入(變成10)
                        } else if (Integer.valueOf(s) == 0) {
                            return 10 + "";
                        } else if (s.substring(0, 1).equals("0")) {
                            return s.substring(1, s.length());
                        } else {
                            //以上狀況都沒出現,顯示用戶輸入
                            return s;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            @Override
            public String fromString(String s) {
                //接收參數
                return s;
            }
        }));

        questionsTime.setTextFormatter(new TextFormatter<String>(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                //將fromString(String s) 的參數傳過來,顯示給用戶
                if (s != null && !s.equals("")) {

                    return value(s);

                } else {
                    return null;
                }
            }

            @Override
            public String fromString(String s) {
                //接收參數
                return s;
            }

            private String value(String s) {

                boolean intOk = s.substring(0, s.length()-1).matches("[0-9]*");
                boolean stringOk = s.substring(s.length() - 1, s.length()).equals("秒");

                //只能輸入數字,判斷
                if (s.matches("[0-9]*")) {
                    //且不能大於100,如果大於100 ,更改用戶輸入(變成99)
                    if (Integer.valueOf(s) > 100) {
                        return 99 + "秒";
                        //不能是0,如果是0,更改用戶輸入(變成10)
                    } else if (Integer.valueOf(s) == 0) {
                        return 10 + "秒";
                    } else if (s.substring(0, 1).equals("0")) {
                        return s.substring(1, s.length()) + "秒";
                    } else {
                        //以上狀況都沒出現,顯示用戶輸入
                        return s + "秒";
                    }
                    //如果是有單位(秒),重複上述判斷
                } else if (intOk && stringOk) {
                    return stringValue(s);
                } else {
                    return null;
                }
            }

            //如果該時間已經有了單位(秒),重複上述判斷
            private String stringValue(String s){
                String value = s.substring(0,s.length()-1);
                if (Integer.valueOf(value) > 100) {
                    return 99 + "秒";
                    //不能是0,如果是0,更改用戶輸入(變成10)
                } else if (Integer.valueOf(value) == 0) {
                    return 10 + "秒";
                } else if (value.substring(0, 1).equals("0")) {
                    return value.substring(1, s.length()) + "秒";
                } else {
                    //以上狀況都沒出現,顯示用戶輸入
                    return value + "秒";
                }
            }
        }));
    }

    public void setStage(Stage stage, RootController rootcontroller) {
        this.dialogStage = stage;
        this.rootController = rootcontroller;
    }


    public void playButton(ActionEvent actionEvent) {
        playButton.requestFocus();
        String questions = numberOfQuestions.getText().trim();
        String time = questionsTime.getText().trim();
        if (!questions.isEmpty() && !time.isEmpty()) {
            int timeToIntger = Integer.parseInt(time.substring(0, time.length() - 1));

            model.setNumberOfQuestions(Integer.parseInt(questions));
            model.setQuestionsTime(timeToIntger);
            dialogStage.close();
            rootController.ttButton1.play();
            rootController.ttButton2.play();
            rootController.ttButton3.play();
            rootController.ttButton4.play();
        }
    }

    public void closeButton(ActionEvent actionEvent) {
        questionsTime.setText(null);
        numberOfQuestions.setText(null);

        dialogStage.close();
    }
}
