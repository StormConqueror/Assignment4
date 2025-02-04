package model;

import db.DatabaseConnector;
import gui.ZooGUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private String name;
    private List<Cage> cages;

    public Zoo(String name) {
        this.name = name;
        this.cages = new ArrayList<>();
    }

    // Add a cage to the zoo
    public void addCage(Cage cage) {
        cages.add(cage);
        DatabaseConnector.addCage(cage); // Add to the database
    }

    // Remove a cage from the zoo
    public void removeCage(Cage cage) {
        cages.remove(cage);
        DatabaseConnector.deleteCage(cage.getId()); // Delete from the database
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public List<Cage> getCages() {
        return cages;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Main method
    public static void main(String[] args) {
        Zoo myZoo = new Zoo("My Zoo");

        // Initialize GUI
        java.awt.EventQueue.invokeLater(() -> new ZooGUI());

        // Example of adding cages directly for demonstration
        Cage lionCage = new Cage("Lion Cage");
        Cage tigerCage = new Cage("Tiger Cage");
        myZoo.addCage(lionCage);
        myZoo.addCage(tigerCage);

        // Fetch all cages from the database to update the zoo instance
        myZoo.updateCagesFromDatabase();

        // Display cages in console as feedback
        System.out.println("Cages in the Zoo:");
        for (Cage cage : myZoo.getCages()) {
            System.out.println(cage.getName());
        }
    }

    // Fetch from the database and update the local list of cages
    public void updateCagesFromDatabase() {
        try {
            ResultSet rs = DatabaseConnector.listCages();
            while (rs.next()) {
                Cage cage = new Cage(rs.getString("name"));
                cage.setId(rs.getInt("id")); // Assuming id is available
                cages.add(cage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to find a cage by name
    public Cage findCageByName(String cageName) {
        for (Cage cage : cages) {
            if (cage.getName().equalsIgnoreCase(cageName)) {
                return cage;
            }
        }
        return null; // Return null if not found
    }
}