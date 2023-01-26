package Inharitance;

public class IT extends Officer{
    private String mission;
    public IT(String nameSurname, String mpn, String email, String department,String workHour, String mission){
        super(nameSurname, mpn, email, department, workHour);
        this.mission = mission;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }
    public void networkConfig(){
        System.out.println(this.getNameSurname() + " is configurating the network");
    }
}
