package soumya;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class salaraymatch {private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine";
private static String tableName = "agent";
//jdbc Connection
private static Connection conn = null;
private static Statement stmt = null;

public static void main(String[] args)
{
	String s= args[0];
	//int o=  Integer.parseInt(s);
	System.out.println(s);
 createConnection();
 selectagent(s);
 shutdown();
 }

private static void createConnection()
{
 try
 {
     Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
     //Get a connection
     conn = DriverManager.getConnection(dbURL); 
 }
 catch (Exception except)
 {
     except.printStackTrace();
 }
}


public static void selectagent(String salar)
{
 try
 {
 	createConnection();
     stmt = conn.createStatement();
     ResultSet results = stmt.executeQuery("select * from " + tableName+" where salary >?");
     String query = null;
	PreparedStatement m = conn.prepareStatement(query);
     m.setInt(1,Integer.parseInt(salar));
     ResultSetMetaData rsmd = results.getMetaData();
     int numberCols = rsmd.getColumnCount();
     for (int i=1; i<=numberCols; i++)
     {
         //print Column Names
         System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
     }

     System.out.println("\n-------------------------------------------------");

     while(results.next())
     {
         int agent_id = results.getInt(1);
         String firstn = results.getString(2);
         String middle = results.getString(3);
         String lastn = results.getString(4);
         String address = results.getString(5);
         String city = results.getString(6);
         String country = results.getString(7);
         int salary = results.getInt(8);
         int clearance_id = results.getInt(9);
         System.out.println(agent_id + "\t\t" + firstn + "\t\t" + middle+ "\t\t" + lastn + "\t\t" + address + "\t\t" + city + "\t\t" + country + "\t\t" + salary + "\t\t" + clearance_id);
     }
     results.close();
     stmt.close();
     shutdown();
 }
 catch (SQLException sqlExcept)
 {
     sqlExcept.printStackTrace();
 }
}

private static void shutdown()
{
 try
 {
     if (stmt != null)
     {
         stmt.close();
     }
     if (conn != null)
     {
         DriverManager.getConnection(dbURL + ";shutdown=true");
         conn.close();
     }           
 }
 catch (SQLException sqlExcept)
 {
     
 }

}
}