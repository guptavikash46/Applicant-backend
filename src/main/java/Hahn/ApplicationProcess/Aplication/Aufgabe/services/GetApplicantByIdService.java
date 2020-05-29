package Hahn.ApplicationProcess.Aplication.Aufgabe.services;

import Hahn.ApplicationProcess.Aplication.Aufgabe.model.Applicant;

public interface GetApplicantByIdService {

    Applicant applicantById(int id);
    
}