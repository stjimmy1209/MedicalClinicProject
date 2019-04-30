import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BusinessReport {


    public static void totalRevenue(){

        Connection connection = DBConnector.connectToDB();

        try {
            Statement statement = connection.createStatement();
            String sql = "select sum(cost_amount) as totalRvn from orders";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int total = resultSet.getInt("totalRvn");
                System.out.println("Current total revenue is : $" + total);
            }

        } catch (Exception e){

            System.out.println("ERROR in input! Please try again.");
            totalRvnDoc();

        }

    }

    public static void totalRvnDoc(){

        System.out.println("Please enter the staff_id to check the revenue: ");

        Scanner scanner = new Scanner(System.in);
        String stfID = scanner.nextLine();

        Connection connection = DBConnector.connectToDB();

        try {
            Statement statement = connection.createStatement();
            String sql = "select sum(cost_amount) as totalRvn from orders where staff_id=" + "\'" + stfID + "\'" + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int total = resultSet.getInt("totalRvn");
                System.out.println("Current total revenue is : $" + total);
            }

        } catch (Exception e){

            System.out.println("ERROR in input! Please try again.");
            totalRvnDoc();

        }

    }

}
