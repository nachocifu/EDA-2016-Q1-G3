package Stopovers;

public class StopoverTime extends Stopover {

	public StopoverTime(Airport air, Flight fli){
		super(air,fli);
		criteriaWeight = fli.getFLightTime();
	}
	
	public int compareTo(StopoverPrice other){
		if(other == null)
			throw new IllegalArgumentException();
		if(criteriaWeight < other.getWeight())
			return -1;
		if(criteriaWeight > other.getWeight())
			return 1;
		return 0;
		
	}
}
