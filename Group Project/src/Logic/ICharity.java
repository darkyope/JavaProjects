package Logic;

public interface ICharity {
	
	void setDonorName(String dName);
	void setCharityName(String cName);
	void setCharityType(String type);
	void setAmount(double amt);
	
	String getDonorName();
	String getCharityName();
	String getCharityType();
	double getAmount();

}
