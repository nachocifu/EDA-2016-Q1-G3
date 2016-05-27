package FlightAssistant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class Airport {
	
	private double MAX_LON = 90.0;
	private double MIN_LON = -90.0;
	private double MAX_LAT = 180.0;
	private double MIN_LAT = -180.0;
	private int MAX_CHARACTERS = 3;
	
	private String code;
	private Float latitude;
	private Float longitude;
	private HashMap<Airport,HashSet<Flight>> to;
	private HashSet<Flight> from;


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

	public String listDepartureFlights() {
		StringBuilder sb = new StringBuilder();

		Iterator<HashSet<Flight>> iteratorSets = this.to.values().iterator();

		Flight flight;
		while (iteratorSets.hasNext()) {
			HashSet<Flight> set = iteratorSets.next();
			Iterator<Flight> itr = set.iterator();
			while ( itr.hasNext() ) {
				flight = itr.next();
				sb.append(flight);
				sb.append(System.lineSeparator());
			}
		}

		return sb.toString();
	}
	
	/**
	 * Return void
	 * 
	 * @param Flight flight to add
	 */
	 public void addFrom(Flight flight){
		 if(flight == null)
			 throw new IllegalArgumentException();
		 from.add(flight);
	 }
}
