package Hahn.ApplicationProcess.Aplication.Aufgabe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hahn.ApplicationProcess.Aplication.Aufgabe.repository.ApplicantRepository;

@Service
public class UpdateApplicantServiceImpl implements UpdateApplicantService {

    @Autowired
    private ApplicantRepository applicantRespository;

    @Override
    public Integer updateApplicantById(int id, String name, String familyName, String address,
    String countryOfOrigin, String emailAddress, Integer age, boolean hired) {
        return applicantRespository.updateDetailsById(id, name, familyName, address,
        countryOfOrigin, emailAddress, age, hired);
    }
    
}