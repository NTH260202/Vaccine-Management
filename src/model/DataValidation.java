package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class DataValidation {
    Scanner scanner = new Scanner(System.in);
    StudentList studentList = new StudentList();
    VaccineList vaccineList = new VaccineList();
    InjectionList injectionList = new InjectionList();
    public String inputNonblankString() {
        String result = "";
        boolean cont = false;

        do {
            try {
                result = scanner.nextLine();
                if (result.length() == 0)
                    throw new Exception("This cannot be empty! Please re-input!");
                cont = false;
            } catch (Exception e) {
                System.out.println("Input Error: " + e.getMessage());
                cont = true;
            }
        } while (cont);

        return result;
    }

    public String getID(String regex) {
        String id = null;
        boolean cont = false;
        do {
            try {
                System.out.println("Enter ID with the following regex: " + regex);
                id = scanner.nextLine();
                if (!id.matches(regex))
                    throw new Exception("ID doesn't match the regex, please check and re-input");
                cont = false;
            } catch (Exception e) {
                System.out.println("Input Error: " + e.getMessage());
                cont = true;
            }
        } while (cont);
        return id;
    }

    public String inputDateStringFormat() {
        boolean cont = false;
        String date = "";
        do {
            try {
                System.out.println("Enter date with following format: yyyy-MM-dd: ");
                date = scanner.nextLine();
                if (!date.matches("\\d{4}-\\d{2}-\\d{2}"))
                    throw new Exception("Invalid date format! Please re-input valid date!");
                cont = false;
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                cont = true;
            }

        } while (cont);

        return date;
    }

    public boolean checkDate(String date) {

        String[] dateElement = date.split("-");
        if (Integer.parseInt(dateElement[2]) < 1 || Integer.parseInt(dateElement[2]) > 31)
            return false;

        if (Integer.parseInt(dateElement[1]) == 2 && Integer.parseInt(dateElement[2]) > 29)
            return false;
        else if ((Integer.parseInt(dateElement[0]) % 4) != 0 && Integer.parseInt(dateElement[2]) == 29)
            return false;
        if (Integer.parseInt(dateElement[1]) == 4 || Integer.parseInt(dateElement[1]) == 6
                || Integer.parseInt(dateElement[1]) == 9 || Integer.parseInt(dateElement[1]) == 11) {
            if (Integer.parseInt(dateElement[2]) > 30)
                return false;
        }

        return true;
    }

    public LocalDate convertToDate(String dateIn) {
        LocalDate date = null;

        date = LocalDate.parse(dateIn);
        return date;
    }

    public int compare2Days(LocalDate day1, LocalDate day2) {
        return day1.compareTo(day2);
    }

    public boolean checkFirstInjectionDate(LocalDate injectionDate) {
        if (compare2Days(injectionDate, convertToDate("2021-03-08")) <= 0)
            return false;
        else
            return true;
    }

    public String inputFirstInjectionDate() {
        String firstInjectionDate = null;
        boolean cont = false;
        do {
            try {
                firstInjectionDate = inputDateStringFormat();
                if (!checkDate(firstInjectionDate))
                    throw new IllegalArgumentException("Invalid Date!");
                if (!checkFirstInjectionDate(convertToDate(firstInjectionDate)))
                    throw new IllegalArgumentException("Vaccine was not published before 2021-03-08");
                cont = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                cont = true;
            }
        } while (cont);

        return firstInjectionDate;
    }

    public String inputSecondInjectionDate(LocalDate firstInjectionDate) {
        String secondInjectionDate = null;
        boolean cont = false;
        do {
            try {
                secondInjectionDate = inputDateStringFormat();
                if (!checkDate(secondInjectionDate))
                    throw new IllegalArgumentException("Invalid Date!");
                if (!checkSecondInjectionDate(convertToDate(secondInjectionDate), firstInjectionDate))
                    throw new IllegalArgumentException("Second injection must be between 4 weeks and 12 weeks after first injection");
                cont = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                cont = true;
            }
        } while (cont);

        return secondInjectionDate;
    }

    public int inputPositiveInteger() {
        int result = 0;
        boolean cont = false;

        do {
            try {
                result = Integer.parseInt(scanner.nextLine());
                if (result <= 0)
                    throw new Exception("This cannot be negative or zero! Please re-input!");
                cont = false;
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                cont = true;
            }
        } while (cont);

        return result;
    }

    public int inputChoice(int max) {
        int choice = 0;
        boolean cont = false;

        do {
            try {
                System.out.println("Enter choice: ");
                choice = inputPositiveInteger();
                if (choice < 1 || choice > max)
                    throw new Exception("Invalid choice!");
                cont = false;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                cont = true;
            }
        } while (cont);
        return choice;
    }

    public boolean checkSecondInjectionDate(LocalDate secondInjectionDate, LocalDate firstInjectionDate) {
        if (compare2Days(secondInjectionDate, firstInjectionDate.plusWeeks(4)) < 0
                || compare2Days(secondInjectionDate, firstInjectionDate.plusWeeks(12)) > 0)
            return false;
        else
            return true;
    }

    public boolean checkIDExistance(String id, String fileName) {
        if(fileName.equals("Student.dat")) {
            ArrayList<Student> stdList = StudentList.readStudentFromFile(fileName);
            for(var std : stdList) {
                if(std.getStudentId().equals(id)) return true;
            }
        }
        if(fileName.equals("Vaccine.dat")) {
            ArrayList<Vaccine> vacList = VaccineList.readVaccineFromFile(fileName);
            for(var vac : vacList) {
                if(vac.getVaccineId().equals(id)) return true;
            }
        }
        if(fileName.equals("Injection.dat")) {
            ArrayList<Injection> injList = InjectionList.readInjectionFromFile(fileName);
            for(var inj : injList) {
                if(inj.getInjectId().equals(id)) return true;
            }
        }
        return false;
    }
}