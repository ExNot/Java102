package xCourse.Model;

import xCourse.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Paths {
    private int Id;
    private String name;

    public Paths(int id, String name) {
        this.Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Paths> getList(){
        ArrayList<Paths> pathList = new ArrayList<>();
        Paths obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM paths");
            while (rst.next()){
                obj = new Paths(rst.getInt("id"), rst.getString("pathname"));
                pathList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pathList;
    }

    public static boolean addPath(String name){

        String addPathQuery = "INSERT INTO paths (pathname) VALUES (?)";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(addPathQuery);
            pst.setString(1, name);
            return pst.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }


}
