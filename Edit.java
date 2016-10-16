package Users;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Edit extends JFrame {
	private Users obj;

	private JTextField textFieldName = null;
	private JTextField textFieldSurname = null;
	private static JComboBox comboBoxCity = null;
	private UsersTableModel editModel;
	private ArrayList citiesList;

	public Edit(Users aobj, UsersTableModel aEditModel, ArrayList aCitiesList) {
		obj = aobj;
		editModel = aEditModel;
		citiesList = aCitiesList;

		setSize(280, 250);

		JPanel addressPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		add(addressPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		Border border = addressPanel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		addressPanel.setBorder(new CompoundBorder(border, margin));

		GridBagLayout panelGridBagLayout = new GridBagLayout();
		panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
		panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
		panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		addressPanel.setLayout(panelGridBagLayout);

		addLabelAndTextField("Name:", 0, addressPanel);
		addLabelAndTextField("Surname:", 1, addressPanel);
		addLabelAndTextField("City:", 2, addressPanel);

		JButton buttonCancel = new JButton("Cancel");
		JButton buttonConfirm = new JButton("Confirm");
		JButton buttonRemove = new JButton("Remove");

		buttonPanel.add(buttonCancel);
		buttonPanel.add(buttonConfirm);
		buttonPanel.add(buttonRemove);

		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JDBC.update(textFieldName.getText(), textFieldSurname.getText(),
							((Cities) comboBoxCity.getSelectedItem()).getCityId(), getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					editModel.setList(JDBC.list());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editModel.fireTableDataChanged();
				setVisible(false);
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		});

		buttonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JDBC.delete(getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					editModel.setList(JDBC.list());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editModel.fireTableDataChanged();
				setVisible(false);
			}
		});
	}

	public int getId() {
		return obj.getId();
	}

	public String getName() {
		return obj.getName();
	}

	public String getSurname() {
		return obj.getSurname();
	}

	public String getCity() {
		return obj.getCity();
	}

	public static void comboBoxCityFill(ArrayList newCitiesList) {
		for (int i = 0; i < newCitiesList.size(); i++) {
			comboBoxCity.addItem(newCitiesList.get(i));
		}
		comboBoxCity.addItem("inne");
	}

	public void comboBoxCitySelect() {
		int k = 0;

		for (k = 0; k < citiesList.size(); k++) {
			if (((((Cities) citiesList.get(k))).getCity()).equals(getCity())) {
				break;
			}
		}
		comboBoxCity.setSelectedItem(citiesList.get(k));
	}

	private void addLabelAndTextField(String labelText, int yPos, Container addressPanel) {
		JLabel faxLabel = new JLabel(labelText);
		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 0;
		gridBagConstraintForLabel.gridy = yPos;
		addressPanel.add(faxLabel, gridBagConstraintForLabel);
		GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
		gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
		gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
		gridBagConstraintForTextField.gridx = 1;
		gridBagConstraintForTextField.gridy = yPos;

		if (yPos == 0) {
			textFieldName = new JTextField(getName());
			addressPanel.add(textFieldName, gridBagConstraintForTextField);
			textFieldName.setColumns(10);
		}
		if (yPos == 1) {
			textFieldSurname = new JTextField(getSurname());
			addressPanel.add(textFieldSurname, gridBagConstraintForTextField);
			textFieldSurname.setColumns(10);
		}
		if (yPos == 2) {
			comboBoxCity = new JComboBox();
			comboBoxCityFill(citiesList);
			comboBoxCitySelect();
			addressPanel.add(comboBoxCity, gridBagConstraintForTextField);

			comboBoxCity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (comboBoxCity.getSelectedItem() == "inne") {
						NewCity obj = new NewCity();
						obj.setVisible(true);
					}
				}
			});
		}
	}
}