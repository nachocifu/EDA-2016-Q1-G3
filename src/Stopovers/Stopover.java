package Stopovers;


public abstract class Stopover{
	private Double criteriaWeight;
	private Airport airport;
	private Flight flight;
	
	public Stopover(Airport air, Double weight, Flight fli){
		if(weight < 0 || air == null)
			throw new IllegalArgumentException();
		criteriaWeight = weight;
		airport = air;
		flight = fli;
	}
	
	public Double getWeight(){
		return criteriaWeight;
	}
	
	public Flight getFlight(){
		return flight;
	}
	
	public void setWeight(Double weight){
		if(weight < 0)
			throw new IllegalArgumentException();
		criteriaWeight = weight;
	}
	
}
