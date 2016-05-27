package FlightAssistant;

import java.util.Comparator;

public class Stopover{
	Double criteriaWeight;
	Airport airport;
	Flight flight;
	
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
	
}
