package xCourse.Model;

import xCourse.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quizz {

    private String quizz_quest;
    private int edu_id;
    private int lesson_id;
    private int id;



    public Quizz(int id, String quizz_quest, int edu_id, int lesson_id) {
        this.id = id;
        this.quizz_quest = quizz_quest;
        this.edu_id = edu_id;
        this.lesson_id = lesson_id;
    }

    public static ArrayList<Quizz> getList(){
        ArrayList<Quizz> quizzes = new ArrayList<>();
        Quizz obj;
        String query = "SELECT * FROM quizz";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Quizz(rst.getInt("ID"), rst.getString("quizz_quest"), rst.getInt("educator"), rst.getInt("lesson_id"));
                quizzes.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;


    }

    public static boolean add(String quizzQuestion, int lesson_id, int edu_id) {

        String query = "INSERT INTO quizz(quizz_quest, educator, lesson_id) VALUES(?,?,?)";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1,quizzQuestion);
            pst.setInt(2,edu_id);
            pst.setInt(3,lesson_id);

            return pst.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void delete(int lessonId) {

        String query = "DELETE FROM quizz WHERE lesson_id = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1, lessonId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void delete(String quizz_quest){
        String query = "DELETE FROM quizz WHERE quizz_quest =?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1,quizz_quest);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getQuizz_quest() {
        return quizz_quest;
    }

    public void setQuizz_quest(String quizz_quest) {
        this.quizz_quest = quizz_quest;
    }

    public int getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(int edu_id) {
        this.edu_id = edu_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
