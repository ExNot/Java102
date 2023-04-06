package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    public static final String DB_URL= "jdbc:mysql://localhost/university";
    public static final String DB_USER= "root";
    public static final String DB_PASSWORD = "Mysql1453.";



    public static void main(String[] args) {


        String InQuery = "INSERT INTO student (student_name, student_class) VALUES(?,?)";
        try {
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            connection.setAutoCommit(false); // yazdığımız queryleri direkt eklemez, queryleri hafızaya alır commit dediğimizde
            //birlikte ekler böylelikle ilişkili datalar birlikte yollanır!

            PreparedStatement preparedStatement = connection.prepareStatement(InQuery);

            preparedStatement.setInt(2,1);
            preparedStatement.setString(1,"Dolores");
            preparedStatement.executeUpdate();




            preparedStatement.setString(1,"Alice");
            preparedStatement.setInt(2,3);
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
