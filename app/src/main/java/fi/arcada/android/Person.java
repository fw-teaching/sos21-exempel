package fi.arcada.android;

public class Person {

    // Vi deklarerar instansvariabler
    private String name;
    private int age;
    private double shoeSize;

    // klassvariabel
    //static int personCount = 0;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

        //personCount++;
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
