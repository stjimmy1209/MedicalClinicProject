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


                            }else if(pinCode.equals(pinDocStaff)){
                                System.out.println("You are doc");


                            }else if(pinCode.equals(pinPatient)){
                                System.out.println("you are a patient");


                            }else if(pinCode.equals(pinScheduler)){
                                System.out.println("you are a scheduler");


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
