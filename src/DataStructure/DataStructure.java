package DataStructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import FlightAssistant.Airport;
import FlightAssistant.AviationGraph;
import FlightAssistant.Flight;
import FlightAssistant.WeekDay;

public class DataStructure {

	private static int DAYS = 7;
	
	//Structures: TreeMap & HashMap<String,Airport> & TreeMap<String, Flight>
	private DataStructure dataTree = new DataStructure();
	private static TreeMap<String,Flight>[] flights = new TreeMap[DAYS];
	private static HashMap<String,Airport> airports = new HashMap();
	
	private DataStructure(){
		for(int i = 0; i < DAYS; i++){
			flights[i] = new TreeMap(new Comparator<Flight>(){
				@Override
				public int compare(Flight f1, Flight f2) {
					return (int) (f1.getDepartureTime() - f2.getDepartureTime());
				}
			});
		}
	}
	
	//Control over the flights.
	public static void addFlight(Flight fli, int day, String key){
		if(fli == null || key == null)
			throw new NullPointerException();
		flights[day].put(key,fli);
		airports.get(key).addFlight(fli);
	}
	
	public Iterable<Flight> findAllFlights() {
		HashSet<Flight> allFlights = new HashSet<Flight>();
		for(int i = 0; i < DAYS; i++){
        	allFlights.addAll(flights[i].values()); //agrega los vuelos dia a dia.
        }
		return allFlights;
    }
	
	public void insertFlight(Double flightTime, Double departureTime, String departureDay, Airport destination, 
							 Airport origin, String airline, Integer flightNumber, Double price){
		
		//Al crear este flight referenciamos al mismo lugar tanto lo que esta en el arbol, 
		//como tambien lo que esta en el hash.
		Flight flight = new Flight(flightTime, departureTime, WeekDay.getWeekDay(departureDay), destination, 
								   origin, airline, flightNumber, price);
		flights[WeekDay.getNumberOfDay(WeekDay.getWeekDay(departureDay).toString())].put(flight.getCode(), flight);
		airports.get(origin.getCode()).addFlight(flight);
	}
	
	//Control over the airports.
	public static void insertAirport(Airport airport){
		if(airport == null)
			throw new NullPointerException();
        airports.put(airport.getCode(), airport);
    }
	
	public Iterable<Airport> findAllAirports(){
       return airports.values();
    }
	
}	
