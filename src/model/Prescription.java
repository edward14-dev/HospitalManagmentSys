package model;


public class Prescription {

    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String medicationName;
    private String dosageInstructions;
    private String pharmacyName;
    private String collectionStatus;  // e.g., Pending, Collected

    public Prescription() {}

    public Prescription(String prescriptionId, String patientId, String clinicianId,
                        String medicationName, String dosageInstructions, String pharmacyName, String collectionStatus) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.medicationName = medicationName;
        this.dosageInstructions = dosageInstructions;
        this.pharmacyName = pharmacyName;
        this.collectionStatus = collectionStatus;
    }

    // Getters and Setters
    public String getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(String prescriptionId) { this.prescriptionId = prescriptionId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }

    public String getMedicationName() { return medicationName; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }

    public String getDosageInstructions() { return dosageInstructions; }
    public void setDosageInstructions(String dosageInstructions) { this.dosageInstructions = dosageInstructions; }

    public String getPharmacyName() { return pharmacyName; }
    public void setPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }

    public String getCollectionStatus() { return collectionStatus; }
    public void setCollectionStatus(String collectionStatus) { this.collectionStatus = collectionStatus; }

    public String getMedicationDetails() {
        return medicationName + " - " + dosageInstructions;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id='" + prescriptionId + '\'' +
                ", medication='" + medicationName + '\'' +
                ", dosage='" + dosageInstructions + '\'' +
                ", status='" + collectionStatus + '\'' +
                '}';
    }
}