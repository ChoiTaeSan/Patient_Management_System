package com.example.patient_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class PatientManagementController extends VBox {

    @FXML
    private TableView<Patient> patientTable = new TableView<>();
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private DatePicker diagnosisDateField; // DatePicker 추가
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    private List<Patient> patients = new ArrayList<>();

    @FXML
    private void addPatient() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String diagnosis = diagnosisField.getText();
        LocalDate diagnosisDate = null;

        // 날짜 형식을 파싱하고 예외 처리
        try {
            String dateString = diagnosisDateField.getEditor().getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 원하는 날짜 형식으로 수정
            diagnosisDate = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            showErrorAlert("유효하지 않은 날짜 형식입니다. (예: yyyy-MM-dd)");
            diagnosisDateField.setValue(null);
            return;
        }
        if (name.isEmpty() || ageText.isEmpty() || diagnosis.isEmpty() || diagnosisDate == null) {
            showErrorAlert("모든 필수 정보를 입력해주세요.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);

            if (age < 0) {
                showErrorAlert("나이는 음수가 될 수 없습니다.");
            } else if (diagnosisDate.isAfter(LocalDate.now())) {
                showErrorAlert("진단일자는 오늘 날짜 이전이어야 합니다.");
            } else {
                Patient patient = new Patient(name, age, diagnosis, diagnosisDate);
                patients.add(patient);
                updateTable();
            }
        } catch (NumberFormatException e) {
            showErrorAlert("나이를 유효한 숫자로 입력해주세요.");
        }
    }



    @FXML
    private void updatePatient() {
        int index = patientTable.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            String name = nameField.getText();
            String ageText = ageField.getText();
            String diagnosis = diagnosisField.getText();
            LocalDate diagnosisDate = diagnosisDateField.getValue();

            if (!name.isEmpty() && !ageText.isEmpty() && !diagnosis.isEmpty() && diagnosisDate != null) {
                try {
                    int age = Integer.parseInt(ageText);

                    Patient updatedPatient = new Patient(name, age, diagnosis, diagnosisDate);
                    patients.set(index, updatedPatient);
                    updateTable();
                } catch (NumberFormatException e) {
                    // 나이를 정수로 변환할 수 없는 경우의 예외 처리 코드
                    showErrorAlert("나이를 유효한 숫자로 입력해주세요.");
                }
            } else {
                // 필수 입력 필드가 비어 있거나 진단일자가 널인 경우의 처리
                showErrorAlert("모든 필수 정보를 입력해주세요.");
            }
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("오류");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void deletePatient() {
        int index = patientTable.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            patients.remove(index);
            updateTable();
        }
    }

    private void updateTable() {
        patientTable.getItems().setAll(patients);
        nameField.clear();
        ageField.clear();
        diagnosisField.clear();
        diagnosisDateField.setValue(null); // 진단일자 DatePicker 초기화
    }
}
