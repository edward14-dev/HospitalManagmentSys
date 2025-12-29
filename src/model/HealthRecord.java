package model;


public class HealthRecord {

    private String recordId;
    private String patientId;
    private String recordDetails;

    public HealthRecord() {}

    public HealthRecord(String recordId, String patientId, String recordDetails) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.recordDetails = recordDetails;
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getRecordDetails() { return recordDetails; }
    public void setRecordDetails(String recordDetails) { this.recordDetails = recordDetails; }

    public void appendUpdate(String update) {
        this.recordDetails += "\n[Update] " + update;
    }

    @Override
    public String toString() {
        return "HealthRecord{" +
                "patientId='" + patientId + '\'' +
                ", details='" + recordDetails + '\'' +
                '}';
    }
}