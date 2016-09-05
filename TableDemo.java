package Users;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableDemo extends JFrame
{
    private JTable table;

    public TableDemo() 
    {
        table = new JTable(new UsersTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
    public JTable getTableDemo()
    {
    	return table;
    }
   
}

class UsersTableModel extends AbstractTableModel
{
	private ArrayList<Users> persons = null;
    private final static Object[] columnNames = {"id", "name", "surname"};
     
    private final static int ID_IDX = 0;
    private final static int NAME_IDX = 1;
    private final static int SURNAME_IDX = 2;
     
    public UsersTableModel() 
    {
    	JDBC objectJDBC = new JDBC();
    	try 
    	{
    		objectJDBC.setConnection();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	persons = objectJDBC.getListUsers();	
    }
     
    public int getRowCount() 
    {
        if(persons==null) return 0;
        return persons.size();
    }
 
    public int getColumnCount() 
    {
        return columnNames.length;
    }
     
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        if(persons == null) return null;
        Users p = (Users) persons.get(rowIndex);
        switch (columnIndex) 
        {
            case ID_IDX:
                return p.getId();
            case NAME_IDX:
                return p.getName();
            case SURNAME_IDX:
                return p.getSurname();
            default:
                return p;
        }
    }
 
    public String getColumnName(int column) 
    {
        return columnNames[column].toString();
    }
 
    public boolean isCellEditable(int row, int column) 
    {
        return false;
    }
     
    public void setModelData(ArrayList<Users> persons) 
    {
       this.persons =  persons;
    }
    
    public Users getPerson(int position) 
    {
        return persons.get(position);
    }
  
}

