package Enums;

public enum Days {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private int day;

    Days(int day){
        this.day = day;
    }

    public int getDay() {
        return day;
    }


    private String getDayName(){

        String dayName = switch (this.day){

          case 1 -> dayName = "MONDAY";
          case 2 -> dayName = "TUESDAY";
          case 3 -> dayName = "WEDNESDAY";
          case 4 -> dayName = "THURSDAY";
          case 5 -> dayName = "FRIDAY";
          case 6 -> dayName = "SATURDAY";
          case 7 -> dayName = "SUNDAY";

            default -> throw new IllegalStateException("Unexpected value: " + this.day);
        };

        return dayName;
    }



    /*without enum*/
    public void getDayNum(){

        int DayNum = switch (this.getDayName()){

            case "MONDAY" -> 1;
            case "TUESDAY" -> 2;
            case "WEDNESDAY" -> 3;
            case "THURSDAY" -> 4;
            case "FRIDAY" -> 5;
            case "SATURDAY" -> 6;
            case "SUNDAY1" -> 7;


            default -> throw new IllegalStateException("Unexpected value: " + this.day);
        };

    }

}
