package zoo.cages;

import zoo.animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Cage {
    private int number;
    private String size;
    private int maxAnimals;
    private List<Animal> animals; 

    public Cage(int number, String size, int maxAnimals) {
        this.number = number;
        this.size = size;
        this.maxAnimals = maxAnimals;
        this.animals = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getMaxAnimals() {
        return maxAnimals;
    }

    public void setMaxAnimals(int maxAnimals) {
        this.maxAnimals = maxAnimals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        if (animals.size() < maxAnimals) {
            animals.add(animal);
            animal.setCage(this); 
        } else {
            System.out.println("Cage is full!");
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public int getCurrentAnimalsCount() {
        return animals.size();
    }
}