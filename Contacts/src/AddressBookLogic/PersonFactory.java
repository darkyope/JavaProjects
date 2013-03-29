package AddressBookLogic;

public class PersonFactory {

	public static Person create(String fName, String lName, int age, String email, String phoneNumber){
		Person newPerson = null;
		
		newPerson.setFirstName(fName);
		newPerson.setLastName(lName);
		newPerson.setAge(age);
		newPerson.setEmail(email);
		newPerson.setPhoneNumber(phoneNumber);
		
		return newPerson;
	}
}
