package it.polito.ezshop.model;



public class GenerateNumericString {
	private static final String NUMERIC_STRING = "0123456789";
	
    public static String getRandomString() 
    { 
    	int count=10;
    	StringBuilder builder = new StringBuilder();
    	while (count-- != 0) {
    	int character = (int)(Math.random()*NUMERIC_STRING.length());
    	builder.append(NUMERIC_STRING.charAt(character));
    	}
    	return builder.toString();
    } 
}