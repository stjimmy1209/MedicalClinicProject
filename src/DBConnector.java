import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class DBConnector {

    public static Connection connectToDB() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/MedicalClinic",
                            "postgres", "123");
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return connection;


    }

}
