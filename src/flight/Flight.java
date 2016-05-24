package flight;


public class Flight {

    private Long flightTime;
    private Long distance;
    private Long departureTime;
    private WeekDay day;
    private Airport destination;
    private String airline;
    private Integer flightNumber;


    public Flight(String airline, Integer flightNumber, ) {

    }

    public String getCode() {
        return "#" + this.airline + this.flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return this.getCode().equals(flight.getCode());

    }

    @Override
    public int hashCode() {
        return this.getCode().hashCode();
    }
}
