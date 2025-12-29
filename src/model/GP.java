package model;

/**
 * Represents a General Practitioner (GP) - primary care doctor.
 * Extends Doctor with primary care focus.
 */
public class GP extends Doctor {

    private String primaryArea;  // e.g., "General Medicine", "Family Practice"

    /**
     * Default constructor
     */
    public GP() {
    }

    /**
     * Constructor for creating a GP
     */
    public GP(String id, String name, String contact, String qualifications, String primaryArea) {
        super(id, name, contact, "GP", "GP Surgery", qualifications, "General Practice");
        this.primaryArea = primaryArea;
    }

    public String getPrimaryArea() {
        return primaryArea;
    }

    public void setPrimaryArea(String primaryArea) {
        this.primaryArea = primaryArea;
    }

    /**
     * GP-specific methods
     */
    public void providePrimaryCare() {
        System.out.println(getFullName() + " providing primary care in " + primaryArea);
    }

    /**
     * Updated to use Referral instead of Transfer
     */
    public void referToSpecialist(Referral referral) {
        if (referral != null) {
            System.out.println(getFullName() + " referring patient " + referral.getPatientId() + " to specialist.");
        } else {
            System.out.println(getFullName() + " attempted to refer patient (no referral details).");
        }
    }

    @Override
    public String toString() {
        return "GP{" +
                "id='" + getPersonId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", specialty='General Practice'" +
                ", primaryArea='" + primaryArea + '\'' +
                '}';
    }
}