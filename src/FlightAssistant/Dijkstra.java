package FlightAssistant;

import java.util.PriorityQueue;

import Stopovers.Stopover;
import Stopovers.StopoverPrice;

public class Dijkstra {
	
	private Double MIN = 0.0;

	public static Stopover algorithm(Airport origin, Airport target, Priority<Airport> priority){
		if(origin == null || target == null)
			return null;
		//clearMarks();
		PriorityQueue<Stopover> queue = new PriorityQueue<>();
		queue.offer(new Stopover(origin,0.0));
		while(!queue.isEmpty()){
			Stopover currentStop = queue.poll();
			while(currentStop.getFinalDestination().isTagged() && !queue.isEmpty()){
				queue.poll();
			}
                        if(currentStop.getFinalDestination().equals(target)) {
                            System.out.println("Se encontro: " + currentStop.toString());
                            return currentStop;
                        }
			currentStop.getFinalDestination().tag();
                        System.out.println("Entre al for");	
		for(Flight each : currentStop.getFinalDestination().getOutboundFlights()){
				if(!each.getDestination().isTagged()){
				                                System.out.println(each.whereTo());	
                                    queue.offer(new Stopover(currentStop, each.getDestination(), each, each.getPrice() + currentStop.getWeight()));                                    
				}
                                System.out.println(queue.peek().toString());
			}
		}
                System.out.println("No se encontro nada");
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
