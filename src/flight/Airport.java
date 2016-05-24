package flight;

import java.util.HashMap;
import java.util.HashSet;

public class Airport {
	
	private double MAX_LON = 90.0;
	private double MIN_LON = -90.0;
	private double MAX_LAT = 180.0;
	private double MIN_LAT = -180.0;
	
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
     */
	public Airport(String code, Float lat, Float lon){
		if (code == null || code.length() > 3 || lat > MAX_LON || lat < MIN_LON
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
}
