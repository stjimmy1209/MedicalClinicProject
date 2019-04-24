import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class ScheduleAppointments {



    public void scheduleAppointment(){

        String appID;
        String patientID;
        String staffID;
        Date appDate = null;

        while(true) {

            System.out.println("Please enter appointment ID(8-digit string): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() != 8) System.out.println("ERROR! Please provide a valid input: ");
            else {
                appID = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter patient ID(8 digit string): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() != 8) System.out.println("ERROR! Please provide a valid patient ID: ");
            else {
                patientID = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter doctor ID: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() != 8) System.out.println("ERROR! Please provide a valid doctor ID: ");
            else {
                staffID = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter appointment date(yyyy-MM-dd): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("")) System.out.println("ERROR! Please provide a valid date: ");
            else {
                try {

                    appDate = java.sql.Date.valueOf(input);

                } catch (Exception e){
                    System.err.print("Error for date parse!");
                }
                break;
            }

        }

        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/MedicalClinic",
                            "postgres", "123");
            connection.setAutoCommit(false);
            String sql = "insert into appointments values (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, appID);
            statement.setString(2, patientID);
            statement.setString(3, staffID);
            statement.setDate(4, appDate);
            statement.executeUpdate();
            connection.commit();
            connection.close();

        } catch (Exception e){

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);

        }
        System.out.println("Your info has been updated.");
        for (int i = 0; i < 3; i++) {
            System.out.println("\n");
        }




    }

    public void createPatient(){

        String patientID;
        int userID;
        String address;




    }

}
