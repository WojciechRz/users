package Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.sql.Statement;

public class JDBC {
	private static Connection createConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Wojtek_baza", "postgres",
					"wojtek");

			Statement stmt = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.append("Nie masz sterownika");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.append("Zle dane");
		}

		return connection;
	}

	public static ArrayList list() throws SQLException {
		Connection connection = createConnection();
		PreparedStatement insertStmt = connection.prepareStatement("SELECT id, imie, nazwisko, miasto FROM users, "
				+ "CITIES WHERE users.miastoId = CITIES.miastoId ORDER BY nazwisko");

		ResultSet rs = insertStmt.executeQuery();

		ArrayList<Users> listUsers = new ArrayList<>();

		while (rs.next()) {
			listUsers.add(
					new Users(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("miasto")));
		}

		insertStmt.close();
		connection.close();

		return listUsers;
	}

	public static ArrayList listCities() throws SQLException {
		Connection connection = createConnection();
		PreparedStatement insertStmt2 = connection.prepareStatement("SELECT * FROM cities ORDER BY miastoId");
		ResultSet rs2 = insertStmt2.executeQuery();

		ArrayList<Cities> listCities = new ArrayList<Cities>();

		while (rs2.next()) {
			listCities.add(new Cities(rs2.getInt("miastoId"), rs2.getString("miasto")));
		}

		insertStmt2.close();
		connection.close();

		return listCities;
	}

	public static void update(String newName, String newSurname, int newCityId, int id) throws Exception {
		Connection connection = createConnection();
		PreparedStatement insertStmt = connection
				.prepareStatement("UPDATE USERS SET imie= ?, nazwisko= ?, miastoId= ? WHERE id= ?");

		insertStmt.setString(1, newName);
		insertStmt.setString(2, newSurname);
		insertStmt.setInt(3, newCityId);
		insertStmt.setInt(4, id);
		insertStmt.executeUpdate();

		insertStmt.close();
		connection.close();
	}

	public static void delete(int id) throws SQLException, Exception {
		Connection connection = createConnection();
		PreparedStatement insertStmt = connection.prepareStatement("DELETE FROM USERS WHERE id= ?");

		insertStmt.setInt(1, id);
		insertStmt.executeUpdate();

		insertStmt.close();
		connection.close();
	}

	public static void insert(String newName, String newSurname, int newCityId) throws SQLException, Exception {
		Connection connection = createConnection();
		PreparedStatement insertStmt = connection
				.prepareStatement("INSERT INTO USERS (imie, nazwisko, miastoId) VALUES (?, ?, ?)");

		insertStmt.setString(1, newName);
		insertStmt.setString(2, newSurname);
		insertStmt.setInt(3, newCityId);
		insertStmt.executeUpdate();

		insertStmt.close();
		connection.close();
	}

	public static void insertNewCity(String newCity) throws SQLException, Exception {
		Connection connection = createConnection();
		PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO CITIES (miasto) VALUES (?)");

		insertStmt.setString(1, newCity);
		insertStmt.executeUpdate();

		insertStmt.close();
		connection.close();
	}
}