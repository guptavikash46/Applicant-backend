package Hahn.ApplicationProcess.Aplication.Aufgabe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Hahn.ApplicationProcess.Aplication.Aufgabe.model.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    
    @Query("SELECT a from Applicant a WHERE a.id =:ID")
    Applicant getApplicantById(@Param("ID") Integer ID);

    @Transactional
    @Modifying
    @Query("UPDATE Applicant a SET a.name =:name, a.familyName =:familyName, a.address =:address, a.countryOfOrigin =:countryOfOrigin, a.emailAddress =:emailAddress, a.age =:age, a.hired =:hired WHERE a.id =:ID")
    Integer updateDetailsById(@Param("ID") Integer ID , @Param("name") String name,
    @Param("familyName") String familyName, @Param("address") String address,
    @Param("countryOfOrigin") String countryOfOrigin, @Param("emailAddress") String emailAddress,
    @Param("age") Integer age, @Param("hired") Boolean hired);
}