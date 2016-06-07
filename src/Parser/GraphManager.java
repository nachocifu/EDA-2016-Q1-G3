package Parser;

import FlightAssistant.Airport;
import FlightAssistant.Flight;


public interface GraphManager {

    Airport findAirportByCode(String code);

    void add(Airport airport);

    void add(Flight flight);

}
