package Inharitance;

public class LabAsisstant extends Assistant{
    public LabAsisstant(String nameSurname, String mpn, String email, String department, String title, String officeHour){
        super(nameSurname, mpn, email, department, title, officeHour);
    }
    public void enterLabs(){
        System.out.println(this.getNameSurname() + " Enter the lab!");
    }
    public void attendClass(){
        System.out.println(this.getNameSurname() + " attend the class!");
    }

    @Override
    public void attendClass(String classHour) {

    }
}
