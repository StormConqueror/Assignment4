package gui;  

import javax.swing.*;  
import javax.swing.table.DefaultTableModel;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.ResultSet;  
import java.sql.SQLException;  

import db.DatabaseConnector;  
import model.Animal;  
import model.Cage;  

public class ZooGUI {  

    private JFrame frame;  
    private JTextField nameField, speciesField, ageField, cageNameField;  
    private JTable animalTable, cageTable;  
    private int selectedAnimalId = -1; // Track selected animal to update  
    private int selectedCageId = -1;   // Track selected cage to delete  

    public ZooGUI() {  
        frame = new JFrame("Zoo Management System");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(800, 600);  

        // Panel setup  
        JPanel panel = new JPanel(new GridLayout(0, 2));  
        
        // Animal Input Fields  
        panel.add(new JLabel("Animal Name:"));  
        nameField = new JTextField();  
        panel.add(nameField);  

        panel.add(new JLabel("Species:"));  
        speciesField = new JTextField();  
        panel.add(speciesField);  
        
        panel.add(new JLabel("Age:"));  
        ageField = new JTextField();  
        panel.add(ageField);  

        JButton addAnimalButton = new JButton("Add Animal");  
        addAnimalButton.addActionListener(new AddAnimalAction());  
        panel.add(addAnimalButton);  

        JButton updateAnimalButton = new JButton("Update Animal");  
        updateAnimalButton.addActionListener(new UpdateAnimalAction());  
        panel.add(updateAnimalButton);  

        JButton deleteAnimalButton = new JButton("Delete Animal");  
        deleteAnimalButton.addActionListener(e -> deleteAnimal());  
        panel.add(deleteAnimalButton);  

        // Cage Input Fields  
        panel.add(new JLabel("Cage Name:"));  
        cageNameField = new JTextField();  
        panel.add(cageNameField);  

        JButton addCageButton = new JButton("Add Cage");  
        addCageButton.addActionListener(new AddCageAction());  
        panel.add(addCageButton);  

        JButton deleteCageButton = new JButton("Delete Cage");  
        deleteCageButton.addActionListener(e -> deleteCage());  
        panel.add(deleteCageButton);  

        animalTable = new JTable();  
        cageTable = new JTable();  

        populateAnimalTable();  
        populateCageTable();  

        // Adding components to the frame  
        JPanel tablePanel = new JPanel(new GridLayout(1, 2));  
        tablePanel.add(new JScrollPane(animalTable));  
        tablePanel.add(new JScrollPane(cageTable));  

        frame.add(panel, BorderLayout.NORTH);  
        frame.add(tablePanel, BorderLayout.CENTER);  
        
        frame.setVisible(true);  
    }  

    private void populateAnimalTable() {  
        try {  
            ResultSet rs = DatabaseConnector.listAnimals();  
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Species", "Age"}, 0);  
            while (rs.next()) {  
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("species"), rs.getInt("age")});  
            }  
            animalTable.setModel(model);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  

    private void populateCageTable() {
        try {
            ResultSet rs = DatabaseConnector.listCages();
            DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
            while (rs.next()) {
                model.addRow(new Object[] { rs.getInt("id"), rs.getString("name") });
            }
            cageTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Action for adding an animal  
    private class AddAnimalAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            String name = nameField.getText();  
            String species = speciesField.getText();  
            int age = Integer.parseInt(ageField.getText());  
            // Assuming a default cage ID; in practice, you'd want to select from existing cages  
            int cageId = selectedCageId;  
            Animal animal = new Animal(name, species, age);  
            DatabaseConnector.addAnimal(animal, cageId);  
            populateAnimalTable();  
        }  
    }  

    // Action for updating an animal  
    private class UpdateAnimalAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            if (selectedAnimalId != -1) {  
                String name = nameField.getText();  
                String species = speciesField.getText();  
                int age = Integer.parseInt(ageField.getText());  
                Animal animal = new Animal(name, species, age);  
                DatabaseConnector.updateAnimal(animal, selectedAnimalId);  
                populateAnimalTable();  
            } else {  
                JOptionPane.showMessageDialog(frame, "Select an animal to update.");  
            }  
        }  
    }  

    private void deleteAnimal() {  
        if (selectedAnimalId != -1) {  
            DatabaseConnector.deleteAnimal(selectedAnimalId);  
            populateAnimalTable();  
            selectedAnimalId = -1; // Reset after deletion  
        } else {  
            JOptionPane.showMessageDialog(frame, "Select an animal to delete.");  
        }  
    }  

    // Action for adding a cage  
    private class AddCageAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            String cageName = cageNameField.getText();  
            Cage cage = new Cage(cageName);  
            DatabaseConnector.addCage(cage);  
            populateCageTable();  
        }  
    }  

    private void deleteCage() {  
        if (selectedCageId != -1) {  
            DatabaseConnector.deleteCage(selectedCageId);  
            populateCageTable();  
            selectedCageId = -1; // Reset after deletion  
        } else {  
            JOptionPane.showMessageDialog(frame, "Select a cage to delete.");  
        }  
    }  

    public static void main(String[] args) {  
        new ZooGUI();  
    }  
}