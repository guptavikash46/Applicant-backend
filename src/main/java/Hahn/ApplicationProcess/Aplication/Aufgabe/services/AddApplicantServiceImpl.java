package Hahn.ApplicationProcess.Aplication.Aufgabe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hahn.ApplicationProcess.Aplication.Aufgabe.model.Applicant;
import Hahn.ApplicationProcess.Aplication.Aufgabe.repository.ApplicantRepository;

@Service
public class AddApplicantServiceImpl implements AddApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public void addApplicant(Applicant applicant) {
        applicantRepository.save(applicant);
    }
    
}