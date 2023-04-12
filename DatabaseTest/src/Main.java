import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testcon";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();

            System.out.println("Connected to SQL Server");

            //query for Hour of the day when the call volume is highest.
            String query = "select hour(start_time) AS HourOfDay,count(duration) AS TotalDuration from testData group By HourOfDay order By TotalDuration desc limit 1";
            ResultSet rs = st.executeQuery(query);
            // getting the values in rs
            while (rs.next())
            {
                String  s=rs.getString("HourOfDay");
                int a= Integer.parseInt(s);
                //System.out.println(a);
                System.out.println("Hour of the day when the call volume  highest is "+a+"-"+(a+1)+".");
            }
            //query for Hour of the day when the calls are longest.
            String query2 = "select hour(start_time) AS HourOfDay from testData where duration=(select max(duration) from testData) ";
            ResultSet rs2 = st.executeQuery(query2);

            while (rs2.next())
            {
                String  s=rs2.getString("HourOfDay");
                int a= Integer.parseInt(s);
                //System.out.println(a);
                System.out.println("Hour of the day when the call length  highest is "+a+"-"+(a+1)+".");
            }
            //query for Day of the week when the call volume is highest.
            String query3 = "select dayname(start_time) AS dayOfWeek,count(duration) AS TotalDuration from testData group By dayOfWeek order By TotalDuration desc limit 1";
            ResultSet rs3 = st.executeQuery(query3);
            while (rs3.next())
            {
                String  s=rs3.getString("dayOfWeek");
                System.out.println("Day of the week when the call volume  highest is " +s);
            }
            //query for Day of the week when the calls are longest.
            String query4 = "select dayname(start_time) AS dayOfweek from testData where duration=(select max(duration) from testData) ";
            ResultSet rs4 = st.executeQuery(query4);
            while (rs4.next())
            {
                String  s=rs4.getString("dayOfWeek");
                System.out.println("Day of the week when the call length  highest is "+s);
            }
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Not able to connect");
            e.printStackTrace();

        }
    }
}
