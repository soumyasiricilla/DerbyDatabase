package soumya;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
public class string {
	  private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine";
	    private static String tableName = "language";
	    private static String tableName2 = "Skill";
	    // jdbc Connection
	    private static Connection conn = null;
	    private static Statement stmt = null;

	    public static void main(String[] args)
	    {
	    	String s= args[0];
	    	System.out.println(s);
	        createConnection();
	        selectLanguage(s);
	        selectskill(s);
	        shutdown();
	    }
	    
	    private static void createConnection()
	    {
	        try
	        {
	            Class.forName("org.apache.derby.jdbc.ClientDriver");
	            //Get a connection
	            conn = DriverManager.getConnection(dbURL); 
	        }
	        catch (Exception except)
	        {
	            except.printStackTrace();
	        }
	    }
	    
	    // select language method if string matches with language
	    public static void selectLanguage(String langskill)
	    {
	        try
	        { 
	        	createConnection();
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery("select * from " + tableName+" where language = '"+langskill+"'");
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
	            	int lang_id = results.getInt(1);
	                String language = results.getString(2);
	                System.out.println(lang_id + "\t\t"+ language);
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
	    //select skill method if skill matches with the skill
	    public static void selectskill(String langskill)
	    {
	        try
	        { 
	        	createConnection();
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery("select * from " + tableName2+" where skill = '"+langskill+"'");
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
	                int skill_id = results.getInt(1);
	                String skill = results.getString(2);
	                System.out.println(skill_id + "\t\t"+ skill);
	            }
	            results.close();
	            stmt.close();
	            shutdown();
	        }
	        // return sql error messages
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	    //shutdown method
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

