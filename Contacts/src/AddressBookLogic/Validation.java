package AddressBookLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	// E-mail validation
	public boolean isValidEmailAddress(String emailAddress){  
		String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		CharSequence inputStr = emailAddress;  
		Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		Matcher matcher = pattern.matcher(inputStr);  
		return matcher.matches();  
	} 
	
	public boolean isValidPhoneNumber(String phoneNumber){
		Pattern pattern = Pattern.compile("(\\d{10})$");
	    Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
	
	// integer validation
	public boolean isValidAge(String personAge){
		Pattern pattern = Pattern.compile("^[0-9,;]+$");
		Matcher matcher = pattern.matcher(personAge);
		return matcher.matches();
	}
	
	
}


