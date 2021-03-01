package fi.arcada.android;

public class Person {

    // Vi deklarerar variabler
    private String name;
    private int age;
    private double shoeSize;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
