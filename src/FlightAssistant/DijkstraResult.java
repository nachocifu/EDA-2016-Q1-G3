package FlightAssistant;

import java.util.LinkedList;

public class DijkstraResult {

	private LinkedList<Flight> path = new LinkedList<>();
		
	public void addPath(Flight flight){
		path.add(flight);
	}
	
	public void print(){
		//TODO:implementarlo en KML
	}
	
	
}

