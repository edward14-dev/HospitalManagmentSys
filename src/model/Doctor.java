package model;

/**
 * Abstract class representing any doctor (GP or Specialist).
 * Extends StaffMember to inherit role and workplace.
 * Adds qualifications and specialty.
 */
public abstract class Doctor extends StaffMember {

    private String qualifications;
    private String specialty;

    /**
     * Default constructor
     */
    public Doctor() {
    }

    /**
     * Full constructor
     */
    public Doctor(String id, String name, String contact, String role, String workplace,
                  String qualifications, String specialty) {
        super(id, name, contact, role, workplace);
        this.qualifications = qualifications;
        this.specialty = specialty;
    }

    // ==================== Getters and Setters ====================

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // ==================== Common Doctor Methods (Updated to Match Your Actual Classes) ====================

    /**
     * Performs a clinical assessment
     */
    public void performAssessment() {
        System.out.println(getFullName() + " is performing clinical assessment.");
    }

    /**
     * Views a patient's health record
     */
    public void viewPatientRecord(HealthRecord record) {
        if (record != null) {
            System.out.println(getFullName() + " viewed record: " + record.getRecordDetails());
        }
    }

    /**
     * Prescribes medication using your actual Prescription class
     */
    public void prescribeMedication(Prescription rx) {
        if (rx != null) {
            System.out.println(getFullName() + " prescribed " + rx.getMedicationName());
        }
    }

    /**
     * Initiates a referral using your actual Referral class
     */
    public void initiateReferral(Referral referral) {
        if (referral != null) {
            System.out.println(getFullName() + " created referral for patient " + referral.getPatientId());
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getPersonId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}