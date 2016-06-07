package FlightAssistant;


import java.io.Serializable;

public class Flight implements Serializable {

    private Double flightTime;
    private Double departureTime;
    private Double arrivalTime;
    private WeekDay departureDay;
    private Airport destination;
    private Airport origin;
    private String airline;
    private Integer flightNumber;
    private Double price;

/**
 * 
 * @param flightTime
 * @param departureTime 
 * Asumiendo que el dia arranca en 00 se pasa en minutos desde que arranca el dia. 
 * Para el horario exacto se le suma el horario que sale más el dia que sale.
 * @param departureDay
 * @param destination
 * @param origin
 * @param airline
 * @param flightNumber
 * @param price 
 */
    public Flight(Double flightTime, Double departureTime, WeekDay departureDay,
                  Airport destination, Airport origin, String airline, 
                  Integer flightNumber, Double price) {
//        if(checks(flightTime, departureTime, departureDay, destination, origin,
//        		  airline, flightNumber, price))
//        	throw new IllegalArgumentException();
        this.flightTime = flightTime;
        this.departureDay = departureDay;
        this.departureTime = departureTime + departureDay.getDaysInMinutes();
        this.arrivalTime = this.departureTime + this.flightTime;
        this.destination = destination;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.price = price;
        this.origin = origin;
    }
    
    /*
     * @param same as constructor
     * Returns True if there is any parameter wrong
     */
    private Boolean checks(Long flightTime, Long departureTime, WeekDay departureDay,
                           Airport destination, Airport origin, String airline, 
                           Integer flightNumber, Double price){
    	boolean flag = false;
    	if(flightTime < 0 || departureTime < 0 || price < 0)
    		return true;
    	for(WeekDay day : WeekDay.values()){
    		if(departureDay == day)
    			flag = true;
    	}
    	if(!flag)
    		return true;
    	return false;
    }

    public Airport getOrigin() {
        return this.origin;
    }
    
    /**
     * Concatenate airline and flightnumber to generate unique identifier
     *
     * @return string representation of FlightAssistant
     */
    public String getCode() {
        return "#" + this.airline + this.flightNumber;
    }

    /**
     * Get duration of this flight
     *
     * @return Long duration in minutes
     */
    public Double getFlightTime() { 
    	return flightTime; 
    }

    /**
     * Get departure time in minutes
     *
     * @return
     */
    public Double getDepartureTime() { 
    	return departureTime; 
    }

    public WeekDay getDepartureDay() { 
    	return departureDay; 
    }

    public Airport getDestination() { 
    	return destination; 
    }
    
    public Double getArrivalTime() {
        return this.arrivalTime;
    }
    
    /**
     * Equality test
     *
     * @param o to be compared for equality
     * @return boolean if o equals this
     */
    public boolean equals(Object o) {
        if (this == o)
        	return true;
        if (o == null || getClass() != o.getClass())
        	return false;
        Flight flight = (Flight) o;
        return this.getCode().equals(flight.getCode());
    }

    /**
     * Unique identifier to each flight
     *
     * @return int hashcode of string unique to each FlightAssistant
     */
    public int hashCode() {
        return this.getCode().hashCode();
    }

    public String toString() { 
    	return this.getCode();
    }
    
    public Double getPriority(String priority) {
        if(priority.equals("ft")) {
            return (Double)getFlightTime();
        }
        else{
            return getPrice();
        }
    }
    
    public String whereTo() {
        return "From: " + origin.toString() + " To: " + destination.toString();
    }
    
    public String getAirline() {
        return this.airline;
    }

    public Integer getFlightNumber() {
        return this.flightNumber;
    }

    public Double getPrice(){
    	return this.price;
    }
}
