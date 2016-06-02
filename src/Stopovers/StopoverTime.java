//package Stopovers;
//
//public class StopoverTime extends Stopover {
//
//	public StopoverTime(Airport air, Flight fli){
//		super(air,fli);
//		setCriteria(fli.getFLightTime());
//	}
//
//	public int compareTo(StopoverPrice other){
//		if(other == null)
//			throw new IllegalArgumentException();
//		Double crit = this.getWeight();
//		if(crit < other.getWeight())
//			return -1;
//		if(crit > other.getWeight())
//			return 1;
//		return 0;
//
//	}
//}
