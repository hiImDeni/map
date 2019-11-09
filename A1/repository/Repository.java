package repository;

import exceptions.NameException;
import model.*;

import java.util.Vector;

public class Repository implements IRepository{
    //animal[] animals = new animal [100];
    animal[] animals;
    int len;

    public Repository() { len = 0; animals = new animal[100]; }

    public animal[] getAnimals() {
        animal[] copy = new animal[len];
        for (int i = 0; i < len; i++)
            copy[i] = animals[i];
        return copy;
    }
    //public int genLength() { return len; }

    public void add(animal newAnimal) throws Exception {
        for (int i = 0; i < len; i++)
            if (newAnimal.getName().equals(animals[i].getName()))
                throw new Exception("Animal already exists!");
        animals[len] = newAnimal;
        len++;
    }

    public void deleteAnimal(String name) throws NameException {
        for(int i = 0; i < len; i++)
            if (animals[i].getName().equals(name))
            {
                animals[i] = animals[len];
                len--;
                return;
            }
        throw new NameException("Name doesn't exist!");
    }
}
