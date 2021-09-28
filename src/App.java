public class App {
    public static void main(String[] args) throws Exception {
        DataValidation dv = new DataValidation();

        System.out.println("Test input ID ");
        String id = dv.getID("SE\\d{4}");
        System.out.println("ID : " + id);
        System.out.println("Test input 1st injection Date");
        boolean cont = false;
        String date = "";
        do{
            try {
                date = dv.inputDateStringFormat();
                if(!dv.checkDate(date)) throw new IllegalArgumentException("Invalid date");
                if(!dv.checkFirstInjectionDate(dv.convertToDate(date))) throw new IllegalArgumentException("Xao cho ha may =))) Vaccine chua co, chich mai thuy a ???");
                cont = false;
            }catch(Exception e) {
                System.out.println(e.getMessage());
                cont = true;
            }
        }while(cont);

        System.out.println("Date : " + date);
    }
}
