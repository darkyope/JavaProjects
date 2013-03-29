import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

public class Util {

	public static boolean isNumber(String str) {
		String exp = "\\d*";
		return str.matches(exp);
	}
	
	public static boolean isDecimal(String str){
		String exp = "^[-+]?\\d+(\\.\\d{0,2})?$";
		return str.matches(exp);
	}

	// E-mail validation
	public static boolean isValidEmailAddress(String emailAddress) {
		String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = emailAddress;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("(\\d{10})$");
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	// integer validation
	public static boolean isValidAge(String personAge) {
		Pattern pattern = Pattern.compile("^[0-9,;]+$");
		Matcher matcher = pattern.matcher(personAge);
		return matcher.matches();
	}
	
	public static DefaultTableModel CreateTableHeader(String[] columns){
		DefaultTableModel model = new DefaultTableModel();
		for(String str:columns){
			model.addColumn((Object)str);
		}
		return model;
	}
}
