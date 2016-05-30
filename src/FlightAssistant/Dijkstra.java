package FlightAssistant;

import java.util.PriorityQueue;

import Stopovers.Stopover;
import Stopovers.StopoverPrice;
import java.util.LinkedList;

public class Dijkstra {
	
	private Double MIN = 0.0;

	public static Stopover getShortestPathFromAToBWithFixedWeights(Airport origin, Airport target, Priority priority){
		if(origin == null || target == null)
			return null;
		//clearMarks();
                LinkedList<Flight> prevFlights = new LinkedList<Flight> ();
		PriorityQueue<Stopover> queue = new PriorityQueue<>();
		Stopover dbgassist = null;
                queue.offer(new Stopover(origin,0.0));
		while(!queue.isEmpty()){
			Stopover currentStop = queue.poll();
			while(currentStop.getFinalDestination().isTagged() && !queue.isEmpty()){
				currentStop = queue.poll();
			}
                        if(currentStop.getFinalDestination().equals(target)) {
                            System.out.println("Se encontro: " + currentStop.toString() + "<<<<<<<<<<<<<<<<<<<<<<<<<");
                            return currentStop;
                        }
			currentStop.getFinalDestination().tag();
                        //System.out.println("Entre al for ---------------------------------------------------");
                        prevFlights = currentStop.getFlights();
                        //System.out.println("Los vuelos previos fueron" + prevFlights.toString());
		for(Flight each : currentStop.getFinalDestination().getOutboundFlights()){
                            if(!each.getDestination().isTagged()){
                          //      System.out.println("Los vuelos previos fueron" + prevFlights.toString());
                                dbgassist = new Stopover(prevFlights, each.getDestination(), each, each.getPriority("ft") + currentStop.getWeight());
                                queue.offer(dbgassist);
                            //    System.out.println(dbgassist.getFlights().toString());
                              //  System.out.println("Ofreci: " + dbgassist.toString());
                            }
			}
		}
                System.out.println("No se encontro nada");
		return null;
	}
	
        public static Stopover getShortestPathFromAToBWithTotalTime(Airport origin, Airport target){
		if(origin == null || target == null)
			return null;
		//clearMarks();
                LinkedList<Flight> prevFlights = new LinkedList<Flight> ();
		PriorityQueue<Stopover> queue = new PriorityQueue<>();
		Stopover dbgassist = null;
                queue.offer(new Stopover(origin,0.0));
		while(!queue.isEmpty()){
			Stopover currentStop = queue.poll();
			while(currentStop.getFinalDestination().isTagged() && !queue.isEmpty()){
				currentStop = queue.poll();
			}
                        if(currentStop.getFinalDestination().equals(target)) {
                            System.out.println("Se encontro: " + currentStop.toString() + "<<<<<<<<<<<<<<<<<<<<<<<<<");
                            return currentStop;
                        }
			currentStop.getFinalDestination().tag();
                        //System.out.println("Entre al for ---------------------------------------------------");
                        prevFlights = currentStop.getFlights();
                        //System.out.println("Los vuelos previos fueron" + prevFlights.toString());
		for(Flight each : currentStop.getFinalDestination().getOutboundFlights()){
                            if(!each.getDestination().isTagged()){
                          //      System.out.println("Los vuelos previos fueron" + prevFlights.toString());
                                dbgassist = new Stopover(prevFlights, each.getDestination(), each, (Dijkstra.getTimeAtTheAirport(each, prevFlights))+each.getFlightTime() + currentStop.getWeight());
                                queue.offer(dbgassist);
                            //    System.out.println(dbgassist.getFlights().toString());
                              //  System.out.println("Ofreci: " + dbgassist.toString());
                            }
			}
		}
                System.out.println("No se encontro nada");
		return null;
	}
        
        public static Double getTimeAtTheAirport(Flight departingFlight, LinkedList<Flight> prevFlights) {
            if(prevFlights.isEmpty()) {
                return 0.0;
            }
            Double timeStopped = (departingFlight.getDepartureTime() - prevFlights.getLast().getArrivalTime());
            if(timeStopped < 0) {
                timeStopped = 24*60*7  - timeStopped;
            }
            System.out.println(timeStopped.toString());
            return timeStopped;
        }
        
	/**
	 * @param 
	 * 
	 * 
	 */
	private void clearMarks(){
		
	}
}