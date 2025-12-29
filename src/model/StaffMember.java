package model;

/**
 * Represents any staff member in the healthcare system (clinical or non-clinical).
 * Extends Person to inherit ID, name, and contact.
 * Adds role and workplace specific to staff.
 */
public class StaffMember extends Person {

    private String role;         // e.g., "Receptionist", "Doctor", "Nurse"
    private String workplace;    // Facility/Clinic name or ID

    /**
     * Default constructor
     */
    public StaffMember() {
    }

    /**
     * Full constructor
     */
    public StaffMember(String id, String name, String contact, String role, String workplace) {
        super(id, name, contact);
        this.role = role;
        this.workplace = workplace;
    }

    // ==================== Getters and Setters ====================

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    /**
     * Placeholder for general staff operations
     */
    public void performDuties() {
        System.out.println(getFullName() + " (" + role + ") is performing duties at " + workplace);
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "id='" + getPersonId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", role='" + role + '\'' +
                ", workplace='" + workplace + '\'' +
                '}';
    }
}