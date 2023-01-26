package Inharitance;

public class Lecturer extends Academician{
    private String doorNum;
    public Lecturer(String nameSurname, String mpn, String email, String department, String title, String doorNum){
        super(nameSurname, mpn, email, department, title);
        this.doorNum = doorNum;
    }
    public String getDoorNum(){
        return this.doorNum;
    }
    public void setDoorNum(String doorNum){
        this.doorNum = doorNum;
    }
    public void senateMeeting(){
        System.out.println(this.getNameSurname() + " in senate meeting!");
    }
    public void exam(){
        System.out.println(this.getNameSurname() + " making exam!");
    }
    @Override
    public void attendClass(String hour){
        System.out.println(this.getNameSurname() + " is attended class at " + hour);
    }
}
