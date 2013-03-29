package Logic;

public class NGO implements ICharity{
	
	private String donorName;
	private String charityName;
	private String charityType;
	private double donationAmount;
	
	
	@Override
	public void setDonorName(String dName){
		this.donorName=dName;
	}
	
	@Override
	public void setCharityName(String cName){
		this.charityName=cName;
	}
	
	@Override
	public void setCharityType(String type){
		this.charityType=type;
	}
	
	@Override
	public void setAmount(double amt){
		this.donationAmount=amt;
	}
	
	@Override
	public String getDonorName(){
		return this.donorName;
	}
	
	@Override
	public String getCharityName(){
		return this.charityName;
	}
	
	@Override
	public String getCharityType(){
		return this.charityType;
	}
	
	@Override
	public double getAmount(){
		return this.donationAmount;
	}
	

}
