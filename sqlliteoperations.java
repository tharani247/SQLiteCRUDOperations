package jdbc;

import java.sql.*;

public class mulesoft {
	
	
	public static void createDatabase(String dbName) {
		String url = "jdbc:sqlite:" + dbName + ".db";
		try {
			Connection conn = DriverManager.getConnection(url);
            if (conn != null) {  
                System.out.println("Database Creation");
            }
            conn.close();
        }
        catch (SQLException e) {  
        	System.out.println(e.getMessage());
        }
	}
	
	public static void createTable(String dbName, String tableName) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        
	        String query = "CREATE TABLE IF NOT EXISTS movies(id integer PRIMARY KEY AUTOINCREMENT,aname text NOT NULL, amovie text NOT NULL, UNIQUE(aname, amovie));";
	        statement.execute(query);
	        System.out.println("Table Creation.");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void insertData(String aname, String amovie) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        String query = "INSERT INTO movies(aname, amovie) VALUES(?,?)";
	        
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setString(1, aname);
	        pstmt.setString(2, amovie);
	        pstmt.executeUpdate();        
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void retrieveData(String aname) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        String query = "SELECT aname, amovie FROM movies";
	        if(aname != null) {
	        	query = query + " where aname='" + aname + "'";
	        }
	        ResultSet rs = statement.executeQuery(query);
	        String format = "%-20s-\t%s\n";
	        System.out.printf(format, "Artist Name", "Acted Movie");
	        while (rs.next()) {
	        	System.out.printf(format,rs.getString(1),rs.getString(2));
            }			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			// Database Connection Section
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
            System.out.println("SQLite Database Connected successfully");

            
            // Database Creation Section
            createDatabase("Tharani");
            
            
            // Table Creation Section
            createTable("Tharani", "movies");
       
            
            
            // Data Insertion Section
            insertData("mahesh babu", "dookudu");
            insertData("prabhas", "bahubali");
            insertData("pawan kalyan", "panjaa");
            insertData("ram charan", "rangasthalam");
            insertData("mahesh babu", "sarileru neekevvaru");
            insertData("junior ntr", "temper");
            insertData("prabhas", "saaho");
            insertData("nithin", "maestro");
            System.out.println("Data Inserted Into The Table.");

            
            
            // Data Querying Section
            System.out.println("Querying Details:");
            retrieveData(null);
            System.out.println("Querying Details With A Particular Actor Name:");
            retrieveData("mahesh babu");
 
            
            con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
