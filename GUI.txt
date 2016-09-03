package Investment;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class GUI extends JFrame
{
	public GUI()
	{
		setSize(350,200);
		setLayout(new BorderLayout());
		
		//utworzenie i dodanie paneli glownych
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel();

		JPanel panelSouth = new JPanel();

		add(panelNorth, BorderLayout.NORTH);
		add(panelCenter, BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);
		
		//utworzenie i dodanie panelu panelNorthWest
		JPanel panelNorthWest = new JPanel();
		panelNorthWest.setLayout(new BorderLayout());
		panelNorth.add(panelNorthWest, BorderLayout.WEST);
		
		//utworzenie i dodanie komponentow do panelu panelNorthWest
		JLabel labelInwestedAmonut = new JLabel("Wp³acona kwota");
		panelNorthWest.add(labelInwestedAmonut, BorderLayout.NORTH);
		
		JLabel labelPeriodOfSaving = new JLabel("Okres oszczêdzania");
		panelNorthWest.add(labelPeriodOfSaving, BorderLayout.CENTER);
		
		JLabel labelInterestRate = new JLabel("Oprocentowanie roczne");
		panelNorthWest.add(labelInterestRate, BorderLayout.SOUTH);
		
		//utworzenie i dodanie panelu panelNorthCenter
		JPanel panelNorthCenter = new JPanel();
		panelNorthCenter.setLayout(new BorderLayout());
		panelNorth.add(panelNorthCenter, BorderLayout.CENTER);
		
		//utworzenie i dodanie komponentow do panelu panelNorthCenter
		JTextField textFieldInwestedAmonut = new JTextField();
		panelNorthCenter.add(textFieldInwestedAmonut, BorderLayout.NORTH);
		
	    JTextField textFieldPeriodOfSaving = new JTextField();
		panelNorthCenter.add(textFieldPeriodOfSaving, BorderLayout.CENTER);
		
		JTextField textFieldInterestRate = new JTextField();
		panelNorthCenter.add(textFieldInterestRate, BorderLayout.SOUTH);
		

		
		
		//utworzenie i dodanie panelu panelNorthEast
		JPanel panelNorthEast = new JPanel();
		panelNorthEast.setLayout(new BorderLayout());
		panelNorth.add(panelNorthEast, BorderLayout.EAST);
		
		//utworzenie i dodanie komponentow do panelu panelNorthEast
		JLabel labelCurrency = new JLabel("z³");
		panelNorthEast.add(labelCurrency, BorderLayout.NORTH);
		
		JLabel labelYears = new JLabel("lata");
		panelNorthEast.add(labelYears, BorderLayout.CENTER);
		
		JLabel labelPercent = new JLabel("%");
		panelNorthEast.add(labelPercent, BorderLayout.SOUTH);
		
		//utworzenie i dodanie komponentow do panelu panelCenter		
		JButton buttonCalculate = new JButton("Oblicz");
		panelCenter.add(buttonCalculate);

		
		//utworzenie i dodanie komponentow do panelu panelSouth
		
		JLabel labelResult = new JLabel("",JLabel.CENTER);
		
		panelSouth.add(labelResult);
		
		
		//dodanie filtrow do komponentow JTextField
		PlainDocument doc1 = (PlainDocument) textFieldInwestedAmonut.getDocument();
	    doc1.setDocumentFilter(new FilterDouble());
	    
		PlainDocument doc2 = (PlainDocument) textFieldPeriodOfSaving.getDocument();
	    doc2.setDocumentFilter(new FilterInt());
	    
		PlainDocument doc3 = (PlainDocument) textFieldInterestRate.getDocument();
	    doc3.setDocumentFilter(new FilterDouble());
		
		//dodanie sluchacza do komponentu buttonCalculate
		
		buttonCalculate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				
				double Amount = Double.valueOf(textFieldInwestedAmonut.getText());
				int Years = Integer.valueOf(textFieldPeriodOfSaving.getText());
				double InterestRate = Double.valueOf(textFieldInterestRate.getText());
				
				Investment objectCalculate = new Investment(Amount, Years, InterestRate);
				labelResult.setText("<html><center> Wp³acaj¹c " + textFieldInwestedAmonut.getText() +"z³ <br> na lokatê z oprocentowaniem rocznym " +
						textFieldPeriodOfSaving.getText() + "%,<br> po okresie " + textFieldInterestRate.getText() + " lat na lokacie bêdzie<br> " +
						objectCalculate.Calculate() + "z³</center><html>");
				
				
			}
			});
	}
	
}
