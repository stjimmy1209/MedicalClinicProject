import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LoginClass {

    String pinAdmin = "admincs425";
    String pinDocStaff = "docstaffcs425";
    String pinPatient = "patientcs425";
    String pinScheduler = "schedulercs425";

    public void login(){

        while(true) {

            System.out.println("Please enter your user ID(eight-digit integer): ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            if (id < 10000000 || id > 99999999) System.out.println("ERROR! Please provide a valid input: ");
            else {

                System.out.println("Please enter your password: ");
                Scanner scannerpassword = new Scanner(System.in);
                String password = scannerpassword.nextLine();
                Connection c = null;
                Statement stmt = null;
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

                                // admin function here
                            }else if(pinCode.equals(pinDocStaff)){
                                System.out.println("You are doc");

                                //docstaff function here
                            }else if(pinCode.equals(pinPatient)){
                                System.out.println("you are a patient");

                                //patient function here
                            }else if(pinCode.equals(pinScheduler)){
                                System.out.println("you are a scheduler");

                                //scheduler function here
                            } else {
                                System.out.println("Not a valid pincode.");
                            }

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

    }

}
