package model;

public class Vaccine {
    private String vaccineId;
    private String vaccineName;

    public Vaccine(String vaccineId, String vaccineName) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
    }

    public Vaccine() {
        
    }

    public String getVaccineId() {
        return this.vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return this.vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }



}