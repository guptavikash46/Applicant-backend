package Hahn.ApplicationProcess.Aplication.Aufgabe.controller;

public class ResponseObject {

    String message;
    String url;

    public ResponseObject(){}

    public ResponseObject(String msg, String url){
        this.message = msg;
        this.url = url;
    }
    public ResponseObject(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
        
}