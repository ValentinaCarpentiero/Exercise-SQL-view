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
             PreparedStatement createItalianView = conn.prepareStatement(ITALIAN_VIEW);
             PreparedStatement createGermanView = conn.prepareStatement(GERMAN_VIEW);
             PreparedStatement selectFromItalianStudents = conn.prepareStatement(SELECT_ITALIAN_STUDENTS);
             PreparedStatement selectFromGermanStudents = conn.prepareStatement(SELECT_GERMAN_STUDENTS)) {

            createItalianView.executeUpdate();
            createGermanView.executeUpdate();

            ArrayList<Student> italianStudents = new ArrayList<>();
            ResultSet italianResult = selectFromItalianStudents.executeQuery();
            while (italianResult.next()) {
                italianStudents.add(new Student(italianResult.getString("first_name"), italianResult.getString("last_name")));
            }

            ArrayList<Student> germanStudents = new ArrayList<>();
            ResultSet germanResult = selectFromGermanStudents.executeQuery();
            while (germanResult.next()) {
                germanStudents.add(new Student(germanResult.getString("first_name"), germanResult.getString("last_name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}