package Inharitance;

public class Security extends Officer{
    private String document;
    public Security(String nameSurname, String mpn, String email, String department, String workHour, String document){
        super(nameSurname, mpn, email, department, workHour);
        this.document = document;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    public void shift(){
        System.out.println(this.getNameSurname() + "'s shift is: 09.00-18.00");
    }
}
