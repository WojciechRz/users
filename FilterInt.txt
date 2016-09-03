package Investment;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

/**
* Filtr dla typu int, przepuszcza tylko znaki cyfr
*/
public class FilterInt extends DocumentFilter
{
	public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException 
	{

        String goodString = "";

        for (int i = 0; i < string.length(); i++) 
        {
            char c = string.charAt(i);
            if (Character.isDigit(c)) 
            {
                goodString += c;
            }
        }
     

        super.insertString(fb, offset, goodString, attr);
        }
}
