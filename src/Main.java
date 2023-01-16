import java.sql.*;
import java.util.ArrayList;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/newdb";
    static final String USER = "developer";
    static final String PASSWORD = "userpasw";
    static final String ITALIAN_VIEW = "CREATE VIEW italian_students AS SELECT first_name, last_name FROM students WHERE country = 'Italy'";
    static final String GERMAN_VIEW = "CREATE VIEW german_students AS SELECT first_name, last_name FROM students WHERE country = 'Germany'";
    static final String SELECT_ITALIAN_STUDENTS = "SELECT * FROM italian_students";
    static final String SELECT_GERMAN_STUDENTS = "SELECT * FROM german_students";


    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            statement.executeUpdate(ITALIAN_VIEW);
            statement.executeUpdate(GERMAN_VIEW);

            ArrayList<Student> italianStudents = new ArrayList<>();
            ResultSet italianResult = statement.executeQuery(SELECT_ITALIAN_STUDENTS);
            while (italianResult.next()) {
                italianStudents.add(new Student(italianResult.getString("first_name"), italianResult.getString("last_name")));
            }

            ArrayList<Student> germanStudents = new ArrayList<>();
            ResultSet germanResult = statement.executeQuery(SELECT_GERMAN_STUDENTS);
            while (germanResult.next()) {
                germanStudents.add(new Student(germanResult.getString("first_name"), germanResult.getString("last_name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}