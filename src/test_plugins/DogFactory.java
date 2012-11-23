package test_plugins;

public class DogFactory {
    public Dog dog = new Dog("Misiek");

    public Dog getDog() {
        return dog;
    }

    class Dog {
        String name;

        public Dog(String name) {
            this.name = name;
        }

        public void bark() {
            System.out.println("WHOW, WHOW!");
        }
    }
}
