package model;


public abstract class Person {

    private String personId;     // Unique identifier (e.g., P001, S001)
    private String fullName;     // Full name of the person
    private String contactInfo;  // Email or phone number


    public Person() {
    }


    public Person(String personId, String fullName, String contactInfo) {
        this.personId = personId;
        this.fullName = fullName;
        this.contactInfo = contactInfo;
    }

    // ==================== Getters and Setters ====================

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Returns a string representation useful for debugging and display
     */
    @Override
    public String toString() {
        return "Person{" +
                "id='" + personId + '\'' +
                ", name='" + fullName + '\'' +
                ", contact='" + contactInfo + '\'' +
                '}';
    }
}