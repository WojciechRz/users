package Users;

public class Users 
{
	private int id;
	private String name;
	private String surname;
	
	public Users(int aId, String aName, String aSurname)
	{
		id = aId;
		name = aName;
		surname = aSurname;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
}
