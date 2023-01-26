package Inharitance;

public  abstract class Officer extends Employee{
    private String department;
    private String workHour;
    public Officer(String nameSurname, String mpn, String email, String department, String workHour){
        super(nameSurname, mpn, email);
        this.department = department;
        this.workHour = workHour;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkHour() {
        return workHour;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    public void work(){
        System.out.println(this.getNameSurname()+ " is working!");
    }
}
