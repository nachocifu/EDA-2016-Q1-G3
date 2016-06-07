package FlightAssistant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

public class Airport implements Serializable{
	
	private double MAX_LON = 90.0;
	private double MIN_LON = -90.0;
	private double MAX_LAT = 180.0;
	private double MIN_LAT = -180.0;
	private int MAX_CHARACTERS = 3;
	private int DAYS = 7;
	
	private String code;
	private Float latitude;
	private Float longitude;
	private TreeMap<Order,Flight>[] flightsPerDay;
	private HashSet<Airport> posibleInboundFlightsOrigin;
	private boolean tag;
	
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

	/**
	 * Contructor
	 *
	 * @param code unique string representing this entity.
	 * @param lat latitud coordinate
	 * @param lon longitud coordinate
	 * @throws IllegalArgumentException
     */
	public Airport(String code, Float lat, Float lon){
		if (code == null || code.length() > MAX_CHARACTERS || lat > MAX_LON || lat < MIN_LON
			|| lon > MAX_LAT || lon < MIN_LAT){
			throw new IllegalArgumentException();
		}
        this.flightsPerDay = new TreeMap[DAYS];
        initializeFlightsPerDay();
        this.code = code;
	this.latitude = lat;
	this.longitude = lon;
        //this.outboundFlights = new TreeMap<Order,Flight>();
        this.posibleInboundFlightsOrigin = new HashSet<Airport> ();
        //this.posibleOutboundFlightsDestination = new HashSet<Airport> ();
	}
        
        private void initializeFlightsPerDay(){
            for(int i = 0; i < DAYS; i++){
                flightsPerDay[i] = new TreeMap<Order,Flight>(new Comparator<Order>(){
                    @Override
                    public int compare(Order t, Order t1) {
                        return (int) (t.getTime() - t1.getTime());
                    }
                    
                });
            }
        }

	/**
	 * Return Airport code
	 *
	 * @return String
     */
	public String getCode() {
		return code;
	}

	/**
	 * Return Airport latitud
	 *
	 * @return latitud
     */
	public Float getLatitude(){
		return latitude;
	}

	/**
	 * Return Airport longitud
	 *
	 * @return longitud
     */
	public Float getLongitude(){
		return longitude;
	}

	/**
	 * Return string representation
	 *
	 * @return string
     */
	public String toString(){ 
		return this.code; 
	}
        
    public void addFlight(Flight flight){
        //this.outboundFlights.put(new Order(flight.getCode(), flight.getDepartureTime()),flight);
        //this.posibleOutboundFlightsDestination.add(flight.getDestination());
        this.addFrom(flight);
        
    }

   // public void removeFlight(Flight flight){
   //     this.outboundFlights.remove(flight);
   // }
        
//	public String listDepartureFlights() {
//		StringBuilder sb = new StringBuilder();
//
//		Iterator<HashSet<Flight>> iteratorSets = this.outboundFlights.values().iterator();
//
//		Flight flight;
//		while (iteratorSets.hasNext()) {
//			HashSet<Flight> set = iteratorSets.next();
//			Iterator<Flight> itr = set.iterator();
//			while ( itr.hasNext() ) {
//				flight = itr.next();
//				sb.append(flight);
//				sb.append(System.lineSeparator());
//			}
//		}
//
//		return sb.toString();
//	}
	
	/**
	 * Return void
	 * 
	 * @param Flight flight to add
	 */
    public void addFrom(Flight flight){
	if(flight == null)
             throw new IllegalArgumentException();
        Order aux = new Order(flight.getCode(), flight.getDepartureTime());
        flightsPerDay[WeekDay.getNumberOfDay(flight.getDepartureDay().toString())].put(aux, flight);
    }
	 
    public Collection<Flight> getOutboundFlights(){
	 //TODO: sea cual sea la impl. va a devovler una lista
	 Collection<Flight> collection = new ArrayList<Flight>();
         for(int i = 0; i < DAYS; i++){
             collection.addAll(flightsPerDay[i].values());
         }
         return collection;
    }
         
     public void deleteAllFlights(){
         initializeFlightsPerDay();
     }
     
     //public HashSet<Airport> getPosibleInboundFlightsOrigins() {
         
     //}
     
    // public HashSet<Airport> getPosibleOutboundDestinations(){
    //     HashSet<Airport> outboundDestinations = new HashSet <Airport>();
    //     for(Flight each: this.outboundFlights.values()) {
    //         outboundDestinations.add(each.getDestination());
     //    }
     //    return outboundDestinations;
     //}
	 
    public void tag(){
	this.tag = true;
    }
	 
    public void unTag(){
        this.tag = false;
    }
	 
    public boolean isTagged(){
	 return this.tag;
    }
}

