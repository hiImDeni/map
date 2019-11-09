package model;

public class fish implements animal{
    String name;
    float age;

    public fish(String Name, float Age){
        name = Name;
        age = Age;
    }

    public float getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString()
    {
        return String.format("fish: " + name + ", " + age);
    }
}
