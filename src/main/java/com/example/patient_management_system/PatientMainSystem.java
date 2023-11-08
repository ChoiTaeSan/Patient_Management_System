package com.example.patient_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PatientMainSystem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML 파일 로드
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientManagementController.fxml"));
        Parent root = loader.load();

        // 컨트롤러 설정
        PatientManagementController controller = loader.getController();

        // Scene 설정
        Scene scene = new Scene(root, 320, 400);

        // Stage 설정
        primaryStage.setTitle("환자 관리 시스템");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
