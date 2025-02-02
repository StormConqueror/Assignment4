package zoo;

import zoo.cages.Cage;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private String name;
    private List<Cage> cages;

    public Zoo(String name) {
        this.name = name;
        this.cages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cage> getCages() {
        return cages;
    }

    public void addCage(Cage cage) {
        cages.add(cage);
    }

    public void removeCage(Cage cage) {
        cages.remove(cage);
    }

    public zoo.animals.Animal findAnimalByName(String name) {
        for (Cage cage : cages) {
            for (zoo.animals.Animal animal : cage.getAnimals()) {
                if (animal.getName().equals(name)) {
                    return animal;
                }
            }
        }
        return null;
    }
}