package com.example.patient_management_system;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
    private String name;
    private int age;
    private String diagnosis;
    private LocalDate diagnosisDate; // 진단일자 필드 추가

    public Patient(String name, int age, String diagnosis, LocalDate diagnosisDate) {
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.diagnosisDate = diagnosisDate; // 진단일자 설정
    }
    public LocalDate getDiagnosisDate() {
        return diagnosisDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
