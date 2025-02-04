package model;

import java.util.*;

public class Cage {
    private int id; // Add ID field
    private String name;
    private List<Animal> animals;

    public Cage(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public int getId() { // Getter for ID
        return id;
    }

    public void setId(int id) { // Setter for ID
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
}