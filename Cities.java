package Users;

public class Cities {
	private int cityId;
	private String city;

	public Cities(int aCityId, String aCity) {
		cityId = aCityId;
		city = aCity;
	}

	public int getCityId() {
		return cityId;
	}

	public String getCity() {
		return city;
	}

	public String toString() {
		return city;
	}
}
