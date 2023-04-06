package JDBC;

import java.sql.*;

public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/university";
    public static final String DB_USER= "root";
    public static final String DB_PASSWORD="Mysql1453.";

    public static void main(String[] args) {

        Connection connection = null;

        String sqlQuery = "SELECT * FROM student";
        String sqlInsert = "INSERT INTO student (student_name, student_class) VALUES ('Damla' ,4)";
        String preInsert = "INSERT INTO student (student_name, student_class) VALUES (? ,?)";
        String preInsert2= "UPDATE student SET student_id= ? WHERE student_id = ? ";
        String preInsert3 = "DELETE FROM student WHERE student_id = ?";
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            /*ResultSet resultSet = statement.executeQuery(sqlQuery);*/

            /*statement.executeUpdate(sqlInsert);*/


            /*PreparedStatement preparedStatement = connection.prepareStatement(preInsert);
            preparedStatement.setString(1,"Naz");
            preparedStatement.setInt(2,4);
            preparedStatement.executeUpdate();
            preparedStatement.close();*/


            /*PreparedStatement preparedStatement = connection.prepareStatement(preInsert2);
            preparedStatement.setInt(1,4);
            preparedStatement.setInt(2,5);
            preparedStatement.executeUpdate();
            preparedStatement.close();*/

            /*PreparedStatement preparedStatement = connection.prepareStatement(preInsert3);
            preparedStatement.setInt(1,4);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();*/




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}
