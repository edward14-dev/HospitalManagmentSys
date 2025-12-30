// File: view/MainWindow.java
package view;

import model.*;
import util.DataLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Main GUI window with CRUD only for Patients.
 * Other tabs (Appointments, Prescriptions, Referrals) are view-only.
 * "Create Referral" moved to bottom as a contextual action.
 */
public class MainWindow extends JFrame {

    private List<Patient> patients;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private List<Referral> referrals;

    private DefaultTableModel patientModel;
    private JTable patientTable;

    private JPanel currentPanel;

    public MainWindow() {
        setTitle("Health Care System - Patient Management");
        setSize(1050, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 250));

        // Load data
        patients = DataLoader.loadPatients();
        appointments = DataLoader.loadAppointments();
        prescriptions = DataLoader.loadPrescriptions();
        referrals = DataLoader.loadReferrals();

        setLayout(new BorderLayout());
        add(createSidebar(), BorderLayout.WEST);

        currentPanel = new JPanel(new BorderLayout());
        add(currentPanel, BorderLayout.CENTER);

        showPatientsPanel();
        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(80, 100, 180));
        sidebar.setPreferredSize(new Dimension(180, 0));

        JButton patientsBtn = new JButton("Patients");
        JButton apptsBtn = new JButton("Appointments");
        JButton rxBtn = new JButton("Prescriptions");
        JButton referralsBtn = new JButton("Referrals");

        styleButton(patientsBtn);
        styleButton(apptsBtn);
        styleButton(rxBtn);
        styleButton(referralsBtn);

        patientsBtn.addActionListener(e -> showPatientsPanel());
        apptsBtn.addActionListener(e -> showAppointmentsPanel());
        rxBtn.addActionListener(e -> showPrescriptionsPanel());
        referralsBtn.addActionListener(e -> showReferralsPanel());

        sidebar.add(Box.createVerticalStrut(40));
        sidebar.add(patientsBtn);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(apptsBtn);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(rxBtn);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(referralsBtn);
        return sidebar;
    }

    private void styleButton(JButton btn) {
        btn.setMaximumSize(new Dimension(160, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(new Color(210, 225, 255));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
    }

    // ============== PATIENTS (FULL CRUD) ==============
    private void showPatientsPanel() {
        currentPanel.removeAll();
        currentPanel.add(createPatientsPanel(), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    private JPanel createPatientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] cols = {"ID", "Name", "Contact", "NHS No.", "GP Surgery", "History"};
        patientModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        loadPatientData();

        patientTable = new JTable(patientModel);
        patientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(patientTable);

        // Top bar: Add button only
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addBtn = new JButton("➕ Add Patient");
        addBtn.addActionListener(e -> addPatient());
        topBar.add(addBtn);

        // Bottom bar: Update & Delete
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton editBtn = new JButton("✏️ Edit Selected");
        JButton deleteBtn = new JButton("🗑️ Delete Selected");

        editBtn.addActionListener(e -> editPatient());
        deleteBtn.addActionListener(e -> deletePatient());

        bottomBar.add(editBtn);
        bottomBar.add(deleteBtn);

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(bottomBar, BorderLayout.SOUTH);
        return panel;
    }

    private void loadPatientData() {
        patientModel.setRowCount(0);
        for (Patient p : patients) {
            patientModel.addRow(new Object[]{
                    p.getPersonId(), p.getFullName(), p.getContactInfo(),
                    p.getNhsNumber(), p.getGpSurgery(), p.getMedicalHistory()
            });
        }
    }

    private void addPatient() {
        String name = JOptionPane.showInputDialog(this, "Full Name:");
        if (name == null || name.trim().isEmpty()) return;

        String contact = JOptionPane.showInputDialog(this, "Contact (Email/Phone):");
        String nhs = JOptionPane.showInputDialog(this, "NHS Number:");
        if (nhs == null || nhs.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NHS Number is required.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String gp = JOptionPane.showInputDialog(this, "GP Surgery:");
        String history = JOptionPane.showInputDialog(this, "Medical History (Optional):");

        String id = "P" + (patients.size() + 1);
        Patient p = new Patient(id, name.trim(), contact != null ? contact.trim() : "",
                nhs.trim(), gp != null ? gp.trim() : "",
                history != null ? history.trim() : "");
        patients.add(p);
        loadPatientData();
    }

    private void editPatient() {
        int row = patientTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a patient to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = patientTable.convertRowIndexToModel(row);
        String id = (String) patientModel.getValueAt(modelRow, 0);
        String currentName = (String) patientModel.getValueAt(modelRow, 1);
        String currentContact = (String) patientModel.getValueAt(modelRow, 2);
        String currentNhs = (String) patientModel.getValueAt(modelRow, 3);
        String currentGp = (String) patientModel.getValueAt(modelRow, 4);
        String currentHistory = (String) patientModel.getValueAt(modelRow, 5);

        String name = JOptionPane.showInputDialog(this, "Name:", currentName);
        if (name == null) return;
        String contact = JOptionPane.showInputDialog(this, "Contact:", currentContact);
        String nhs = JOptionPane.showInputDialog(this, "NHS Number:", currentNhs);
        if (nhs == null || nhs.trim().isEmpty()) return;
        String gp = JOptionPane.showInputDialog(this, "GP Surgery:", currentGp);
        String history = JOptionPane.showInputDialog(this, "Medical History:", currentHistory);

        // Update in list
        Patient p = findPatientById(id);
        if (p != null) {
            p.setFullName(name);
            p.setContactInfo(contact != null ? contact : "");
            p.setNhsNumber(nhs);
            p.setGpSurgery(gp != null ? gp : "");
            p.setMedicalHistory(history != null ? history : "");
        }

        loadPatientData(); // Refresh table
    }

    private void deletePatient() {
        int row = patientTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = patientTable.convertRowIndexToModel(row);
        String id = (String) patientModel.getValueAt(modelRow, 0);
        String name = (String) patientModel.getValueAt(modelRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete patient: " + name + " (" + id + ")?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            patients.removeIf(p -> p.getPersonId().equals(id));
            loadPatientData();
        }
    }

    private Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getPersonId().equals(id)) return p;
        }
        return null;
    }

    // ============== APPOINTMENTS (VIEW ONLY) ==============
    private void showAppointmentsPanel() {
        currentPanel.removeAll();
        currentPanel.add(createReadonlyTable("Appointments",
                new String[]{"ID", "Patient", "Clinician", "Date", "Time", "Status", "Reason"},
                appointments), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    // ============== PRESCRIPTIONS (VIEW ONLY) ==============
    private void showPrescriptionsPanel() {
        currentPanel.removeAll();
        currentPanel.add(createReadonlyTable("Prescriptions",
                new String[]{"ID", "Patient", "Medication", "Dosage", "Pharmacy", "Status"},
                prescriptions), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    // ============== REFERRALS (VIEW ONLY + CREATE BUTTON AT BOTTOM) ==============
    private void showReferralsPanel() {
        currentPanel.removeAll();
        currentPanel.add(createReferralsPanel(), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    private JPanel createReferralsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] cols = {"ID", "Patient", "From", "To", "Summary", "Urgency"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        for (Referral r : referrals) {
            model.addRow(new Object[]{
                    r.getReferralId(),
                    r.getPatientId(),
                    r.getReferringClinicianId(),
                    r.getTargetFacilityId(),
                    r.getClinicalSummary(),
                    r.getUrgencyLevel()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // ✅ "Create Referral" moved to bottom — suitable for an action
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createReferralBtn = new JButton("📤 Create New Referral");
        createReferralBtn.addActionListener(e -> createReferral());
        actionPanel.add(createReferralBtn);

        panel.add(new JLabel("Referrals (Read-only view)"), BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void createReferral() {
        String patientId = JOptionPane.showInputDialog(this, "Patient ID:");
        if (patientId == null || patientId.trim().isEmpty()) return;

        String summary = JOptionPane.showInputDialog(this, "Clinical Summary:");
        if (summary == null || summary.trim().isEmpty()) return;

        String urgency = JOptionPane.showInputDialog(this, "Urgency (Low/Medium/High):");
        if (urgency == null) urgency = "Medium";

        String id = "R" + (referrals.size() + 1);
        Referral referral = new Referral(id, patientId.trim(), "GP01", "HOSP01",
                summary.trim(), urgency.trim(), new Date());

        ReferralHandler.getInstance().createReferral(referral);
        referrals.add(referral);

        // Refresh referrals tab only if currently visible (optional)
        JOptionPane.showMessageDialog(this, "Referral created and logged.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Generic readonly table for appointments/prescriptions
    private JPanel createReadonlyTable(String title, String[] columns, List<?> dataList) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        if (title.equals("Appointments")) {
            for (Object obj : dataList) {
                Appointment a = (Appointment) obj;
                String dateStr = a.getAppointmentDate() != null ?
                        new SimpleDateFormat("yyyy-MM-dd").format(a.getAppointmentDate()) : "Invalid";
                model.addRow(new Object[]{
                        a.getAppointmentId(), a.getPatientId(), a.getClinicianId(),
                        dateStr, a.getTimeSlot(), a.getCurrentStatus(), a.getVisitReason()
                });
            }
        } else if (title.equals("Prescriptions")) {
            for (Object obj : dataList) {
                Prescription p = (Prescription) obj;
                model.addRow(new Object[]{
                        p.getPrescriptionId(), p.getPatientId(), p.getMedicationName(),
                        p.getDosageInstructions(), p.getPharmacyName(), p.getCollectionStatus()
                });
            }
        }

        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}