package Hahn.ApplicationProcess.Aplication.Aufgabe.exceptionHandling;

class ApiValidationError {
   private String object;
   private String message;

   public ApiValidationError(String object, String message) {
       this.object = object;
       this.message = message;
   }

   public String getObject() {
       return object;
   }

   public void setObject(String object) {
       this.object = object;
   }

   public String getMessage() {
       return message;
   }

   public void setMessage(String message) {
       this.message = message;
   }
   
}