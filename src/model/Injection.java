package model;

public class Injection {
    
    private String injectId;
    private String studentId;
    private String vaccineId;
    private String firstInjectionDate;
    private String secondInjectionDate;
    private String firsttInjectionPlace;
    private String secondtInjectionPlace;

    public String getInjectId() {
        return this.injectId;
    }

    public void setInjectId(String injectId) {
        this.injectId = injectId;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVaccineId() {
        return this.vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getFirstInjectionDate() {
        return this.firstInjectionDate;
    }

    public void setFirstInjectionDate(String firstInjectionDate) {
        this.firstInjectionDate = firstInjectionDate;
    }

    public String getSecondInjectionDate() {
        return this.secondInjectionDate;
    }

    public void setSecondInjectionDate(String secondInjectionDate) {
        this.secondInjectionDate = secondInjectionDate;
    }

    public String getFirsttInjectionPlace() {
        return this.firsttInjectionPlace;
    }

    public void setFirsttInjectionPlace(String firsttInjectionPlace) {
        this.firsttInjectionPlace = firsttInjectionPlace;
    }

    public String getSecondtInjectionPlace() {
        return this.secondtInjectionPlace;
    }

    public void setSecondtInjectionPlace(String secondtInjectionPlace) {
        this.secondtInjectionPlace = secondtInjectionPlace;
    }

    public Injection() {

    }

    public Injection(String injectId, String studentId, String vaccineId
                    , String firstInjectionDate, String secondInjectionDate 
                    , String firsttInjectionPlace, String secondtInjectionPlace) {
        this.injectId = injectId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.firstInjectionDate = firstInjectionDate;
        this.secondInjectionDate = secondInjectionDate;
        this.firsttInjectionPlace = firsttInjectionPlace;
        this.secondtInjectionPlace = secondtInjectionPlace;
    }

    @Override
    public String toString() {
        return "Injection{ Injection ID: " + injectId + " Student ID: " + studentId + " Vaccine ID: " + vaccineId + " First Injection Date: " + firstInjectionDate + " Second Injection Date: " + secondInjectionDate + 
                " First Injection Place: " + firstInjectionDate + " Second Injection Place:" + secondtInjectionPlace + "}";
    }
}
