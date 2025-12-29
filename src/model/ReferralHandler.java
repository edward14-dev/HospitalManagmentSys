package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReferralHandler {

    private static ReferralHandler singleInstance;
    private List<Referral> referralList;
    private static final String LOG_FILE = "referral_log.txt";

    private ReferralHandler() {
        referralList = new ArrayList<>();
        // Initialize log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE))) {
            writer.println("=== Referral Log Started: " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) + " ===");
        } catch (IOException e) {
            System.err.println("Could not create referral log file.");
        }
    }

    public static ReferralHandler getInstance() {
        if (singleInstance == null) {
            singleInstance = new ReferralHandler();
        }
        return singleInstance;
    }

    public void createReferral(Referral referral) {
        if (referral != null) {
            referralList.add(referral);
            logReferralToFile(referral);
            simulateEHRUpdate(referral);
            System.out.println("Referral " + referral.getReferralId() + " created and logged.");
        }
    }

    private void logReferralToFile(Referral referral) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("\n--- New Referral ---");
            writer.println("ID: " + referral.getReferralId());
            writer.println("Patient: " + referral.getPatientId());
            writer.println("From: " + referral.getReferringClinicianId());
            writer.println("To: " + referral.getTargetFacilityId());
            writer.println("Urgency: " + referral.getUrgencyLevel());
            writer.println("Summary: " + referral.getClinicalSummary());
            writer.println("Date: " + referral.getReferralDate());
            writer.println("-------------------");
        } catch (IOException e) {
            System.err.println("Failed to log referral.");
        }
    }

    private void simulateEHRUpdate(Referral referral) {
        System.out.println("EHR updated for patient " + referral.getPatientId() + " with referral " + referral.getReferralId());
    }

    public List<Referral> getAllReferrals() {
        return new ArrayList<>(referralList);
    }
}