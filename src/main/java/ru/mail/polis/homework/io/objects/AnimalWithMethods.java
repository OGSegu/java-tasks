package ru.mail.polis.homework.io.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Дубль класса Animal, для Serializer.serializeWithMethods
 * 3 балла
 */
public class AnimalWithMethods implements Serializable {

    private AnimalKind animalKind = AnimalKind.UNKNOWN;
    private String name = "unknown";
    private int age;
    private int weight;
    private List<String> locationsList = new ArrayList<>();
    private Colour colour = Colour.UNKNOWN;
    private Parents parents = new Parents();

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(animalKind);
        out.writeUTF(name);
        out.writeInt(age);
        out.writeInt(weight);
        out.writeObject(locationsList);
        out.writeObject(colour);
        out.writeObject(parents);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        animalKind = (AnimalKind) in.readObject();
        name = in.readUTF();
        age = in.readInt();
        weight = in.readInt();
        locationsList = (List<String>) in.readObject();
        colour = (Colour) in.readObject();
        parents = (Parents) in.readObject();
    }

    public static AnimalWithMethods getRandom(Random random) {
        return newBuilder().getRandom(random);
    }

    public static Builder newBuilder() {
        return new AnimalWithMethods().new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalWithMethods animal = (AnimalWithMethods) o;
        return age == animal.age &&
                weight == animal.weight &&
                animalKind == animal.animalKind &&
                Objects.equals(name, animal.name) &&
                Objects.equals(locationsList, animal.locationsList) &&
                colour == animal.colour &&
                Objects.equals(parents, animal.parents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalKind, name, age, weight, locationsList, colour, parents);
    }

    @Override
    public String toString() {
        return "AnimalWithMethods{" +
                "animalKind=" + animalKind +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", locationsList=" + locationsList +
                ", colour=" + colour +
                ", parents=" + parents +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        private AnimalWithMethods getRandom(Random random) {
            setAnimalKind(AnimalKind.getRandom(random));
            setName(Utils.getRandomString(random, 10));
            setAge(random.nextInt(99));
            setWeight(random.nextInt(500));
            for (int i = 0; i < random.nextInt(20); i++)
                addLocations(Utils.getRandomString(random, 12));
            setColour(Colour.getRandom(random));
            setParents(new Parents(random.nextBoolean(), random.nextBoolean()));
            return build();
        }

        public Builder setAnimalKind(AnimalKind animalKind) {
            AnimalWithMethods.this.animalKind = animalKind;
            return this;
        }

        public Builder setName(String name) {
            AnimalWithMethods.this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            AnimalWithMethods.this.age = age;
            return this;
        }

        public Builder setWeight(int weight) {
            AnimalWithMethods.this.weight = weight;
            return this;
        }

        public Builder addLocations(String... locations) {
            AnimalWithMethods.this.locationsList.addAll(Arrays.asList(locations));
            return this;
        }

        public Builder setLocationList(List<String> locationList) {
            AnimalWithMethods.this.locationsList = locationList;
            return this;
        }

        public Builder setColour(Colour colour) {
            AnimalWithMethods.this.colour = colour;
            return this;
        }

        public Builder setParents(Parents parents) {
            AnimalWithMethods.this.parents = parents;
            return this;
        }

        public AnimalWithMethods build() {
            return AnimalWithMethods.this;
        }

    }
}
