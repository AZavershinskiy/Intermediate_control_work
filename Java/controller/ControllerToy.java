package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.RepositoryToy;
import model.Toy;

public class ControllerToy {
    RepositoryToy repositoryToy;
    Logger logger;

    public ControllerToy(RepositoryToy repositoryToy, Logger logger) {
        this.repositoryToy = repositoryToy;
        this.logger = logger;
    }

    public List<Toy> readAllToys() {
        return repositoryToy.getAllToys();
    }

    public void saveToy(Toy toy) throws Exception {
        repositoryToy.createToy(toy);
        this.logger.info("Save toy " + '(' + toy.getName() + ')');
    }

    public Toy readToy(String toyId) throws Exception {
        List<Toy> toys = repositoryToy.getAllToys();
        Toy toy = toySearch(toyId, toys);
        this.logger.info("Read toy " + '(' + toy.getName() + ')');
        return toy;
    }

    private Toy toySearch(String toyId, List<Toy> toys) throws Exception {
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    public void deleteToy(String toyId) throws Exception {
        Toy nameToy = readToy(toyId);
        List<Toy> toys = repositoryToy.getAllToys();
        List<Toy> newToys = new ArrayList<>();
        for (Toy toy : toys) {
            String tempId = toy.getId();
            Integer tempCount = toy.getCount();
            if (tempId.equals(toyId)) {
                if (tempCount > 1) {
                    toy.decCount();
                    newToys.add(toy);
                }
            } else {
                newToys.add(toy);
            }
        }
        repositoryToy.saveToys(newToys);
        this.logger.info("Delete toy " + '(' + nameToy.getName() + ')');
    }

    public void editToy(String toyId, Toy newToy) throws Exception {
        List<Toy> toys = repositoryToy.getAllToys();
        Toy toy = toySearch(toyId, toys);
        toy.setName(newToy.getName());
        toy.setCount(newToy.getCount());
        toy.setDropRate(newToy.getDropRate());
        repositoryToy.saveToys(toys);
        logger.info("Edit toy " + '(' + toy.getName() + ')');
    }

    public void addToy(Toy toy) {
        String toyId = toy.getId();
        List<Toy> toys = repositoryToy.getAllToys();
        List<Toy> newToys = new ArrayList<>();
        Boolean added = false;
        for (Toy item : toys) {
            String tempId = item.getId();
            String tempName = item.getName();
            if (tempId.equals(toyId) && tempName.equals(toy.getName())) {
                item.incCount();
                newToys.add(item);
                added = true;
            } else {
                newToys.add(item);
            }
        }
        if (!added) {
            toy.setCount(1);
            newToys.add(toy);
        }
        repositoryToy.saveToys(newToys);
        this.logger.info("Delete toy " + '(' + toy.getName() + ')');
    }
}