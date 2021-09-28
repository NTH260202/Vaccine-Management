import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class DataValidation {
    Scanner scanner = new Scanner(System.in);
    
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
        do{
            try{
                System.out.println("Enter ID with the following regex: " + regex);
                id = scanner.nextLine();
                if(!id.matches(regex)) throw new Exception("ID doesn't match the regex, please check and re-input");
                cont = false;
            }catch (Exception e){
                System.out.println("Input Error: " + e.getMessage());
                cont = true;
            }
        }while(cont);
        return id;
    }

    public String inputDateStringFormat() {
        boolean cont = false;
        String date = "";
        do {
            try {
                System.out.println("Enter date with following format: dd MM yyyy: ");
                date = scanner.nextLine();
                if (!date.matches("\\d{2} \\d{2} \\d{4}"))
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

        String[] dateElement = date.split(" ");
        if (Integer.parseInt(dateElement[0]) < 1 || Integer.parseInt(dateElement[0]) > 31)
            return false;

        if (Integer.parseInt(dateElement[1]) == 2 && Integer.parseInt(dateElement[0]) > 29)
            return false;
        else if ((Integer.parseInt(dateElement[2]) % 4) != 0 && Integer.parseInt(dateElement[0]) == 29)
            return false;
        if (Integer.parseInt(dateElement[1]) == 4 || Integer.parseInt(dateElement[1]) == 6
                || Integer.parseInt(dateElement[1]) == 9 || Integer.parseInt(dateElement[1]) == 11) {
            if (Integer.parseInt(dateElement[0]) > 30)
                return false;
        }

        return true;
    }

    public Date convertToDate(String dateIn) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");

        try {
            date = sdf.parse(dateIn);
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return date;
    }

    public int compare2Days(Date day1, Date day2) {
        return day1.compareTo(day2);
    }

    public boolean checkFirstInjectionDate(Date injectionDate) {
        if(compare2Days(injectionDate, convertToDate("08 03 2021")) <= 0) return false;
            else return true;
    }

    public boolean checkExistance() {
        return true;
    }
}