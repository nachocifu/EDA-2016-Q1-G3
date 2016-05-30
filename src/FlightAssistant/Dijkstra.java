package FlightAssistant;

import java.util.PriorityQueue;

import Stopovers.Stopover;

public class Dijkstra {
	
	private Double MIN = 0.0;

	public DijkstraResult algorithm(Airport origin, Airport target, Priority<Airport> priority){
		DijkstraResult result = new DijkstraResult();
		if(origin == null || target == null)
			return result;
		clearMarks();
		PriorityQueue<Stopover> queue = new PriorityQueue<>();
		queue.offer(new Stopover(origin,MIN,null));
		while(!queue.isEmpty()){
			Stopover currentStop = queue.poll();
			while(currentStop.airport.isTagged()){
				queue.poll();
			}
			result.addPath(currentStop.flight);
			currentStop.airport.tag();
			for(Flight each : currentStop.airport.getOutboundFlights()){
				if(!each.getDestination().isTagged()){
					queue.offer(new Stopover(each.getDestination(),
								each.getFlightTime() + currentStop.getWeight(), each));
				}
			}
		}
		return null;
	}
	
	/**
	 * @param 
	 * 
	 * 
	 */
	private void clearMarks(){
		
	}
}
