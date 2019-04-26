import javax.swing.text.View;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoginClass {

    String pinAdmin = "admincs425";
    String pinDocStaff = "docstaffcs425";
    String pinPatient = "patientcs425";
    String pinScheduler = "schedulercs425";

    public String login(){

        while(true) {

            System.out.println("Please enter your user ID(eight-digit integer): ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            if (id < 10000000 || id > 99999999) System.out.println("ERROR! Please provide a valid input: ");
            else {

                System.out.println("Please enter your password: ");
                Scanner scannerPassword = new Scanner(System.in);
                String password = scannerPassword.nextLine();
                Connection c = null;
                Statement stmt = null;
                PreparedStatement stmt2 = null;
                String timeLogin = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//login time stored
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/MedicalClinic",
                                    "postgres", "123");
                    c.setAutoCommit(false);
                    stmt = c.createStatement();
                    ResultSet resultSet = stmt.executeQuery("select user_id, password from users");

                    while (resultSet.next()){

                        int userID = resultSet.getInt("user_id");
                        String userPassword = resultSet.getString("password");

                        if (userID == id && userPassword.equals(password)){

                            System.out.println("Please enter your pin code to proceed: ");
                            Scanner scanner1 = new Scanner(System.in);
                            String pinCode = scanner1.nextLine();

                            if (pinCode.equals(pinAdmin)){
                                System.out.println("You are an admin");

                                UpdateFunctions.createStaff(userID, pinAdmin);

                                System.out.println("Welcome. Please select your next move: ");
                                System.out.println("1. Schedule an appointment");
                                System.out.println("2. Create a new patient");
                                System.out.println("3. Check the business report");

                                //admin functions here
                                Scanner scannerAdmin = new Scanner(System.in);
                                int selection = scannerAdmin.nextInt();

                                if (selection == 1){

                                    UpdateFunctions.scheduleAppointment();

                                } else if(selection == 2){

                                    UpdateFunctions.createPatient();

                                } else if(selection == 3){
                                    //business report function here
                                    System.out.println("1. Total revenue: ");
                                    System.out.println("2. Total revenue of a doctor: ");

                                    Scanner scanner2 = new Scanner(System.in);
                                    int input = scanner2.nextInt();
                                    if (input == 1) BusinessReport.totalRevenue();
                                    else if(input == 2) BusinessReport.totalRvnDoc();

                                }

                            }else if(pinCode.equals(pinDocStaff)){
                                System.out.println("You are doc/staff");
                                UpdateFunctions.createStaff(userID, pinDocStaff);
                                //doc/staff menu
                                System.out.println("Welcome doctor. Please select from the menu: ");
                                System.out.println("1. Check patient records");
                                System.out.println("2. Update patient records");
                                System.out.println("3. Create an order");
                                System.out.println("4. Schedule appointments");

                                Scanner scannerDoc = new Scanner(System.in);
                                int input = scannerDoc.nextInt();
                                if (input == 1){
                                    ViewFunctions.viewPatient();
                                } else if(input == 2){
                                    UpdateFunctions.updatePatient();
                                }else if(input == 3){
                                    UpdateFunctions.createOrder();
                                }else if(input == 4){
                                    UpdateFunctions.scheduleAppointment();
                                }

                            }else if(pinCode.equals(pinPatient)){
                                System.out.println("you are a patient");
                                //patient menu
                                System.out.println("Welcome. Please select from the menu: ");
                                System.out.println("1. Check an order");
                                System.out.println("2. View your bills");
                                System.out.println("3. Check your appointment: ");

                                Scanner scannerDoc = new Scanner(System.in);
                                int input = scannerDoc.nextInt();
                                if (input == 1){

                                    ViewFunctions.checkOrder(userID, pinPatient);

                                } else if(input == 2){

                                    ViewFunctions.checkBill(userID, pinPatient);

                                } else if(input == 3){

                                    ViewFunctions.checkApmt(userID);

                                }

                            }else if(pinCode.equals(pinScheduler)){
                                System.out.println("you are a scheduler");

                                UpdateFunctions.createStaff(userID, pinScheduler);
                                //scheduler menu
                                System.out.println("Welcome. Please choose from the menu: ");
                                System.out.println("1. View an order");
                                System.out.println("2. View bill");
                                System.out.println("3. Schedule appointments");

                                //scheduler functions here
                                Scanner scannerDoc = new Scanner(System.in);
                                int input = scannerDoc.nextInt();
                                if (input == 1){

                                    ViewFunctions.checkOrder(userID, pinScheduler);

                                } else if(input == 2){

                                    ViewFunctions.checkBill(userID, pinScheduler);

                                } else if(input == 3){

                                    UpdateFunctions.scheduleAppointment();

                                }

                            } else {
                                System.out.println("Not a valid pin code.");
                            }

                            String timeLogout = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//logout time stored

                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/MedicalClinic",
                                            "postgres", "123");
                            c.setAutoCommit(false);
                            stmt = c.createStatement();
                            ResultSet resultSet2 = stmt.executeQuery("select user_name from users where user_id=" + userID +";");

                            while(resultSet2.next()) {

                            String nameUser = resultSet2.getString("user_name");
                            String sessionID = nameUser + timeLogin;

                            stmt2 = c.prepareStatement("insert into logins values (?,?,?,?,?)");
                            stmt2.setString(1, sessionID);
                            stmt2.setInt(2, userID);
                            stmt2.setString(3, nameUser);
                            stmt2.setString(4, timeLogin);
                            stmt2.setString(5, timeLogout);
                            stmt2.executeUpdate();
                            c.commit();
                            c.close();

                            }


                            return pinCode;
                        }

                    }

                    resultSet.close();
                    stmt.close();
                    c.close();

                }catch ( Exception e ) {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
                }


            }
                break;
        }
        return null;
    }


}
