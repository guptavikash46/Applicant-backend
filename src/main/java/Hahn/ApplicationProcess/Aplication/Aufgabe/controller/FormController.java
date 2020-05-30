package Hahn.ApplicationProcess.Aplication.Aufgabe.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hahn.ApplicationProcess.Aplication.Aufgabe.exceptionHandling.BadRequest;
import Hahn.ApplicationProcess.Aplication.Aufgabe.model.Applicant;
import Hahn.ApplicationProcess.Aplication.Aufgabe.services.AddApplicantService;
import Hahn.ApplicationProcess.Aplication.Aufgabe.services.DeleteApplicantService;
import Hahn.ApplicationProcess.Aplication.Aufgabe.services.GetApplicantByIdService;
import Hahn.ApplicationProcess.Aplication.Aufgabe.services.UpdateApplicantService;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*")
@Validated
public class FormController {
    @Autowired
    private AddApplicantService addApplicantService;

    @Autowired
    private GetApplicantByIdService getApplicantService;

    @Autowired
    private UpdateApplicantService updateApplicantService;

    @Autowired
    private DeleteApplicantService deleteApplicantService;

    @PostMapping("/app/add-applicant")
    public ResponseEntity<Object> addApplicant(@ApiParam(value = "Your name",example ="vikas") @RequestParam(value = "name") @Size(min = 5, message = "Name must be atleast 5 characters long.") String name,
            @ApiParam(example = "vicky", value = "Your family name") @RequestParam(value = "familyName") @Size(min = 5, message = "family Name must be atleast 5 characters long.") String familyName,
            @ApiParam(example = "random address here", value = "Your Address") @RequestParam(value = "address") @Size(min = 10, message = "Address must be atleast 10 characters long.") String address, 
            @ApiParam(example = "India", value = "Your Country of Origin") @RequestParam(value = "countryOfOrigin") String countryOfOrigin,
            @ApiParam(example = "xyz@gmail.com", value = "Your email") @RequestParam(value = "emailAddress") @NotNull(message = "Email cannot be empty.") @Email(message = "Must be a valid email address including @ sign.") String emailAddress, 
            @ApiParam(example = "24", value = "Your Age") @RequestParam(value = "age") @Min(value = 20, message = "Minimum age must be more than 20")  @Max(value = 60, message = "Maximum age must be less than 60") int age,
            @ApiParam(example = "true", value = "Add your hired status") @RequestParam(value = "hired") @NotNull(message = "Hired status cannot be empty.") Boolean hired) {

        Applicant applicant = new Applicant(name, familyName, address, countryOfOrigin, emailAddress, age, hired);

        // making http request to check validity for the country name.
        int responseCode = 0;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/name/" + countryOfOrigin + "?fullText=true");
            CloseableHttpResponse responseHttp = client.execute(httpGet);
            responseCode = responseHttp.getStatusLine().getStatusCode();
            client.close();
        } catch (Exception e) {
            
        }
        if(responseCode == 200){
            addApplicantService.addApplicant(applicant);
            //response.setStatus(HttpServletResponse.SC_CREATED);
            ResponseObject responseObject = new ResponseObject("Successfully created.", 
            "http://localhost:8080/app/applicant/"+applicant.getId());
            return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
        }
        else{
            throw new BadRequest("Invalid country name");
        }
    }

    @GetMapping("/app/applicant/{id}")
    public Applicant getApplicantById(@ApiParam(value = "Enter the id", example = "3235") @PathVariable(value = "id") int id){
        Applicant result = getApplicantService.applicantById(id);
        if(result == null) throw new BadRequest("Invaild ID");
        else return result;
    }

    @PutMapping("/app/update-applicant-details/{id}")
    public ResponseEntity<Object> updateApplicantById(@ApiParam(value = "Enter the id", example = "3235") @PathVariable(value = "id") int id, 
    @ApiParam(value = "Your name",example ="vikas") @RequestParam(value = "name") @Size(min = 5, message = "Name must be atleast 5 characters long.") String name,
    @ApiParam(value = "Your family name",example ="vicky") @RequestParam(value = "familyName") @Size(min = 5, message = "family Name must be atleast 5 characters long.") String familyName,
    @ApiParam(value = "Your address",example ="my random address") @RequestParam(value = "address") @Size(min = 10, message = "Address must be 10 atleast characters long.") String address, 
    @ApiParam(value = "Your country of origin",example ="India") @RequestParam(value = "countryOfOrigin") String countryOfOrigin,
    @ApiParam(value = "Your email address",example ="random@xyz.com") @RequestParam(value = "emailAddress") @Email(message = "Must be a valid email address.") String emailAddress, 
    @ApiParam(value = "Your age",example ="23") @RequestParam(value = "age") @Min(value = 20, message = "Minimum age must be more than 20")  @Max(value = 60, message = "Maximum age must be less than 60") int age,
    @ApiParam(value = "Your hired status",example ="true") @RequestParam(value = "hired") @NotNull(message = "Hired status cannot be empty.") Boolean hired ){
        
        int responseCode = 0;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/name/" + countryOfOrigin + "?fullText=true");
            CloseableHttpResponse responseHttp = client.execute(httpGet);
            responseCode = responseHttp.getStatusLine().getStatusCode();
            client.close();
        } catch (Exception e) {
            
        }
        if(responseCode == 200){
            Integer result = updateApplicantService.updateApplicantById(id, name, familyName, address, 
            countryOfOrigin, emailAddress, age, hired);
            if(result != 1){
                throw new BadRequest("Couldn't update because the ID is invalid.");
            }
            else{
                ResponseObject responseObject = new ResponseObject("Successfully updated.", 
                "http://localhost:8080/app/applicant/"+id);
                return new ResponseEntity<>(responseObject, HttpStatus.OK);
            }
        }
        else{
            throw new BadRequest("Invalid country name");
        }

    }

    @DeleteMapping("/app/delete-applicant/{id}")
    public ResponseEntity<Object> deleteById(@ApiParam(value = "Enter the id", example = "3235") @PathVariable(value = "id") int id){
        if(deleteApplicantService.deleteApplicantById(id)){
            ResponseObject object = new ResponseObject("Successfully deleted.");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseObject("Unable to delete because no such record exists."), HttpStatus.BAD_REQUEST);
        }
        
    }
    
}