package test_plugins;

import java.util.LinkedList;
import java.util.List;

public class DogContainer {
    private List<String> names;
    private int numberOfDogs;

    public DogContainer() {
        this.names = new LinkedList<String>();
        names.add("Misiek");
        names.add("Burek");
        names.add("Tofik");
        this.numberOfDogs = 3;
    }

    public List<String> getNames() {
        return this.names;
    }

    public void add(DogContainer anotherDogContainer) {
        this.names.addAll(anotherDogContainer.names);
        this.numberOfDogs += anotherDogContainer.numberOfDogs;
    }

    public DogContainer getDogContainer() {
        return this;
    }

    public int getNumberOfDogs() {
        return this.numberOfDogs;
    }
}
