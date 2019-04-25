import javax.swing.plaf.nimbus.State;
import java.sql.*;
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

    public static void checkOrder(int userID, String pinCode){

        if (pinCode.equals("patientcs425")){

            Connection connection = DBConnector.connectToDB();
            try {
                Statement statement = connection.createStatement();
                String sql = "select * from users natural join orders where user_id=" + "\'" + userID + "\';";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {

                    String orderID = resultSet.getString("order_id");
                    String staffID = resultSet.getString("staff_id");
                    String patientID = resultSet.getString("patient_id");
                    String CateType = resultSet.getString("category_type");
                    int cost = resultSet.getInt("cost_amount");
                    Date orderDate = resultSet.getDate("order_date");
                    String results = resultSet.getString("results");

                    System.out.println("Data retrieved: ");
                    System.out.println("Order ID: " + orderID);
                    System.out.println("Staff ID: " + staffID);
                    System.out.println("Your Patient ID: " + patientID);
                    System.out.println("Category Type: " + CateType);
                    System.out.println("Order Cost: " + cost);
                    System.out.println("Order Date: " + orderDate);
                    System.out.println("Diagnostic Result: " + results);



                }

                resultSet.close();
                statement.close();
                connection.close();


            } catch (Exception e){

                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);

            }



        }else if(pinCode.equals("schedulercs425")){

            System.out.println("Please enter the order number you want to check: ");
            Scanner scanner = new Scanner(System.in);
            String orderNo = scanner.nextLine();

            Connection connection = DBConnector.connectToDB();
            try {
                Statement statement = connection.createStatement();
                String sql = "select * from orders where order_id=" + "\'" + orderNo + "\';";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {

                    String orderID = resultSet.getString("order_id");
                    String staffID = resultSet.getString("staff_id");
                    String patientID = resultSet.getString("patient_id");
                    String CateType = resultSet.getString("category_type");
                    int cost = resultSet.getInt("cost_amount");
                    Date orderDate = resultSet.getDate("order_date");
                    String results = resultSet.getString("results");

                    System.out.println("Data retrieved: ");
                    System.out.println("Order ID: " + orderID);
                    System.out.println("Staff ID: " + staffID);
                    System.out.println("Your Patient ID: " + patientID);
                    System.out.println("Category Type: " + CateType);
                    System.out.println("Order Cost: " + cost);
                    System.out.println("Order Date: " + orderDate);
                    System.out.println("Diagnostic Result: " + results);

                }

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

    public static void checkBill(int userID, String pinCode){

        Connection connection = DBConnector.connectToDB();

        try {
            if (pinCode.equals("patientcs425")) {

                System.out.println("Your current bills are: ");
                Statement statement = connection.createStatement();
                String sql = "select order_id, cost_amount from users natural join orders where user_id=" + "\'" + userID + "\';";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {

                    String orderID = resultSet.getString("order_id");
                    int cost = resultSet.getInt("cost_amount");

                    System.out.println("OrderID: " + orderID);
                    System.out.println("Bill amount for this order: $" + cost);

                }

                String sqlTotal = "select SUM(cost_amount) as total from users natural join orders where user_id=" + "\'" + userID + "\';";
                ResultSet resultSet1 = statement.executeQuery(sqlTotal);
                while (resultSet1.next()) {

                    int billTotal = resultSet1.getInt("total");
                    System.out.println("Your total bill amount is: $" + billTotal);

                }

                statement.close();
                resultSet1.close();

            } else if (pinCode.equals("schedulercs425")) {

                System.out.println("Please enter the patient_id that you want to check: ");
                Scanner scanner = new Scanner(System.in);
                String patientID = scanner.nextLine();

                Statement statement = connection.createStatement();

                String sqlTotal = "select SUM(cost_amount) as total from orders where patient_id=" + "\'" + patientID + "\';";
                ResultSet resultSet1 = statement.executeQuery(sqlTotal);
                while (resultSet1.next()) {

                    int billTotal = resultSet1.getInt("total");
                    System.out.println("Total bill amount for this patient is: $" + billTotal);

                }

                statement.close();
                resultSet1.close();


            }
        } catch (Exception e){

            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);

        }

    }




}
