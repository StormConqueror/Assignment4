import java.util.Scanner;

import zoo.Zoo;
import zoo.animals.Animal;
import zoo.cages.Cage;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String zooName;
        do {
            System.out.print("Enter the name of the zoo: ");
            zooName = scanner.nextLine();
            if (zooName.trim().isEmpty()) {
                System.out.println("Zoo name cannot be empty. Please try again.");
            }
        } while (zooName.trim().isEmpty());

        Zoo zoo = new Zoo(zooName);

        int cagesCount;
        do {
            System.out.print("Enter the number of cages: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid format. Please enter an integer.");
                scanner.next();
            }
            cagesCount = scanner.nextInt();
            scanner.nextLine();
            if (cagesCount < 0) {
                System.out.println("The number of cages must be greater than 0. Please try again.");
            }
        } while (cagesCount < 0);

        for (int i = 0; i < cagesCount; i++) {
            System.out.println("\nEntering data for cage №" + (i + 1));

            int cageNumber;
            do {
                System.out.print("Cage number: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid format. Please enter an integer.");
                    scanner.next();
                }
                cageNumber = scanner.nextInt();
                scanner.nextLine();
                if (cageNumber < 0) {
                    System.out.println("Cage number must be greater than 0. Please try again.");
                }
            } while (cageNumber < 0);

            String cageSize;
            do {
                System.out.print("Cage size: ");
                cageSize = scanner.nextLine();
                if (cageSize.trim().isEmpty()) {
                    System.out.println("Cage size cannot be empty. Please try again.");
                }
            } while (cageSize.trim().isEmpty());

            int maxAnimals;
            do {
                System.out.print("Maximum number of animals in the cage: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid format. Please enter an integer.");
                    scanner.next();
                }
                maxAnimals = scanner.nextInt();
                scanner.nextLine();
                if (maxAnimals <= 0) {
                    System.out.println("The maximum number of animals must be greater than 0. Please try again.");
                }
            } while (maxAnimals <= 0);

            Cage cage = new Cage(cageNumber, cageSize, maxAnimals);
            zoo.addCage(cage);

            int animalsCount;
            do {
                System.out.print("Enter the number of animals in the cage: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid format. Please enter an integer.");
                    scanner.next();
                }
                animalsCount = scanner.nextInt();
                scanner.nextLine();
                if (animalsCount < 0) {
                    System.out.println("The number of animals cannot be negative. Please try again.");
                } else if (animalsCount > maxAnimals) {
                    System.out.println("The number of animals cannot exceed " + maxAnimals + ". Please try again.");
                }
            } while (animalsCount < 0 || animalsCount > maxAnimals);

            for (int j = 0; j < animalsCount; j++) {
                System.out.println("\nEntering data for animal №" + (j + 1));

                String animalName;
                do {
                    System.out.print("Animal name: ");
                    animalName = scanner.nextLine();
                    if (animalName.trim().isEmpty()) {
                        System.out.println("Animal name cannot be empty. Please try again.");
                    }
                } while (animalName.trim().isEmpty());

                boolean isPredator;
                do {
                    System.out.print("Is it a predator (true/false): ");
                    while (!scanner.hasNextBoolean()) {
                        System.out.println("Invalid format. Please enter true or false.");
                        scanner.next();
                    }
                    isPredator = scanner.nextBoolean();
                    scanner.nextLine();
                } while (isPredator != true && isPredator != false);

                Animal animal = new Animal(animalName, isPredator);
                cage.addAnimal(animal);
            }
        }

        System.out.println("\nZoo Information:");
        System.out.println("Name: " + zoo.getName());
        System.out.println("Cages:");
        for (Cage cage : zoo.getCages()) {
            System.out.println("- Cage №" + cage.getNumber() + " (" + cage.getSize() + "), "
                    + cage.getCurrentAnimalsCount() + " animals");
        }

        String searchName;
        do {
            System.out.print("\nEnter the name of an animal to search: ");
            searchName = scanner.nextLine();
            if (searchName.trim().isEmpty()) {
                System.out.println("Animal name cannot be empty. Please try again.");
            }
        } while (searchName.trim().isEmpty());

        Animal foundAnimal = zoo.findAnimalByName(searchName);
        if (foundAnimal != null) {
            System.out.println(
                    "Animal found: " + foundAnimal.getName() + (foundAnimal.isPredator() ? " (predator)" : ""));
            System.out.println("Located in cage №" + foundAnimal.getCage().getNumber());
        } else {
            System.out.println("Animal not found.");
        }

        scanner.close();
    }
}