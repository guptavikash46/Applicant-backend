package Hahn.ApplicationProcess.Aplication.Aufgabe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hahn.ApplicationProcess.Aplication.Aufgabe.model.Applicant;
import Hahn.ApplicationProcess.Aplication.Aufgabe.repository.ApplicantRepository;

@Service
public class GetApplicantByIdServiceImpl implements GetApplicantByIdService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public Applicant applicantById(int id) {
        return applicantRepository.getApplicantById(id);
    }
    
}