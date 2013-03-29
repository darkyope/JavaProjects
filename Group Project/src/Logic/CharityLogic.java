package Logic;

import java.util.ArrayList;

public class CharityLogic {
	
	private ArrayList<ICharity> charities;
	
	public CharityLogic(){
		charities = new ArrayList<ICharity>();
	}
	
	public ArrayList<ICharity> GetCharities(){
		return this.charities;
	}
	
	public ArrayList<ICharity> GetCharities(CharityType type){
		ArrayList<ICharity> temp = new ArrayList<ICharity>();
		for(ICharity donation : this.charities){
			switch(type){
			case Animal:
				if(donation instanceof Animal)
					temp.add(donation);
				break;
			case Cultural:
				if(donation instanceof Cultural)
					temp.add(donation);
				break;
			case Education:
				if(donation instanceof Education)
					temp.add(donation);
				break;
			case Environmental:
				if(donation instanceof Environmental)
					temp.add(donation);
				break;
			case Health:
				if(donation instanceof Health)
					temp.add(donation);
				break;
			case NGO:
				if(donation instanceof NGO)
					temp.add(donation);
				break;
			}
		}
		
		return temp;
	}
	
	public void addDonation(CharityType type,
			String donorName,
			String charityName,
			double amt
			){
		
		ICharity newDon = CharityFactory.Create(type, donorName, charityName, amt);
		this.charities.add(newDon);
		
	}
	
	public void removeDonation(String donorName){
		
		for(int x = this.charities.size() - 1; x > -1; x--){
			if(this.charities.get(x).getDonorName() == donorName){
				this.charities.remove(x);
				break;
			}
		}
		
	}

}
