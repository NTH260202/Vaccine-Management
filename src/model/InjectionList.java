package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class InjectionList {
    ArrayList<Injection> list = new ArrayList<Injection>();

    public void addInjection(Injection injection) {
        list.add(injection);
        writeInjectionToFile(injection, "Injection.DAT");
    }

    public Injection inputInjection() {
        Injection injection = new Injection();
        DataValidation dv = new DataValidation();

        System.out.println("Injection ID: ");
        injection.setInjectId(dv.getID("ISV\\d{5}"));
        System.out.println("Student ID: ");
        injection.setStudentId(dv.getID("SE\\d{6}"));
        System.out.println("Vaccine ID: ");
        injection.setVaccineId(dv.getID("V\\d{2}"));
        System.out.println("Fist injection date: ");
        injection.setFirstInjectionDate(dv.inputFirstInjectionDate());
        System.out.println("1st injection Place");
        injection.setFirsttInjectionPlace(dv.inputNonblankString());
        System.out.println("2nd injection date: ");
        injection.setSecondInjectionDate(
                dv.inputSecondInjectionDate(dv.convertToDate(injection.getFirstInjectionDate())));
        System.out.println("2nd injection Place: ");
        injection.setSecondtInjectionPlace(dv.inputNonblankString());
        addInjection(injection);
        return injection;
    }

    public void printInjectionList() {
        ArrayList<Injection> list = readInjectionFromFile("Injection.DAT");
        for (var injection : list)
            System.out.println(injection);
    }

    // File operations
    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    static PrintWriter printWriter;
    static Scanner scanner;

    protected static void openFileToWrite(String filename) throws IOException {
        try {
            fileWriter = new FileWriter(filename, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void closeFileAfterWrite(String filename) throws IOException {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void writeInjectionToFile(Injection injection, String fileName) {

        try {
            openFileToWrite(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printWriter.println(injection.getInjectId() + "|" + injection.getStudentId() + "|" + injection.getVaccineId()
                + "|" + injection.getFirsttInjectionPlace() + "|" + injection.getFirstInjectionDate() + "|"
                + injection.getSecondInjectionDate() + "|" + injection.getSecondtInjectionPlace());

        try {
            closeFileAfterWrite(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Injection> readInjectionFromFile(String fileName) {
        ArrayList<Injection> injectionList = new ArrayList<>();

        try {
            openFileToRead(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Injection injection = createInjectionFromData(data);
            injectionList.add(injection);
        }

        closeFileAfterRead(fileName);

        return injectionList;
    }

    public static Injection createInjectionFromData(String data) {

        String[] tokens = data.split("\\|");

        Injection injection = new Injection(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                tokens[6]);

        return injection;
    }

    public static void updateFile(ArrayList<Injection> list, String fileName) throws IOException {
        File current = new File(fileName);
        if (current.exists())
            current.delete();

        try {
            openFileToWrite(fileName);
        } catch (IOException e) {
            System.out.println("e.getmessage()");

        }
        for (var injection : list) {
            printWriter
                    .println(injection.getInjectId() + "|" + injection.getStudentId() + "|" + injection.getVaccineId()
                            + "|" + injection.getFirsttInjectionPlace() + "|" + injection.getFirstInjectionDate() + "|"
                            + injection.getSecondInjectionDate() + "|" + injection.getSecondtInjectionPlace());
        }

        closeFileAfterWrite(fileName);
    }
}
