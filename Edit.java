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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Edit extends JFrame
{
		private Users obj;
	
		private JTextField textFieldId = null;
		private JTextField textFieldName = null;
		private JTextField textFieldSurname = null;
		private UsersTableModel editModel;
		
		public Edit(Users aobj, UsersTableModel aEditModel)
	 	{
			obj = aobj;
			editModel = aEditModel;
	
			setSize(280,250);
			
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
		    
		    JButton buttonCancel = new JButton("Cancel");
		    JButton buttonConfirm = new JButton("Confirm");
		    JButton buttonRemove = new JButton("Remove");
		    
		    buttonPanel.add(buttonCancel);
		    buttonPanel.add(buttonConfirm);
		    buttonPanel.add(buttonRemove);
		    
		    buttonConfirm.addActionListener(new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent event)
	        	{
	        		try {
						JDBC.update(textFieldName.getText(), textFieldSurname.getText(), getId());
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

		    buttonCancel.addActionListener(new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent event)
	        	{
	           		setVisible(false);
	        	}
	        	});

		  buttonRemove.addActionListener(new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent event)
	        	{
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
	
		public int getId()
		{
			return obj.getId();
		}
		
		public String getName()
		{
			return obj.getName();
		}
	 	
		public String getSurname()
		{
			return obj.getSurname();
		}
		
	 	private void addLabelAndTextField(String labelText, int yPos, Container addressPanel) 
	 	{
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
	 
	 	    if(yPos==0)
	 	    {
	 	    textFieldName = new JTextField(getName());
	 	    addressPanel.add(textFieldName, gridBagConstraintForTextField);
	 	    textFieldName.setColumns(10);
	 	    }
	 	    if(yPos==1)
	 	    {
	 	    textFieldSurname = new JTextField(getSurname());
	 	    addressPanel.add(textFieldSurname, gridBagConstraintForTextField);
	 	    textFieldSurname.setColumns(10);
	  	    }
	 	}
}