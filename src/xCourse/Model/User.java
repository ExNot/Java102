package xCourse.Model;

import xCourse.Helper.DBConnector;
import xCourse.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String userName;
    private String password;
    private String type;


    public User(){

    }

    public User(int id, String name, String userName, String password, String type) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList(){

        String ALL_USERS_QUERY = "SELECT * FROM user";
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(ALL_USERS_QUERY);

            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUserName(rs.getString("username"));
                obj.setType(rs.getString("type"));
                obj.setPassword(rs.getString("pass"));
                userList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }
    public static boolean add(String name, String userName, String password, String type){

        String pstQuery = "INSERT INTO user (name, username, pass, type) VALUES (?,?,?,?)";
        User findUser = User.getFetch(userName);
        if (findUser != null){
            Helper.showMsg("this username is used!!");
            return false;
        }


        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(pstQuery);
            pst.setString(1,name);
            pst.setString(2,userName);
            pst.setString(3,password);
            pst.setString(4,type);

            int response = pst.executeUpdate();

            if (response == -1){
                Helper.showMsg("error");
            }

            return response != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }

    public static User getFetch(String uname){
        User obj = null;
        String query = "SELECT * FROM user WHERE username LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, uname);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()){

                obj = new User();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUserName(resultSet.getString("username"));
                obj.setType(resultSet.getString("type"));
                obj.setPassword(resultSet.getString("pass"));


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean deleteById(int id){

        String query = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setInt(1,id);
            return pst.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean updateById(int id, String name, String userName, String password, String type){

        String query = "UPDATE user SET name = ?, username = ?, pass = ?, type= ? WHERE id = ?";

        User findUser = User.getFetch(userName);
        if (findUser != null && findUser.getId() != id){
            Helper.showMsg("this username is used!!");
            return false;
        }




        try {


            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2,userName);
            pst.setString(3, password);

            pst.setInt(5, id);
            if (type.equals("operator") || type.equals("student") || type.equals("educator")){
                pst.setString(4, type);
            }
            else {
                Helper.showMsg("You can not choose invalid Account Type!");
                return false;
            }

            return pst.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<User> searchUserList(String schQuey){

        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(schQuey);

            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUserName(rs.getString("username"));
                obj.setType(rs.getString("type"));
                obj.setPassword(rs.getString("pass"));
                userList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;

    }

    public static String searchQuery(String name, String userName, String type){

        String query = "SELECT * FROM user WHERE username LIKE '%{{username}}%' AND name LIKE '%{{name}}%' AND type LIKE '{{type}}'";

        query = query.replace("{{username}}", userName);
        query = query.replace("{{name}}", name);

        if (type.equals(""))
        {
            query = query.replace("{{type}}", "%%");
        }
        else
            query = query.replace("{{type}}", type);

        return query;
    }


}



