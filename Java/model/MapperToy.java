package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperToy {
    public String map(Toy toy) {
        return String.format("%s;%s;%d;%d", toy.getId(), toy.getName(), toy.getCount(), toy.getDropRate());
    }

    public Toy map(String line) {
        List<String> lines = new ArrayList<String>(Arrays.asList(line.split(";")));
        Toy toy = new Toy(lines.get(0), lines.get(1), Integer.parseInt(lines.get(2)), Integer.parseInt(lines.get(3)));
        return toy;
    }
}