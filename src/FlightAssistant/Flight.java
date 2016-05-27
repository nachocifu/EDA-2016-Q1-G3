package FlightAssistant;


class Flight {

    private Long flightTime;
    private Long departureTime;
    private WeekDay departureDay;
    private Airport destination;
    private String airline;
    private Integer flightNumber;


    public Flight(Long flightTime, Long departureTime, WeekDay departureDay,
                  Airport destination, String airline, Integer flightNumber) {
        if(flightTime < 0 || departureTime < 0)
        	throw new IllegalArgumentException();
    	this.flightTime = flightTime;
        this.departureTime = departureTime;
        this.departureDay = departureDay;
        this.destination = destination;
        this.airline = airline;
        this.flightNumber = flightNumber;
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
    public Long getFlightTime() { 
    	return flightTime; 
    }

    /**
     * Get departure time in minutes
     *
     * @return
     */
    public Long getDepartureTime() { 
    	return departureTime; 
    }

    public WeekDay getDepartureDay() { 
    	return departureDay; 
    }

    public Airport getDestination() { 
    	return destination; 
    }

    /**
     * Equality test
     *
     * @param o to be compared for equality
     * @return boolean if o equals this
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

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

    public String getAirline() {
        return airline;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }
}
