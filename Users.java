package Users;

public class Users {
	private int id;
	private String name;
	private String surname;
	private String city;

	public Users(int aId, String aName, String aSurname, String aCity) {
		id = aId;
		name = aName;
		surname = aSurname;
		city = aCity;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getCity() {
		return city;
	}

	public void setId(Object value) {
		id = (int) value;
	}

	public void setName(Object value) {
		name = (String) value;
	}

	public void setSurname(Object value) {
		surname = (String) value;
	}

	public void setCity(Object value) {
		city = (String) value;
	}
}
