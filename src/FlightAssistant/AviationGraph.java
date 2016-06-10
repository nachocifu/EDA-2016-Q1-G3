package FlightAssistant;

import Priorities.Priority;
import Stopovers.Stopover;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class AviationGraph implements Serializable{

    private HashMap<String,Airport> airports;
    private HashMap<String,Flight> flights;
    
    public AviationGraph() {
        this.airports = new HashMap<String, Airport>();
        this.flights = new HashMap<String, Flight>();
        
    }

    public void insertAirport(Airport airport){
        if(this.airports.containsKey(airport.getCode())) return;
        this.airports.put(airport.getCode(), airport);
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
    
    public HashMap<String, Flight> getFlights() {
        return this.flights;
    }
    
    public void insertFlight(Double flightTime, Double departureTime, String departureDay,
                             String destinationAirport, String originAirport, String airline, Integer flightNumber, Double price){

        Airport destination = this.airports.get(destinationAirport);
        Airport origin = this.airports.get(originAirport);
        Flight flight = new Flight(flightTime, departureTime, WeekDay.getWeekDay(departureDay), 
        		                   destination, origin, airline, flightNumber, price);
        // agregar FlightAssistant a los to en origin
        // agregar origin a los from de destinatin.
    }
    
    public Stopover getBestPath(Airport origin, Airport target, Priority priority) {
        this.clearMarks();
        return Dijkstra.getShortestPathFromAToBWithFixedWeights(origin, target, priority);
    }
    
    public Stopover getBestPath(Airport origin, Airport target, Priority priority, WeekDay [] weekdays) {
        this.clearMarks();
        return Dijkstra.getShortestPathFromAToBWithFixedWeightsDepartingSpecificDays(origin, target, priority, weekdays);
    }
    
    public HashMap<String, Airport> getAirports() {
        return this.airports;
    }
    
    public void clearMarks() {
        for (String keys: this.airports.keySet())
            this.airports.get(keys).unTag();   
    }


    public boolean isOk(Flight flight) {
        return true;
    }

    public boolean isOk(Airport airport) {
        return true;
    }
}