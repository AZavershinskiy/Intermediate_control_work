package model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryToy {
    MapperToy mapperToy;
    FileOperationToy fileOperationToy;

    public RepositoryToy(FileOperationToy fileOperationToy, MapperToy mapperToy) {
        this.mapperToy = mapperToy;
        this.fileOperationToy = fileOperationToy;
    }

    public void createToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        toy.setId(Integer.toString(newId));
        toys.add(toy);
        saveToys(toys);
    }

    public void saveToys(List<Toy> toys) {
        List<String> lines = new ArrayList<>();
        for (Toy item : toys) {
            lines.add(mapperToy.map(item));
        }
        fileOperationToy.saveAllLines(lines);
    }

    public List<Toy> getAllToys() {
        List<String> lines = fileOperationToy.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapperToy.map(line));
        }
        return toys;
    }
}
