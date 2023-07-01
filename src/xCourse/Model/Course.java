package xCourse.Model;

import Generic.Method.Print;
import xCourse.Helper.DBConnector;
import xCourse.Helper.Helper;
import xCourse.Helper.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int path_id;
    private String name;
    private String lang;
    private Paths paths;
    private User educator;

    public Course() {
    }

    public Course(int id, int user_id, int path_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.path_id = path_id;
        this.name = name;
        this.lang = lang;

        this.paths = Paths.getFetch(path_id);
        this.educator = User.getFetch(user_id);
    }


    public static ArrayList<Course> getList(){
        ArrayList<Course> courses = new ArrayList<>();
        Course obj;
        String query = "SELECT * FROM course";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){

                obj = new Course(rst.getInt("id"), rst.getInt("user_id"), rst.getInt("path_id"),rst.getString("name"), rst.getString("lang"));
                courses.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;

    }

    public static boolean add(String name, String lang, Item path, Item edu) {

        String query = "INSERT INTO course (user_id, path_id, name, lang) VALUES (?,?,?,?)";

        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,edu.getKey());
            pst.setInt(2, path.getKey());
            pst.setString(3,name);
            pst.setString(4,lang);


            return pst.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    public static boolean updateById(int id, int eduId, int pathId, String courseName, String lang){

        String query = "UPDATE course SET user_id = ?, path_id = ?, name = ?, lang= ? WHERE id = ?";

        try {


            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);

            pst.setInt(1,eduId);
            pst.setInt(2,pathId);
            pst.setString(3,courseName);
            pst.setString(4, lang);
            pst.setInt(5,id);

            return pst.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public static ArrayList<Course> getList(int user_id){
        ArrayList<Course> courses = new ArrayList<>();
        Course obj;
        String query = "SELECT * FROM course WHERE user_id = ?";
        try {

            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1, user_id);
            ResultSet rst = pst.executeQuery();

            while (rst.next()){
                obj = new Course(rst.getInt("id"), rst.getInt("user_id"), rst.getInt("path_id"), rst.getString("name"), rst.getString("lang"));
                courses.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return courses;
    }



    public static boolean delete(int id){
        String query = "DELETE FROM course WHERE id =?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,id);
            return pst.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Course getFetch(String courseName){
        Course obj = null;
        String query = "SELECT * FROM course WHERE name LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, courseName);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                obj = new Course();
                obj.setId(rst.getInt("id"));
                obj.setEducator(User.getFetch(rst.getInt("user_id")));
                obj.setPath_id(rst.getInt("path_id"));
                obj.setPaths(Paths.getFetch(rst.getInt("path_id")));
                obj.setUser_id(rst.getInt("user_id"));
                obj.setName(rst.getString("name"));
                obj.setLang(rst.getString("lang"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }



    public static Course getFetch(int id){
        Course obj = null;
        String query = "SELECT * FROM course WHERE id LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()){

                obj = new Course();
                obj.setId(resultSet.getInt("id"));
                obj.setEducator(User.getFetch(resultSet.getInt("user_id")));
                obj.setPath_id(resultSet.getInt("path_id"));
                obj.setPaths(Paths.getFetch(resultSet.getInt("path_id")));
                obj.setUser_id(resultSet.getInt("user_id"));
                obj.setName(resultSet.getString("name"));
                obj.setLang(resultSet.getString("lang"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPath_id() {
        return path_id;
    }

    public void setPath_id(int path_id) {
        this.path_id = path_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Paths getPaths() {
        return paths;
    }

    public void setPaths(Paths paths) {
        this.paths = paths;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
