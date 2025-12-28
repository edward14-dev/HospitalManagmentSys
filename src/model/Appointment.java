package model;

import java.util.Date;

/**
 * Represents a scheduled appointment between a patient and clinician.
 */
public class Appointment {

    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private Date appointmentDate;
    private String timeSlot;
    private String currentStatus;  // e.g., Scheduled, Completed, Cancelled
    private String visitReason;

    public Appointment() {}

    public Appointment(String appointmentId, String patientId, String clinicianId,
                       Date appointmentDate, String timeSlot, String currentStatus, String visitReason) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.currentStatus = currentStatus;
        this.visitReason = visitReason;
    }

    // Getters and Setters
    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }

    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }

    public String getVisitReason() { return visitReason; }
    public void setVisitReason(String visitReason) { this.visitReason = visitReason; }

    public void confirmAppointment() { this.currentStatus = "Confirmed"; }
    public void reschedule(Date newDate, String newTime) {
        this.appointmentDate = newDate;
        this.timeSlot = newTime;
        this.currentStatus = "Rescheduled";
    }
    public void cancelAppointment() { this.currentStatus = "Cancelled"; }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + appointmentId + '\'' +
                ", patient='" + patientId + '\'' +
                ", date=" + appointmentDate +
                ", status='" + currentStatus + '\'' +
                '}';
    }
}