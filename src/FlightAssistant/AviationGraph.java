package FlightAssistant;

import Priorities.Priority;
import Stopovers.Stopover;
import java.util.HashMap;
import java.util.HashSet;

public class AviationGraph {

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

    public String listFlights() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator() + "---> Listing all flights <---");
        sb.append(System.lineSeparator());
        for (Airport airport : AviationGraph.airports.values()) {
            sb.append("Flights departing from " + airport + System.lineSeparator());
            sb.append(airport.getOutboundFlights().toString()+ System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
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