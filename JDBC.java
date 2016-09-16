package Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.sql.Statement;

public class JDBC 
{
    private static Connection createConnection() throws SQLException 
    {
    	Connection connection = null;
    	
        try 
        {
        	Class.forName("org.postgresql.Driver");

        	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Wojtek_baza","postgres", "wojtek");

            Statement stmt = connection.createStatement();
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
            System.out.append("Nie masz sterownika");
        }
        catch (SQLException e )
        {
            e.printStackTrace();
            System.out.append("Zle dane");
        }
        
        return connection;
    }
    
    public static ArrayList list() throws SQLException
    {
    	Connection connection = createConnection();
    	PreparedStatement insertStmt = connection.prepareStatement("SELECT * FROM users ORDER BY nazwisko");
    	    			
        ResultSet rs = insertStmt.executeQuery();
       
        ArrayList<Users> listUsers = new ArrayList<>();
        
        while(rs.next())
        {
        listUsers.add(new Users(rs.getInt("id"),rs.getString("imie"),rs.getString("nazwisko")));         
        }
        
        insertStmt.close();
        connection.close();
        
        return listUsers;
    }
    
    public static void update(String newName, String newSurname, int id) throws Exception 
    {
    	Connection connection = createConnection();
    	PreparedStatement insertStmt = connection.prepareStatement("UPDATE USERS SET imie= ?, nazwisko= ? WHERE id= ?");
    	
    	insertStmt.setString(1, newName);
    	insertStmt.setString(2, newSurname);
    	insertStmt.setInt(3, id);
    	insertStmt.executeUpdate();
    	
        insertStmt.close();
        connection.close();
    }
    
    public static void delete(int id) throws SQLException, Exception
    {
    	Connection connection = createConnection();
    	PreparedStatement insertStmt = connection.prepareStatement("DELETE FROM USERS WHERE id= ?");

    	insertStmt.setInt(1, id);
    	insertStmt.executeUpdate();
    	
        insertStmt.close();
        connection.close();
     }
    
    public static void insert(String newName, String newSurname) throws SQLException, Exception
    {
    	Connection connection = createConnection();
    	PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO USERS (imie, nazwisko) VALUES (?, ?)");

    	insertStmt.setString(1, newName);
    	insertStmt.setString(2, newSurname);
    	insertStmt.executeUpdate();
    	
        insertStmt.close();
        connection.close();
     }
 }