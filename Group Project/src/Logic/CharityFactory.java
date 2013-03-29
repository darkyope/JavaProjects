package Logic;

// Factory class to retrieve all the user inputs and put into an array.
public class CharityFactory {
	
	public static ICharity Create(CharityType type, String donorName, String charityName, double amt){
	
		ICharity newDonation = null;
		switch(type){
		case Animal:
			newDonation = new Animal();
			break;
		case Cultural:
			newDonation = new Cultural();
			break;
		case Education:
			newDonation = new Education();
			break;
		case Environmental:
			newDonation = new Environmental();
			break;
		case Health:
			newDonation = new Health();
			break;
		case NGO:
			newDonation = new NGO();
			break;
			
		}
		
		if(newDonation != null){
			newDonation.setDonorName(donorName);
			newDonation.setCharityName(charityName);
			newDonation.setAmount(amt);
		}
		
		return newDonation;
		
		
	}

}
