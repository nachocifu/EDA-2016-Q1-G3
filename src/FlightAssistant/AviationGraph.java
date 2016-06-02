package FlightAssistant;

import Priorities.Priority;
import Stopovers.Stopover;
import java.util.HashMap;

public class AviationGraph {

    private static HashMap<String,Airport> airports;
    private AviationGraph() {
        airports = new HashMap<String, Airport>();
    }

    public void insertAirport(String code, Float lat, Float lon){
        Airport airport = new Airport(code,lat,lon);
        AviationGraph.airports.put(airport.getCode(), airport);
    }

    public String listAirports() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (Airport each : AviationGraph.airports.values()) {
            sb.append(each + "  Lat: " + each.getLatitude() + "  Lon: " + each.getLongitude());
            sb.append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
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
    
    public static void clearMarks() {
        for (String keys: airports.keySet())
            airports.get(keys).unTag();   
    }


}