import java.util.ArrayList;
import java.util.List;
import java.awt.*;
 
public class JDBC 
{
	private ArrayList<Users> listUsers;

    public void setConnection() throws SQLException 
    {
        try 
        {
            Class.forName("org.postgresql.Driver");

            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Wojtek_baza","postgres", "wojtek");

            Statement stmt = connection.createStatement();
            String command = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(command);
            
            listUsers = new ArrayList<>();
            
            while(rs.next())
            {
            	listUsers.add(new Users(rs.getInt("id"),rs.getString("imie"),rs.getString("nazwisko")));         
            }
            
            connection.close();    
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
    }
    
    public ArrayList<Users> getListUsers()
    {
    	return listUsers;
    }
}