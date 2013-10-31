DerbyDatabase
=============


Development Environment
This program is developed in eclipse IDE.
For data base connection. I used  “Apache Derby database” (is an open source relational database). 
Derby is based on the Java, JDBC, and SQL standards. 
Derby provides an embedded JDBC driver that lets you embed Derby in any Java-based solution. 


Installed Apache Derby plug-ins for Eclipse,which provide a seamless integration between Eclipse and Apache Derby. 
It enables the use of the Derby database JAR files as an installable component to Eclipse. 
Additionally the ij SQL scripting tool and the Apache Derby Network Server can be run from the Eclipse console.


In program we have to write the below code and a method to create connection

private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine";
T
he code explains connecting to the derby server in the localhost 1527


private static void createConnection()

{

    try
    
    {
    
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        
        //to Get a connection
        conn = DriverManager.getConnection(dbURL); 
        
    }
