package model;

import java.util.Scanner;


public class Menu {
    Scanner scanner = new Scanner(System.in);
    DataValidation dv = new DataValidation();
    InjectionList list = new InjectionList();
    
    
    public void callMenu() {
        int choice = 0;
        do{
            System.out.println("______________Vaccine management______________");
            System.out.println("Press number to choose function.");
            System.out.println("1. Show information all students have been injected");
            System.out.println("2. Add student's vaccine injection information");
            System.out.println("3. Updating information of students' vaccine injection");
            System.out.println("4. Delete student vaccine injection information");
            System.out.println("5. Search for injection information by studentID");
            System.out.println("6. Quit");
            System.out.print("Your choice: ");
            choice = dv.inputChoice(6);
            switch(choice){
                case 1:
                list.printInjectionList();
                break;
                case 2:
                list.addInjection(list.inputInjection());
                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                break;
                default:
                System.out.println("Thanks for using :>>>>");
                return;
            }

        }while(true);
    }
}
