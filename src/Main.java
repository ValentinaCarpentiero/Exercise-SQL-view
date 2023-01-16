import java.sql.*;
import java.util.ArrayList;

public class Main {
    static final String DB_NAME = "newdb";
    static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    static final String USER = "developer";
    static final String PASSWORD = "userpasw";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            statement.executeUpdate("CREATE VIEW italian_students AS SELECT first_name, last_name FROM students WHERE country = 'Italy'");
            statement.executeUpdate("CREATE VIEW german_students AS SELECT first_name, last_name FROM students WHERE country = 'Germany'");

            ArrayList<Student> italianStudents = new ArrayList<>();
            ResultSet italianResult = statement.executeQuery("SELECT * FROM italian_students");
            while (italianResult.next()) {
                italianStudents.add(new Student(italianResult.getString("first_name"), italianResult.getString("last_name")));
            }

            ArrayList<Student> germanStudents = new ArrayList<>();
            ResultSet germanResult = statement.executeQuery("SELECT * FROM german_students");
            while (germanResult.next()) {
                germanStudents.add(new Student(germanResult.getString("first_name"), germanResult.getString("last_name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}