package model;

import java.util.Date;


public class Referral {

    private String referralId;
    private String patientId;
    private String referringClinicianId;
    private String targetFacilityId;
    private String clinicalSummary;
    private String urgencyLevel;  // Low, Medium, High
    private Date referralDate;

    public Referral() {}

    public Referral(String referralId, String patientId, String referringClinicianId,
                    String targetFacilityId, String clinicalSummary, String urgencyLevel, Date referralDate) {
        this.referralId = referralId;
        this.patientId = patientId;
        this.referringClinicianId = referringClinicianId;
        this.targetFacilityId = targetFacilityId;
        this.clinicalSummary = clinicalSummary;
        this.urgencyLevel = urgencyLevel;
        this.referralDate = referralDate;
    }

    // Getters and Setters
    public String getReferralId() { return referralId; }
    public void setReferralId(String referralId) { this.referralId = referralId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getReferringClinicianId() { return referringClinicianId; }
    public void setReferringClinicianId(String referringClinicianId) { this.referringClinicianId = referringClinicianId; }

    public String getTargetFacilityId() { return targetFacilityId; }
    public void setTargetFacilityId(String targetFacilityId) { this.targetFacilityId = targetFacilityId; }

    public String getClinicalSummary() { return clinicalSummary; }
    public void setClinicalSummary(String clinicalSummary) { this.clinicalSummary = clinicalSummary; }

    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }

    public Date getReferralDate() { return referralDate; }
    public void setReferralDate(Date referralDate) { this.referralDate = referralDate; }

    public void processReferral() {
        System.out.println("Processing referral " + referralId + " (Urgency: " + urgencyLevel + ")");
    }

    @Override
    public String toString() {
        return "Referral{" +
                "id='" + referralId + '\'' +
                ", patient='" + patientId + '\'' +
                ", urgency='" + urgencyLevel + '\'' +
                ", summary='" + clinicalSummary + '\'' +
                '}';
    }
}