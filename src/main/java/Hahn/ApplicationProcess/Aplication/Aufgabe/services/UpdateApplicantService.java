package Hahn.ApplicationProcess.Aplication.Aufgabe.services;

public interface UpdateApplicantService {

    Integer updateApplicantById(int id, String name, String familyName, String address,
    String countryOfOrigin, String emailAddress, Integer age, boolean hired);
    
}