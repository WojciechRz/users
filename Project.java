package Users;

import java.sql.SQLException;
import java.util.ArrayList;

public class Project {
	public static void main(String[] args) throws SQLException {
		TableDemo demo = new TableDemo();
		demo.setVisible(true);
		demo.setDefaultCloseOperation(demo.EXIT_ON_CLOSE);
		demo.setSize(300, 300);
		demo.getTableDemo();

	}
}
