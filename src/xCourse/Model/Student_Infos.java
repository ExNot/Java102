package xCourse.Model;

import xCourse.Helper.DBConnector;
import xCourse.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student_Infos {
    private User student;
    private String reg_courses;
    private String solved_quizz;
    private String course_rating;
    private String course_comment;


    public Student_Infos() {
    }

    public Student_Infos(User student, String reg_courses, String solved_quizz, String course_rating, String course_comment) {
        this.reg_courses = reg_courses;
        this.solved_quizz = solved_quizz;
        this.course_rating = course_rating;
        this.course_comment = course_comment;
        this.student = student;
    }

    public static ArrayList<Student_Infos> getInfos(){
        ArrayList<Student_Infos> studentInfos = new ArrayList<>();
        Student_Infos obj;
        String query = "SELECT * FROM student_infos";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Student_Infos(User.getFetch(rst.getInt("user_id")),rst.getString("reg_courses"), rst.getString("solved_quizz"), rst.getString("course_rating"), rst.getString("course_comment"));
                studentInfos.add(obj);
            }
            return studentInfos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean setInfosCourse(String selectedCourseId, User user) {

        String query = "SELECT * FROM student_infos WHERE user_id = ?";

        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,user.getId());
            ResultSet rst = pst.executeQuery();

            if (rst.next()){
                String query2 = "UPDATE student_infos SET reg_courses = CONCAT(reg_courses, ',' ?) WHERE user_id = ?";
                PreparedStatement pst2 = DBConnector.getInstance().prepareStatement(query2);
                pst2.setString(1, selectedCourseId);
                pst2.setInt(2, user.getId());

                return pst2.executeUpdate() != -1;
            }

            else {
                String query3 = "INSERT INTO student_infos (user_id, reg_courses, solved_quizz, course_rating, course_comment) VALUES (?,?,?,?,?)";
                PreparedStatement pst3 = DBConnector.getInstance().prepareStatement(query3);
                pst3.setInt(1,user.getId());
                pst3.setString(2, selectedCourseId);

                return pst3.executeUpdate() != -1;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Student_Infos getFetch(int id){


        String query = "SELECT * FROM student_infos WHERE user_id = ?";
        Student_Infos obj;
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                obj = new Student_Infos(User.getFetch(rst.getInt("user_id")), rst.getString("reg_courses"), rst.getString("solved_quizz"), rst.getString("course_rating"), rst.getString("course_comment"));
            }
            else {
                obj = null;
                Helper.showMsg("error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

    public String getReg_courses() {
        return reg_courses;
    }

    public void setReg_courses(String reg_courses) {
        this.reg_courses = reg_courses;
    }

    public String getSolved_quizz() {
        return solved_quizz;
    }

    public void setSolved_quizz(String solved_quizz) {
        this.solved_quizz = solved_quizz;
    }

    public String getCourse_rating() {
        return course_rating;
    }

    public void setCourse_rating(String course_rating) {
        this.course_rating = course_rating;
    }

    public String getCourse_comment() {
        return course_comment;
    }

    public void setCourse_comment(String course_comment) {
        this.course_comment = course_comment;
    }
}
