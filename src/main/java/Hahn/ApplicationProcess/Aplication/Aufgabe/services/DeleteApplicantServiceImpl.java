package Hahn.ApplicationProcess.Aplication.Aufgabe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hahn.ApplicationProcess.Aplication.Aufgabe.model.Applicant;
import Hahn.ApplicationProcess.Aplication.Aufgabe.repository.ApplicantRepository;

@Service
public class DeleteApplicantServiceImpl implements DeleteApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public boolean deleteApplicantById(Integer Id) {
        Applicant applicantObj = applicantRepository.getApplicantById(Id);
        if(applicantObj != null){
            applicantRepository.deleteById(Id);
            return true;
        }
        return false;
    }
    
}