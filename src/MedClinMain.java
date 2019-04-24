import java.util.Scanner;

public class MedClinMain {

    static void mainRunner(){

        while(true) {
            System.out.println("**********************************************");
            System.out.println("***                                        ***");
            System.out.println("    WElCOME TO THE MEDICAL CLINIC SYSTEM");
            System.out.println("***                                        ***");
            System.out.println("**********************************************");
            DBConnector dbConnector = new DBConnector();
            dbConnector.connectToDB();
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
                if (userPin.equals(loginClass.pinAdmin)){

                    //admin menu
                    System.out.println("Welcome. Please select your next move: ");
                    System.out.println("1. Schedule an appointment");
                    System.out.println("2. Create a new patient");
                    System.out.println("3. Check the business report");


                }else if(userPin.equals(loginClass.pinDocStaff)){

                    //doc/staff menu
                    System.out.println("Welcome doctor. Please select from the menu: ");
                    System.out.println("1. Check patient records");
                    System.out.println("2. Update patient records");
                    System.out.println("3. Create an order");
                    System.out.println("4. Check existing appointments");

                } else if(userPin.equals(loginClass.pinPatient)){

                    //patient menu
                    System.out.println("Welcome. Please select from the menu: ");
                    System.out.println("1. Check an order");
                    System.out.println("2. View your bills");

                } else if(userPin.equals(loginClass.pinScheduler)){

                    //scheduler menu
                    System.out.println("Welcome. Please choose from the menu: ");
                    System.out.println("1. View an order");
                    System.out.println("2. View bill");
                    System.out.println("3. Check existing appointments");

                }

            }

        }



    }

    public static void main(String[] args) {

        mainRunner();




    }

}
