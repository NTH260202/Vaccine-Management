package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentList {
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

    public static ArrayList<Student> readStudentFromFile(String fileName) {
        ArrayList<Student> studentList = new ArrayList<>();

        try {
            openFileToRead(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Student student = createStudentFromData(data);
            studentList.add(student);
        }

        closeFileAfterRead(fileName);

        return studentList;
    }

    public static Student createStudentFromData(String data) {

        String[] tokens = data.split("\\|");

        return new Student(tokens[0], tokens[1]);
    }
}
