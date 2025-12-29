package model;

public class Nurse extends Doctor {

    public Nurse() {}

    public Nurse(String id, String name, String contact, String qualifications) {
        super(id, name, contact, "Nurse", "GP Surgery/Hospital", qualifications, "Clinical Nursing");
    }

    public void providePatientSupport() {
        System.out.println(getFullName() + " providing nursing support.");
    }

    public void supportClinicalAssessment() {
        System.out.println(getFullName() + " assisting in clinical assessment.");
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id='" + getPersonId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", role='Nurse'" +
                '}';
    }
}