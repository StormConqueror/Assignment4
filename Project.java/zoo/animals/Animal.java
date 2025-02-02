package zoo.animals;

public class Animal {
    private String name;
    private boolean predator;
    private zoo.cages.Cage cage; 

    public Animal(String name, boolean predator) {
        this.name = name;
        this.predator = predator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPredator() {
        return predator;
    }

    public void setPredator(boolean predator) {
        this.predator = predator;
    }

    public zoo.cages.Cage getCage() {
        return cage;
    }

    public void setCage(zoo.cages.Cage cage) {
        this.cage = cage;
    }
}