package Inharitance;

public class Assistant extends Academician{
    private String officeHour;
    public Assistant(String nameSurname, String mpn, String email, String department, String title, String officeHour){
        super(nameSurname, mpn, email, department, title);
        this.officeHour = officeHour;
    }

    public void setOfficeHour(String officeHour) {
        this.officeHour = officeHour;
    }

    public String getOfficeHour(){
        return this.officeHour;
    }
    public void makeQuiz(){
        System.out.println(this.getNameSurname() + " is making Quiz!");
    }

    @Override
    public void attendClass(String classHour) {

    }
}
