import com.sun.corba.se.spi.orbutil.fsm.State;
import sun.nio.ch.sctp.SctpNet;

import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;

public class UpdateFunctions {



    public static void scheduleAppointment(){

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
            if (input.length() == 0) System.out.println("ERROR! Please provide a valid doctor ID: ");
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

    public static void createPatient(){

        String patientID = "";
        int userID = 0;
        String address = "";
        System.out.println("Please enter a patient ID (8-digit): ");
        Scanner scanner = new Scanner(System.in);
        patientID = scanner.nextLine();

        System.out.println("Please enter a user ID for this patient");
        Scanner scanner1 = new Scanner(System.in);
        userID = scanner1.nextInt();

        System.out.println("Please enter an address for this patient: ");
        Scanner scanner2 = new Scanner(System.in);
        address = scanner2.nextLine();

        Connection connection = DBConnector.connectToDB();
        try {
        PreparedStatement statement = connection.prepareStatement("insert into patient values (?,?,?)");
        statement.setString(1, patientID);
        statement.setInt(2, userID);
        statement.setString(3, address);
        statement.executeUpdate();
        connection.commit();
        connection.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        System.out.println("The new patient has been created successfully.");
    }

    public static void updatePatient(){

        System.out.println("Please enter the patient ID for the patient you want to update: ");
        Scanner scanner = new Scanner(System.in);
        String patientToUpdate = scanner.nextLine();

        System.out.println("Please enter the new address for this patient: ");
        Scanner scanner1 = new Scanner(System.in);
        String updatedAddr = scanner1.nextLine();

        try{
        Connection connection = DBConnector.connectToDB();
        Statement statement = connection.createStatement();
        String sql = "UPDATE patient SET address = " + "\'" + updatedAddr + "\'" +  " WHERE patient_id = " + "\'" + patientToUpdate + "\'" + ";";
        statement.executeUpdate(sql);
        connection.commit();
        connection.close();
        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }

    public static void createStaff(int userID, String privilegeType){

        Connection connection = DBConnector.connectToDB();
        try {

            Statement statement = connection.createStatement();
            String sql = "select user_id from staff";
            ResultSet resultSet = statement.executeQuery(sql);
            HashSet<Integer> entries = new HashSet<>();
            while (resultSet.next()) {
                entries.add(resultSet.getInt("user_id"));
            }
            if (!entries.contains(userID)) {

                PreparedStatement preparedStatement = connection.prepareStatement("insert into staff values(?,?,?::privilege_types)");
                preparedStatement.setString(1, "staff_" + Integer.toString(userID));
                preparedStatement.setInt(2, userID);
                if (privilegeType.equals("docstaffcs425")) {
                    preparedStatement.setString(3, "doctor");
                } else if (privilegeType.equals("admincs425")) {
                    preparedStatement.setString(3, "admin");
                } else if (privilegeType.equals("schedulercs425")) {
                    preparedStatement.setString(3, "scheduler");
                }

                preparedStatement.executeUpdate();
                connection.commit();
                connection.close();

            }
        } catch (Exception e){

            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);

        }


    }




}
