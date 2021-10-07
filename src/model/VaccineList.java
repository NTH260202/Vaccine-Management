package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class VaccineList {
    static Scanner scanner = new Scanner(System.in);
    protected static void openFileToRead(String fileName) throws IOException {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void closeFileAfterRead(String fileName) {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Vaccine> readVaccineFromFile(String fileName) {
        ArrayList<Vaccine> vaccineList = new ArrayList<>();

        try {
            openFileToRead(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Vaccine vaccine = createVaccineFromData(data);
            vaccineList.add(vaccine);
        }

        closeFileAfterRead(fileName);

        return vaccineList;
    }

    public static Vaccine createVaccineFromData(String data) {

        String[] tokens = data.split("\\|");

        return new Vaccine(tokens[0], tokens[1]);
    }
}
