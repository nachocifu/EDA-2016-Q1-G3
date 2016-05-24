package flight;

public class Airport {
	
	private double MAX_LON = 90.0;
	private double MIN_LON = -90.0;
	private double MAX_LAT = 180.0;
	private double MIN_LAT = -180.0;
	
	private String name;
	private Float latitude;
	private Float longitude;
	
	public Airport(String name, Float lat, Float lon){
		if (name == null || name.length() > 3 || lat > MAX_LON || lat < MIN_LON
			|| lon > MAX_LAT || lon < MIN_LAT){
			throw new IllegalArgumentException();
		}
	}
	
}
