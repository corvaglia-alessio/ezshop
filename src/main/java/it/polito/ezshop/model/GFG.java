package it.polito.ezshop.model;

//Java static method to implement
//Luhn algorithm

public class GFG {
  
//Returns true if given
//card number is valid
public static boolean checkLuhn(String cardNo)
{

	if(cardNo==null || cardNo.isEmpty())
		return false;
	
	try {
        Double.parseDouble(cardNo);
    } catch (NumberFormatException nfe) {
        return false;
    }
	 int nDigits = cardNo.length();
	
	 int nSum = 0;
	 boolean isSecond = false;
	  
	 for (int i = nDigits - 1; i >= 0; i--)
	 {
	
	     int d = cardNo.charAt(i) - '0';
	
	     if (isSecond == true)
	         d = d * 2;
	
	     // We add two digits to handle
	     // cases that make two digits
	     // after doubling
	     nSum += d / 10;
	     nSum += d % 10;
	
	     isSecond = !isSecond;
	 }
	 return (nSum % 10 == 0);
}


}