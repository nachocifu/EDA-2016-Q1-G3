package FlightAssistant;

import Priorities.Priority;
import Stopovers.Stopover;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class AviationGraph implements Serializable{

    private static HashMap<String,Airport> airports;

    public AviationGraph() {
        airports = new HashMap<String, Airport>();
    }

    public void insertAirport(Airport airport){
        AviationGraph.airports.put(airport.getCode(), airport);
    }

    public Iterable<Airport> findAllAirports() {
        HashSet<Airport> allAirports = new HashSet<Airport> ();
        for(String each: this.airports.keySet()) {
            if(this.getAirports().get(each) != null) {
                allAirports.add(this.airports.get(each));
            }
        }
        return allAirports;
    }

    public Iterable<Flight> findAllFlights() {
        HashSet<Flight> allFlights = new HashSet<Flight> ();
        for(Airport each: this.findAllAirports()) {
            allFlights.addAll(each.getOutboundFlights());
        }
        return allFlights;
    }

    public void insertFlight(Double flightTime, Double departureTime, String departureDay,
                             String destinationAirport, String originAirport, String airline, Integer flightNumber, Double price){

        Airport destination = AviationGraph.airports.get(destinationAirport);
        Airport origin = AviationGraph.airports.get(originAirport);
        Flight flight = new Flight(flightTime, departureTime, WeekDay.getWeekDay(departureDay), 
        		                   destination, origin, airline, flightNumber, price);
        // agregar FlightAssistant a los to en origin
        // agregar origin a los from de destinatin.
    }
    
    public Stopover getBestPath(Airport origin, Airport target, Priority priority) {
        clearMarks();
        return Dijkstra.getShortestPathFromAToBWithFixedWeights(origin, target, priority);
    }
    
    public HashMap<String, Airport> getAirports() {
        return this.airports;
    }
    
    public static void clearMarks() {
        for (String keys: airports.keySet())
            airports.get(keys).unTag();   
    }


}