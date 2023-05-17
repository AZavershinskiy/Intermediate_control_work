package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import controller.ControllerToy;
import model.Toy;

public class ViewToy {
    private final ControllerToy controllerToy;
    private final ControllerToy controllerPrizeToy;
    private final ControllerToy controllerGivenOutPrizeToy;
    private Logger logger;

    public ViewToy(ControllerToy controllerToy, ControllerToy controllerPrizeToy,
            ControllerToy controllerGivenOutPrizeToy, Logger logger) {
        this.controllerToy = controllerToy;
        this.controllerPrizeToy = controllerPrizeToy;
        this.controllerGivenOutPrizeToy = controllerGivenOutPrizeToy;
        this.logger = logger;
    }

    public void run() throws Exception {
        while (true) {
            listToys(" TOYS IN THE STORE ", controllerToy.readAllToys());
            listToys(" PRIZE TOYS ", controllerPrizeToy.readAllToys());
            listToys(" GIVEN PRIZE TOYS ", controllerGivenOutPrizeToy.readAllToys());
            String cmd = prompt(
                    "\nEnter command (n - new, e - edit, r - read, d - delete, c - choose prize toy, g - give out prize toy, q - quit): ");
            logger.info("Command entered: " + cmd);
            try {
                switch (cmd.toUpperCase()) {
                    case "N":
                        createToy();
                        break;
                    case "D":
                        try {
                            deleteToy();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            this.logger.warning(e.getMessage());
                        }
                        break;
                    case "R":
                        try {
                            readToy();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            this.logger.warning(e.getMessage());
                        }
                        break;
                    case "E":
                        try {
                            editToy();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            this.logger.warning(e.getMessage());
                        }
                        break;
                    case "C":
                        choosePrizeToy();
                        break;
                    case "G":
                        giveOutPrizeToy();
                        break;
                    case "Q":
                        return;
                }
            } catch (Exception e) {
                logger.warning("Error: " + e);
            }
        }
    }

    private void listToys(String title, List<Toy> listToys) {
        int idLen = 3;
        int nameLen = 40;
        int countLen = 5;
        int dropRateLen = 3;
        int fullLen = idLen + nameLen + countLen + dropRateLen + 13;
        int titleLen = title.length();
        System.out.println("=".repeat((fullLen - titleLen) / 2) + title + "=".repeat((fullLen - titleLen) / 2));
        System.out.printf("| %3s | %-40s | %5s | %3s |\n", "ID", "Name", "Count", "d/r");
        System.out.println("-".repeat(fullLen));
        for (Toy toy : listToys) {
            String name = toy.getName();
            if (name.length() >= nameLen) {
                name = name.substring(0, nameLen - 3) + "...";
            }
            System.out.printf("| %3s | %-40s | %5d | %3d |\n", toy.getId(), name, toy.getCount(), toy.getDropRate());
        }
        System.out.println("-".repeat(fullLen));
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Toy inputToy() {
        String name = prompt("Name of the toy: ");
        String count = prompt("Amount of toys: ");
        Integer dropRate = Integer.valueOf(prompt("Toy drop rate: "));
        Toy toy = new Toy(name, Integer.valueOf(count));
        toy.setDropRate(dropRate);
        return toy;
    }

    private void createToy() throws Exception {
        controllerToy.saveToy(inputToy());
    }

    private void readToy() throws Exception {
        String toyId = getToyId("Input toy ID: ");

        Toy toy = controllerToy.readToy(toyId);
        System.out.println(toy);
    }

    private void deleteToy() throws Exception {
        String toyId = getToyId("Input ID of the toy to delete: ");
        controllerToy.deleteToy(toyId);
    }

    private void editToy() throws Exception {
        String toyId = getToyId("Input toy ID to edit: ");
        controllerToy.editToy(toyId, inputToy());
        Toy toy = controllerToy.readToy(toyId);
        System.out.println(toy);
    }

    private String getToyId(String message) {
        String readToyId = prompt(message);
        return readToyId;
    }

    public void giveOutPrizeToy() throws Exception {
        String toyId = getToyId("Input the ID of the prize toy for issuance: ");
        Toy toy = controllerPrizeToy.readToy(toyId);
        controllerPrizeToy.deleteToy(toyId);
        controllerGivenOutPrizeToy.addToy(toy);
    }

    public void choosePrizeToy() throws Exception {
        List<Toy> allToys = controllerToy.readAllToys();
        List<Integer> allDropRates = new ArrayList<>();
        Toy prizeToy = new Toy("", 0);
        Integer summDropRates = 0;
        for (int i = 0; i < allToys.size(); i++) {
            Integer dropRate = allToys.get(i).getDropRate();
            summDropRates += dropRate;
            allDropRates.add(dropRate);
        }
        List<Double> normalized = new ArrayList<>();
        for (int i = 0; i < allDropRates.size(); i++) {
            normalized.add((double) allDropRates.get(i) / (double) summDropRates);
        }
        List<Double> accumulated = new ArrayList<>();
        Double acc = 0d;
        for (int i = 0; i < normalized.size(); i++) {
            acc += normalized.get(i);
            accumulated.add(acc);
        }
        accumulated.set(accumulated.size() - 1, 1d);
        Double rand = new Random().nextDouble();
        for (int i = 0; i < accumulated.size(); i++) {
            if (rand <= accumulated.get(i)) {
                prizeToy = allToys.get(i);
                break;
            }
        }
        controllerToy.deleteToy(prizeToy.getId());
        controllerPrizeToy.addToy(prizeToy);
    }
}