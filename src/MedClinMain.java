import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class MedClinMain {

    static void mainRunner(){

        while(true) {
            System.out.println("**********************************************");
            System.out.println("***                                        ***");
            System.out.println("    WElCOME TO THE MEDICAL CLINIC SYSTEM");
            System.out.println("***                                        ***");
            System.out.println("**********************************************");
            DBConnector.connectToDB();
            System.out.println("You have successfully connected to the database.");
            System.out.println("\n");
            System.out.println("Please select:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input == 3) break;
            else if(input == 1){

                System.out.println("Please enter your pin for administrator: ");
                Scanner scanner1 = new Scanner(System.in);
                String pin = scanner1.nextLine();
                LoginClass loginClass = new LoginClass();
                if (pin.equals(loginClass.pinAdmin)) UsersClass.register();
                else System.out.println("Sorry, only an admin can register new account.");


            }
            else if(input == 2){

                LoginClass loginClass = new LoginClass();
                String userPin = loginClass.login();

            }

        }



    }

    static void createTable(){

        System.out.println("Creating tables...");
        Connection connection = DBConnector.connectToDB();

        String[]buildTables = new String[8];

        buildTables[0] = "create type privilege_types as enum( 'admin', 'doctor', 'scheduler');";
        buildTables[1] = "create type category_types as enum('lab', 'MRI', 'Xray', 'Office_Visit');";

        buildTables[2] = "create table if not exists users (\n" +
                "\tuser_id\t\tinteger    \tprimary key,\n" +
                "\tuser_name\tvarchar(50)\t\tnot null,\n" +
                "\tpassword\tvarchar(50)\t\tnot null,\n" +
                "\tuser_email\tvarchar(50)\t\tnot null,\n" +
                "\tfirst_name\tvarchar(20)\t\tnot null,\n" +
                "\tmiddle_name\tvarchar(20)\t\tnot null,\n" +
                "\tlast_name\tvarchar(20)\t\tnot null,\n" +
                "\tbirthday\tvarchar(15)\t\tnot null,\n" +
                "\tuser_age\tinteger\t\tnot null\n" +
                ");";

        buildTables[3] = "create table if not exists logins (\n" +
                "\tsession_id\tVARCHAR(50)\tNOT NULL PRIMARY KEY,\n" +
                "\tuser_id\t integer\tNOT NULL REFERENCES users(user_id),\n" +
                "\tusername\tVARCHAR(20) NOT NULL ,\n" +
                "\tlogin_time\tVARCHAR(50)\tNOT NULL ,\n" +
                "\tlogout_time\tVARCHAR(50)\tnot null\n" +
                ");";

        buildTables[4] = "create table if not exists staff(\n" +
                "\tstaff_id \tVARCHAR(50) PRIMARY KEY,\n" +
                "\tuser_id \tinteger\tNOT NULL REFERENCES users(user_id),\n" +
                "\tprivilege_type\t privilege_types  NOT NULL\n" +
                ");";

        buildTables[5] = "create table if not exists patient(\n" +
                "\tpatient_id\tVARCHAR(8) PRIMARY KEY,\n" +
                "\tuser_id \tinteger\tNOT NULL REFERENCES users(user_id),\n" +
                "\taddress \tVARCHAR(50) NOT NULL\n" +
                ");";

        buildTables[6] = "create table if not exists orders(\n" +
                "\torder_id \tVARCHAR(8) NOT NULL PRIMARY KEY,\n" +
                "\tstaff_id \tVARCHAR(50) NOT NULL REFERENCES staff(staff_id),\n" +
                "\tpatient_id\tVARCHAR(8) NOT NULL REFERENCES patient(patient_id),\n" +
                "\tcategory_type\tcategory_types NOT NULL,\n" +
                "\tcost_amount\t\tinteger CHECK(cost_amount >= 0) NOT NULL,\n" +
                "\torder_date\tdate NOT NULL,\n" +
                "\tresults\t\tVARCHAR(255)\n" +
                ");";

        buildTables[7] = "create table if not exists appointments (\n" +
                "\tappointment_id\tvarchar(10)\tnot null primary key,\n" +
                "\tpatient_id\tvarchar(8)\tnot null references patient(patient_id),\n" +
                "\tstaff_id\tvarchar(50)\tnot null references\tstaff(staff_id),\n" +
                "\tappointment_date\tdate\tnot null\n" +
                ");";

        for (int i = 0; i < buildTables.length; i++) {
            try {

                Statement stmt = connection.createStatement();
                stmt.executeUpdate(buildTables[i]);
                connection.commit();
                stmt.close();


            } catch (Exception e){

                mainRunner();

            }
        }


        System.out.println("Done.");

    }

    public static void main(String[] args) {

        createTable();
        mainRunner();

    }

}
