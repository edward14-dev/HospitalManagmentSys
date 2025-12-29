package model;

/**
 * Represents a Specialist Doctor (secondary care).
 * Extends Doctor with specific expertise area.
 */
public class Specialist extends Doctor {

    private String expertiseArea;

    public Specialist() {}

    public Specialist(String id, String name, String contact, String qualifications, String expertiseArea) {
        super(id, name, contact, "Specialist", "Hospital", qualifications, expertiseArea);
        this.expertiseArea = expertiseArea;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    public void deliverSpecialistCare() {
        System.out.println(getFullName() + " providing specialist care in " + expertiseArea);
    }

    public void assessReferral(Referral referral) {
        System.out.println(getFullName() + " assessing referral for patient " + referral.getPatientId());
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "id='" + getPersonId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", expertise='" + expertiseArea + '\'' +
                '}';
    }
}