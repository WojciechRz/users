package Users;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class TableDemo extends JFrame {
	private UsersTableModel model;
	private JTable table;
	private JButton newRecord;
	private int r; // mouse Click row

	public TableDemo() throws SQLException {
		model = new UsersTableModel();
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		newRecord = new JButton("New record");
		add(newRecord, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.SOUTH);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					r = table.rowAtPoint(e.getPoint());
					Edit ed;
					try {
						ed = new Edit(model.getPerson(r), model, JDBC.listCities());
						ed.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}

				}
			}
		});

		newRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				NewRecord obj;
				try {
					obj = new NewRecord(model, JDBC.listCities());
					obj.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public JTable getTableDemo() {
		return table;
	}

	public void setTableRow(int id, String name, String surname) {
		table.setValueAt(name, 0, r); // name
		table.setValueAt(surname, 1, r); // surname
	}
}

class UsersTableModel extends AbstractTableModel {
	private ArrayList<Users> persons = null;
	private final static Object[] columnNames = { "name", "surname", "city" };

	private final static int NAME_IDX = 0;
	private final static int SURNAME_IDX = 1;
	private final static int CITY_IDX = 2;

	public UsersTableModel() throws SQLException {
		setList(JDBC.list());
	}

	public void setList(ArrayList<Users> newList) {
		persons = newList;
	}

	public int getRowCount() {
		if (persons == null)
			return 0;
		return persons.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (persons == null)
			return null;
		Users p = (Users) persons.get(rowIndex);
		switch (columnIndex) {
		case NAME_IDX:
			return p.getName();
		case SURNAME_IDX:
			return p.getSurname();
		case CITY_IDX:
			return p.getCity();
		default:
			return p;
		}
	}

	public String getColumnName(int column) {
		return columnNames[column].toString();
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void setModelData(ArrayList<Users> persons) {
		this.persons = persons;
	}

	public Users getPerson(int position) {
		return persons.get(position);
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Users p = (Users) persons.get(rowIndex);
		switch (columnIndex) {
		case NAME_IDX:
			p.setName(value);
			break;
		case SURNAME_IDX:
			p.setSurname(value);
			break;
		case CITY_IDX:
			p.setCity(value);
			break;
		default:
			break;
		}
		fireTableDataChanged();
	}

	public void dataRefresh() {
		fireTableDataChanged();
	}
}