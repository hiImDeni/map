package controller;

import exceptions.NameException;
import exceptions.TypeException;
import model.*;
import repository.IRepository;
import repository.Repository;

import java.util.Vector;

public class Controller {
    IRepository repo;
    int currentLength;

    public Controller() {
        repo = new Repository();
        currentLength = 0;
    }

    public Controller(IRepository r) { repo = r; currentLength = 0; }

    public void add(String type, String name, float age) throws Exception
    {
        animal a;
        if (type.equals("tortoise"))
            a = new tortoise(name, age);
        else if (type.equals("fish"))
            a= new fish(name, age);
        else
            throw new TypeException("Type " + type + " doesn't exist");
        repo.add(a);
    }

    public animal[] filterByAge(Float age)
    {
        //int len = repo.genLength();
        animal[] filterArray = new animal[getAnimals().length];
        //int currentLength = 0;
        for (animal i : repo.getAnimals())
        {
            if (i.getAge() > age)
            {
                filterArray[currentLength] = i;
                currentLength ++;
            }
        }
        return filterArray;
    }

    public int getFilterSize() { return currentLength; }

    public animal[] getAnimals() { return repo.getAnimals(); }

    public void remove(String name) throws NameException
    {
        repo.deleteAnimal(name);
    }
}
