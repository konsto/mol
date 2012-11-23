package test_plugins;

public class Dog {
    String name;
    Number number;

    public Dog(String name) {
        this.name = name;
        this.number = 0;
    }

    public Dog() {
        this("Misiek");
    }

    public void bark() {
        System.out.println("WHOW, WHOW!");
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Number getNumber() {
        return this.number;
    }
    
    public String present() {
        return new String("Jestem psem. Nazywam sie " + this.name);
    }
}