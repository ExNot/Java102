package Inharitance;

public abstract class Academician extends Employee{
    private String department;
    private String title;
    public Academician(String nameSurname, String mpn, String email, String department, String title){
        super(nameSurname, mpn, email);
        this.department = department;
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void attendClass(String classHour);
    @Override
    public void enterUniversity(){
        System.out.println(this.getNameSurname() + " Entered the university! from door A");
    }
}
