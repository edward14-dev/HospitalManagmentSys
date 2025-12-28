package model;

/**
 * Represents a healthcare facility (GP surgery or hospital).
 */
public class Clinic {

    private String clinicId;
    private String clinicName;
    private String facilityType;     // GP Surgery or Hospital
    private String servicesOffered;
    private String contactDetails;
    private int patientCapacity;

    public Clinic() {}

    public Clinic(String clinicId, String clinicName, String facilityType, String servicesOffered,
                  String contactDetails, int patientCapacity) {
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.facilityType = facilityType;
        this.servicesOffered = servicesOffered;
        this.contactDetails = contactDetails;
        this.patientCapacity = patientCapacity;
    }

    // Getters and Setters
    public String getClinicId() { return clinicId; }
    public void setClinicId(String clinicId) { this.clinicId = clinicId; }

    public String getClinicName() { return clinicName; }
    public void setClinicName(String clinicName) { this.clinicName = clinicName; }

    public String getFacilityType() { return facilityType; }
    public void setFacilityType(String facilityType) { this.facilityType = facilityType; }

    public String getServicesOffered() { return servicesOffered; }
    public void setServicesOffered(String servicesOffered) { this.servicesOffered = servicesOffered; }

    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }

    public int getPatientCapacity() { return patientCapacity; }
    public void setPatientCapacity(int patientCapacity) { this.patientCapacity = patientCapacity; }

    @Override
    public String toString() {
        return "Clinic{" +
                "id='" + clinicId + '\'' +
                ", name='" + clinicName + '\'' +
                ", type='" + facilityType + '\'' +
                '}';
    }
}