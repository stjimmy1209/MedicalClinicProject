import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ViewFunctions {

    public static void viewPatient(){


        String firstName = "";
        String lastName = "";
        String address = "";

        System.out.println("Please enter the patient_id that you want to check: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();

        Connection connection = DBConnector.connectToDB();

        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users natural join patient where patient_id=" + "\'" + id + "\';";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                address = resultSet.getString("address");

            }

            System.out.println("Data retrieved: ");
            System.out.println("Patient ID: " + id);
            System.out.println("First name: " + firstName);
            System.out.println("Last name: " + lastName);
            System.out.println("Address: " + address);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e){

            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);

        }

    }

}
