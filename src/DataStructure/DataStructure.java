package DataStructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import FlightAssistant.Airport;
import FlightAssistant.Flight;
import FlightAssistant.WeekDay;

public class DataStructure {

	private static int DAYS = 7;
	
	//La clase Order el unico fin que tiene es que el arbol se ordene acorde al tiempo de salida
	//Si ponemos TreeMap<Double,Flight>no se admitirian 2 vuelos que salen a la misma hora.
	private static class Order{
		String str;
		Double time;
		
		public Order(String str, Double time){
			this.str = str;
			this.time = time;
		}
		
		public double getTime(){
			return time;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null)
				return false;
			if(obj.getClass().equals(this.getClass()))
				return false;
			Order or = (Order) obj;
			return or.str.equals(this.str);
		}
	}
	
	//Structures: TreeMap & HashMap<String,Airport> & TreeMap<String, Flight>
	private DataStructure dataTree = new DataStructure();
	private static TreeMap<Order,Flight>[] flights = new TreeMap[DAYS];
	private static HashMap<String,Airport> airports = new HashMap();
	
	private DataStructure(){
		for(int i = 0; i < DAYS; i++){
			flights[i] = new TreeMap(new Comparator<Order>(){
				@Override
				public int compare(Order o1, Order o2) {
					return (int) (o1.getTime() - o2.getTime());
				}
			});
		}
	}
	
	//Control over the flights.
	public static void addFlight(Flight fli, int day, String key){
		if(fli == null || key == null)
			throw new NullPointerException();
		Order or = new Order(fli.getCode(),fli.getDepartureTime());
		flights[day].put(or,fli);
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
		Order or = new Order(flight.getCode(),flight.getDepartureTime());
		flights[WeekDay.getNumberOfDay(WeekDay.getWeekDay(departureDay).toString())].put(or, flight);
		airports.get(origin.getCode()).addFlight(flight);
	}
	
	//Control over the airports.
	public static void insertAirport(Airport airport){
		if(airport == null)
			throw new NullPointerException();
        airports.put(airport.getCode(), airport);
    }
	
	public static Iterable<Airport> findAllAirports(){
       return airports.values();
    }
	
	public static HashMap<String, Airport> getAirports(){
        return airports;
    }
	
}	
