package Outputs;

import FlightAssistant.Airport;
import FlightAssistant.Flight;

/**
 * Created by nacho on 6/4/16.
 */
public class KmlFormat implements OutputFormater {
    @Override
    public String write(Flight flight) {
        return null;
    }

    @Override
    public String writeNotFound() {
        return null;
    }

    @Override
    public String writeHeader(Double price, Double flightTime, Double totalTime) {
        return null;
    }

    @Override
    public String write(Airport airport) {
        return null;
    }
}
