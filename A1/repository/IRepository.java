package repository;

import exceptions.NameException;
import model.animal;

import java.util.Vector;

public interface IRepository {
    animal[] getAnimals();
    void add(animal a) throws Exception;
    //void addFish(String name, float age) throws Exception;
    void deleteAnimal(String name) throws NameException;
}
