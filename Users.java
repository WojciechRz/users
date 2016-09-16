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
	
	public void setId(Object value)
	{
		id = (int) value;
	}
	
	public void setName(Object value)
	{
		name = (String) value;
	}
	
	public void setSurname(Object value)
	{
		surname = (String) value;
	}
}
