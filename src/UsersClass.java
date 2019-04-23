import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UsersClass {


    public static void register(){

        int userID = 0;
        String userName = null;
        String password = null;
        String email = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String birthday = null;
        int userAge = 0;

        while(true) {

            System.out.println("Please enter your user ID(eight-digit integer): ");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input < 10000000 || input > 99999999) System.out.println("ERROR! Please provide a valid input: ");
            else {
                userID = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your user name: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null) System.out.println("ERROR! Please provide a valid input: ");
            else {
                userName = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your password: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null) System.out.println("ERROR! Please provide a valid input: ");
            else {
                password = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your email: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null) System.out.println("ERROR! Please provide a valid input: ");
            else {
                email = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your first name: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null) System.out.println("ERROR! Please provide a valid input: ");
            else {
                firstName = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your middle name(Please enter NONE if there is none): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null) System.out.println("ERROR! Please provide a valid input: ");
            else {
                middleName = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your last name: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null) System.out.println("ERROR! Please provide a valid input: ");
            else {
                lastName = input;
                break;
            }

        }

        while(true) {
            System.out.println("Please enter your birthday(MM/DD/YYYY): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input == null || input.length() > 10 ||
                    input.charAt(2) != '/' || input.charAt(5) != '/') System.out.println("ERROR! Please provide a valid input: ");
            else {
                birthday = input;
                int year = Calendar.getInstance().get(Calendar.YEAR);
                String inputYear = input.substring(6);
                int user_year = Integer.parseInt(inputYear);
                userAge = year - user_year;
                break;
            }

        }

        Connection connection = null;


        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/MedicalClinic",
                            "postgres", "123");
            connection.setAutoCommit(false);
            String sql = "insert into users values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setString(2, userName);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, firstName);
            statement.setString(6, middleName);
            statement.setString(7, lastName);
            statement.setString(8, birthday);
            statement.setInt(9, userAge);
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

}
