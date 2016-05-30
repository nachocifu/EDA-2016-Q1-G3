package Stopovers;

import FlightAssistant.Airport;
import FlightAssistant.Flight;

public interface Stopover{
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
	
	public void setWeight(Double weight){
		if(weight < 0)
			throw new IllegalArgumentException();
		criteriaWeight = weight;
	}
	
}
