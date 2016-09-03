package Investment;

import java.text.NumberFormat;

public class Investment 
{
	private double amount;
	private int years;
	private double interestRate;
	
	public Investment(double aAmount, int aYears, double aInterestRate)
	{
		amount = aAmount;
		years = aYears;
		interestRate = aInterestRate;
	}
	
	/**
	* Oblicz ile pieniedzy bedzie na lokacie
	* @amountString zwrocona wartosc lokaty po zakonczeniu oszczedzania, wartosc zwrocona jako lancuch
	*/
	public String Calculate()
	{
		for (int i=1; i<=years; i++)
		{
			double interest = amount * interestRate/100;
			amount += interest; 
		}

		NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String amountString = nf.format(amount);
		return amountString;
		
		
	}

}

