package FlightAssistant;


import java.util.HashMap;

class AviationGraph {

    private HashMap<String,Airport> airports;

    public AviationGraph() {
        airports = new HashMap<String, Airport>();
    }

    public void insertAirport(String code, Float lat, Float lon){

        Airport airport = new Airport(code,lat,lon);

        this.airports.put(airport.getCode(), airport);
    }

    public String listAirports() {
        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator());
        for ( Airport each : this.airports.values() ) {
            sb.append(each + "  Lat: " + each.getLatitude() + "  Lon: " + each.getLongitude());
            sb.append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public String listFlights() {
        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator() + "---> Listing all flights <---");
        sb.append(System.lineSeparator());
        for ( Airport airport : this.airports.values() ) {
            sb.append("Flights departing from " + airport + System.lineSeparator());
            sb.append(airport.listDepartureFlights() + System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public void insertFlight(Long flightTime, Long departureTime, String departureDay,
                             String destinationAirport, String originAirport, String airline, Integer flightNumber){

        Airport destination = this.airports.get(destinationAirport);
        Airport origin = this.airports.get(originAirport);

        Flight flight = new Flight(flightTime, departureTime, WeekDay.getWeekDay(departureDay), destination, airline, flightNumber);

        // agregar FlightAssistant a los to en origin

        // agregar origin a los from de destinatin.
    }

}
