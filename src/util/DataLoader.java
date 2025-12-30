// File: util/DataLoader.java
package util;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Utility class to load data from CSV files into model objects.
 * All methods are static for easy access.
 */
public class DataLoader {

    private static final String DATA_FOLDER = "data/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Patient> loadPatients() {
        List<Patient> list = new ArrayList<>();
        String file = DATA_FOLDER + "patients.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 to keep trailing empty strings
                if (parts.length >= 6) {
                    Patient p = new Patient(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim()
                    );
                    list.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading patients: " + e.getMessage());
        }
        return list;
    }

    public static List<Appointment> loadAppointments() {
        List<Appointment> list = new ArrayList<>();
        String file = DATA_FOLDER + "appointments.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 to preserve empty trailing fields
                if (parts.length >= 10) { // we need at least up to reason_for_visit (index 9)
                    String appointmentId = parts[0].trim();
                    String patientId = parts[1].trim();
                    String clinicianId = parts[2].trim();
                    // parts[3] = facility_id → skip
                    String dateString = parts[4].trim(); // ✅ correct date column
                    String timeSlot = parts[5].trim();
                    // parts[6-7] = duration, type → skip
                    String status = parts[8].trim();
                    String reason = parts[9].trim();

                    Date date = null;
                    try {
                        date = DATE_FORMAT.parse(dateString);
                    } catch (ParseException e) {
                        System.err.println("Invalid date in appointment: '" + dateString + "' (full line: " + line + ")");
                        continue; // skip invalid rows
                    }

                    Appointment a = new Appointment(
                            appointmentId,
                            patientId,
                            clinicianId,
                            date,
                            timeSlot,
                            status,
                            reason
                    );
                    list.add(a);
                } else {
                    System.err.println("Appointment line has too few fields: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
        return list;
    }

    public static List<Prescription> loadPrescriptions() {
        List<Prescription> list = new ArrayList<>();
        String file = DATA_FOLDER + "prescriptions.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 7) {
                    Prescription p = new Prescription(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            parts[6].trim()
                    );
                    list.add(p);
                } else {
                    System.err.println("Prescription CSV line has <7 fields: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading prescriptions: " + e.getMessage());
        }
        return list;
    }

    public static List<Referral> loadReferrals() {
        List<Referral> list = new ArrayList<>();
        String file = DATA_FOLDER + "referrals.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 7) {
                    Date date = null;
                    try {
                        date = DATE_FORMAT.parse(parts[6].trim());
                    } catch (ParseException e) {
                        System.err.println("Invalid date in referral: " + parts[6]);
                    }

                    Referral r = new Referral(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            date
                    );
                    list.add(r);
                } else {
                    System.err.println("Referral CSV line has <7 fields: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading referrals: " + e.getMessage());
        }
        return list;
    }
}