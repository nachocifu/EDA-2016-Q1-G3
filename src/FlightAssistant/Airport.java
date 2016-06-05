package FlightAssistant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Airport implements Serializable{
	
	private double MAX_LON = 90.0;
	private double MIN_LON = -90.0;
	private double MAX_LAT = 180.0;
	private double MIN_LAT = -180.0;
	private int MAX_CHARACTERS = 3;
	
	private String code;
	private Float latitude;
	private Float longitude;
	private LinkedList<Flight> outboundFlights;
        private HashSet<Airport> posibleOutboundFlightsDestination;
	private HashSet<Airport> posibleInboundFlightsOrigin;
	private boolean tag;


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
		
		this.code = code;
		this.latitude = lat;
		this.longitude = lon;
                this.outboundFlights = new LinkedList<Flight>();
                this.posibleInboundFlightsOrigin = new HashSet<Airport> ();
                this.posibleOutboundFlightsDestination = new HashSet<Airport> ();
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
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * Return Airport longitud
	 *
	 * @return longitud
     */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * Return string representation
	 *
	 * @return string
     */
	public String toString() { 
		return this.code; 
	}
        
        public void addFlight(Flight flight) {
            this.outboundFlights.add(flight);
            this.posibleOutboundFlightsDestination.add(flight.getDestination());
        }

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
		 posibleInboundFlightsOrigin.add(flight.getOrigin());
	 }
	 
	 public LinkedList<Flight> getOutboundFlights(){
		 //TODO: sea cual sea la impl. va a devovler una lista
		 return this.outboundFlights;
	 }
         
         public HashSet<Airport> getPosibleOutboundDestination() {
             return this.posibleOutboundFlightsDestination;
         }
	 
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

