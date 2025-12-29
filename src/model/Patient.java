package model;

import java.util.ArrayList;
import java.util.List;


public class Patient extends Person {

    private String nhsNumber;        // Unique NHS patient identifier
    private String gpSurgery;        // Registered GP practice
    private String medicalHistory;   // Patient's medical background/notes
    private List<Appointment> appointmentList;  // List of patient's appointments

    /**
     * Default constructor - required for CSV loading and flexibility
     */
    public Patient() {
        this.appointmentList = new ArrayList<>();
    }

    /**
     * Full constructor used when creating a new patient
     */
    public Patient(String id, String name, String contact, String nhsNumber,
                   String gpSurgery, String medicalHistory) {
        super(id, name, contact);
        this.nhsNumber = nhsNumber;
        this.gpSurgery = gpSurgery;
        this.medicalHistory = medicalHistory;
        this.appointmentList = new ArrayList<>();
    }

    // ==================== Getters and Setters ====================

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public String getGpSurgery() {
        return gpSurgery;
    }

    public void setGpSurgery(String gpSurgery) {
        this.gpSurgery = gpSurgery;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    /**
     * Adds an appointment to the patient's record
     */
    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointmentList.add(appointment);
        }
    }

    /**
     * Updates the patient's medical history (from class diagram requirement)
     */
    public void updateMedicalRecord(String newHistory) {
        this.medicalHistory = newHistory;
    }

    /**
     * Nice string representation for display and debugging
     */
    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getPersonId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", nhsNumber='" + nhsNumber + '\'' +
                ", gpSurgery='" + gpSurgery + '\'' +
                ", appointments=" + appointmentList.size() +
                '}';
    }
}