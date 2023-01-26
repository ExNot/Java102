package Inharitance;

public abstract class Employee {
    private String nameSurname;
    private String mpn;
    private String email;

    public Employee(String nameSurname, String mpn, String email){

        this.nameSurname = nameSurname;
        this.mpn = mpn;
        this.email = email;

    }
    public String getNameSurname(){
        return this.nameSurname;
    }
    public void setNameSurname(String nameSurname){
        this.nameSurname = nameSurname;
    }
    public String getMpn(){
        return this.mpn;
    }
    public void setMpn(String mpn){
        this.mpn = mpn;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void enterUniversity(){
        System.out.println(this.nameSurname + " Entered the university!");
    }
    public void exitUniversity(){
        System.out.println(this.nameSurname + " Exit the university!");
    }
    public void canteen(){
        System.out.println(this.nameSurname + " is in canteen!");
    }
}
