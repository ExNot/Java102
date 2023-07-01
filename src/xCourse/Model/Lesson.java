package xCourse.Model;

import xCourse.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lesson {
    private int id;
    private String title;
    private String youtube;
    private int course_id;
    private Course course;
    private String description;
    private User educator;

    public Lesson(int id, String title, String youtube, int course_id, String description, int educator_id) {
        this.id = id;
        this.title = title;
        this.youtube = youtube;
        this.description = description;

        this.course_id = course_id;

        this.course = Course.getFetch(course_id);
        this.educator = User.getFetch(educator_id);
    }

    public Lesson() {
    }

    public static ArrayList<Lesson> getList() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {

                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lessons;

    }

    public static Lesson getFetchByTitle(String title){

        Lesson obj = null;
        String query = "SELECT * FROM lesson WHERE title = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1,title);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return obj;

    }

    public static Lesson getFetch(int id){

        Lesson obj = null;
        String query = "SELECT * FROM lesson WHERE id =?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,id);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }


    public static ArrayList<Lesson> searchBy123(String des, String tit, int course_id) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE description LIKE ? AND title LIKE ? AND course =?";

        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, des);
            pst.setString(2, tit);
            pst.setInt(3, course_id);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public static ArrayList<Lesson> searchBy23(String searchLessonTitle, int courseId) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE title LIKE ? AND course = ?";

        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);

            pst.setString(1, searchLessonTitle);
            pst.setInt(2, courseId);
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {

                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;

    }

    public static ArrayList<Lesson> searchBy13(String searchLessonDes, int courseId) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE description LIKE ? AND course = ?";

        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, searchLessonDes);
            pst.setInt(2, courseId);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {

                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;

    }

    public static ArrayList<Lesson> searchBy12(String des, String title) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE description LIKE ? AND title LIKE ?";

        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1,des);
            pst.setString(2,title);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public static ArrayList<Lesson> searchBy1(String searchLessonDes) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE description LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, searchLessonDes);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lessons;
    }

    public static ArrayList<Lesson> searchBy2(String searchLessonTitle) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE title LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1,searchLessonTitle);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public static ArrayList<Lesson> searchBy3(int courseId) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson obj;
        String query = "SELECT * FROM lesson WHERE course = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,courseId);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                obj = new Lesson(rst.getInt("id"), rst.getString("title"), rst.getString("youtube"), rst.getInt("course"), rst.getString("description"), rst.getInt("educator_id"));
                lessons.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public static boolean add(String title, String description, String youtube, int course_id, int id) {

        String query = "INSERT INTO lesson (educator_id, title, youtube, course, description) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,id);
            pst.setString(2,title);
            pst.setString(3,youtube);
            pst.setInt(4,course_id);
            pst.setString(5,description);

            return pst.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(String title) {

        String query = "DELETE FROM lesson WHERE title=?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1,title);
            return pst.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }








    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
