package model;

public class tortoise implements animal {
    float age;
    String name;

    public float getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public tortoise(String Name, float Age)
    {
        name = Name;
        age = Age;
    }

    @Override
    public String toString()
    {
        return String.format("tortoise: " + name + ", " + age);
    }
}
